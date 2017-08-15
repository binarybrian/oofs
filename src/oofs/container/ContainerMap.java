package oofs.container;

import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

import oofs.entity.FileEntity;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

public class ContainerMap extends ForwardingMap<String, FileEntity>
{
	final Map <String, FileEntity> entitys = Maps.newConcurrentMap();
	
	@Override
	protected Map<String, FileEntity> delegate()
	{
		return entitys;
	}

	@Override
	public void clear()
	{
		for (FileEntity fileEntity : entitys.values())
		{
			if (fileEntity instanceof ContainerEntity)
			{
				((ContainerEntity)fileEntity).removeAll();
			}
		}
		super.clear();
	}
	
	public FileEntity addEntity(FileEntity entity) throws PathExistsException
	{
		String name = entity.getName();
		if (entitys.containsKey(name))
		{
			throw new PathExistsException(name);
		}
		return super.put(name, entity);
	}

	public FileEntity removeEntity(String fileName) throws PathNotFoundException
	{
		if (!entitys.containsKey(fileName))
		{
			throw new PathNotFoundException(fileName);
		}
		return super.remove(fileName);
	}
	
	public int getContainerSize()
	{
		int size = 0;
		for (FileEntity fileEntity : entitys.values())
		{
			size += fileEntity.getSize();
		}
		return size;
	}
	
	public static ContainerMap create()
	{
		return new ContainerMap();
	}
}
