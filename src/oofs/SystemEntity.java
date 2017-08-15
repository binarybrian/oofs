package oofs;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import oofs.container.ContainerEntity;
import oofs.entity.AbstractEntity;
import oofs.entity.DriveEntity;
import oofs.entity.TextEntity;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

/** 
 * A basic, singleton "system" to manage drives and provide a point of 
 * entry for testing and utilizing the various entity components 
 * comprising the OO file system. 
 **/
public class SystemEntity
{
	private static class LazyInstance
	{
		static final SystemEntity instance = new SystemEntity();
	}
	
	public static SystemEntity getInstance()
	{
		return LazyInstance.instance;
	}

	public static SystemEntity system()
	{
		return getInstance();
	}
	
	private final Map <String, DriveEntity> entitys = Maps.newConcurrentMap();
	
	public Optional <ContainerEntity> findContainerEntity(Iterable <String> pathTokens)
	{
		if (Iterables.isEmpty(pathTokens))
		{
			return Optional.empty();
		}
		
		Iterator <String> iterator = pathTokens.iterator();
		
		String driveName = iterator.next();
		
		AbstractEntity entity = entitys.get(driveName);
		while (iterator.hasNext())
		{
			if (entity instanceof ContainerEntity)
			{
				entity = ((ContainerEntity)entity).getFileEntity(iterator.next());
				if (entity == null)
				{
					break;
				}
			}
			else /* TextEntitys cannot be containers */
			{
				return Optional.empty();
			}
		}
		
		if (entity instanceof ContainerEntity)
		{
			return Optional.of((ContainerEntity)entity);
		}
		return Optional.empty();
	}

	public Optional <AbstractEntity> findEntity(Iterable <String> pathTokens) throws PathNotFoundException
	{
		if (Iterables.isEmpty(pathTokens))
		{
			return Optional.empty();
		}
		
		Iterator <String> iterator = pathTokens.iterator();
		
		String driveName = iterator.next();
		AbstractEntity entity = entitys.get(driveName);
		
		while (iterator.hasNext())
		{
			String nextName = iterator.next();
			
			if (entity instanceof ContainerEntity)
			{
				entity = ((ContainerEntity)entity).getFileEntity(nextName);
			}
			else if (entity instanceof TextEntity)
			{
				if (nextName != entity.getName())
				{
					throw new PathNotFoundException("Path not found.  Expected: " + nextName + ", Actual: " + entity.getName());
				}
				if (iterator.hasNext())
				{
					throw new PathNotFoundException("Invalid Path. " + TextEntity.class.getSimpleName() + " cannot have descendents: " + nextName);
				}
			}
			else
			{
				throw new PathNotFoundException("In findEntity.  Unknown entity type: " + entity.getClass().getSimpleName());
			}
			
			if (entity == null)
			{
				break;
			}
		}
		
		return Optional.ofNullable(entity);
	}
	
	public DriveEntity createDriveEntity(String name) throws PathExistsException
	{
		if (entitys.containsKey(name))
		{
			throw new PathExistsException(name);
		}
		
		DriveEntity driveEntity = new DriveEntity(name);
		entitys.put(name, driveEntity);
		
		return driveEntity;
	}

	public void clear()
	{
		entitys.clear();
	}

	public void deleteDrive(String name)
	{
		entitys.remove(name);
	}
}
