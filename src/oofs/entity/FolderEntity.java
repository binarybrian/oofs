package oofs.entity;

import oofs.container.ContainerEntity;
import oofs.container.ContainerMap;
import oofs.entity.Entitys.EntityType;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

public class FolderEntity extends FileEntity implements ContainerEntity
{
	final ContainerMap entitys = ContainerMap.create();
	
	public FolderEntity(String name, ContainerEntity parentContainer)
	{ 
		this(EntityType.FOLDER, name, parentContainer);
	}

	public FolderEntity(EntityType entityType, String name, ContainerEntity parentContainer)
	{
		super(entityType, name, parentContainer);
	}
	
	@Override
	public int getSize()
	{
		return entitys.getContainerSize();
	}

	@Override
	public FileEntity getFileEntity(String fileName)
	{
		return entitys.get(fileName);
	}

	@Override /* ContainerEntity */
	public void addFileEntity(FileEntity fileEntity) 	throws PathExistsException
	{
		entitys.addEntity(fileEntity);
	}

	@Override /* ContainerEntity */
	public void removeFileEntity(String fileName) throws PathNotFoundException
	{
		entitys.removeEntity(fileName);
	}

	@Override
	public void removeAll()
	{
		entitys.clear();
	}
	
	@Override
	public AbstractEntity getEntity()
	{
		return this;
	}
}
