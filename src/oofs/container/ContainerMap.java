package oofs.container;

import java.util.Map;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

import oofs.entity.FileEntity;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

public class ContainerMap extends ForwardingMap<String, FileEntity>
{
	final Map <String, FileEntity> fileEntitys = Maps.newConcurrentMap();
	
	@Override
	protected Map<String, FileEntity> delegate()
	{
		return fileEntitys;
	}
	
	@Override
	public void clear()
	{
		for (FileEntity fileEntity : fileEntitys.values())
		{
			if (fileEntity instanceof ContainerEntity)
			{
				((ContainerEntity)fileEntity).clear();
			}
		}
		super.clear();
	}
	
	public FileEntity addEntity(FileEntity entity) throws PathExistsException
	{
		String name = entity.getName();
		if (fileEntitys.containsKey(name))
		{
			throw new PathExistsException(name);
		}
		return super.put(name, entity);
	}

	public FileEntity removeEntity(String fileName) throws PathNotFoundException
	{
		if (!fileEntitys.containsKey(fileName))
		{
			throw new PathNotFoundException(fileName);
		}
		return super.remove(fileName);
	}
	
	public int getContainerSize()
	{
		return fileEntitys.values()
				.stream()
				.mapToInt(f -> f.getSize())
				.sum();
		
	}
	
	public static ContainerMap create()
	{
		return new ContainerMap();
	}
}
