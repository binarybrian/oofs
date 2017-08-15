package oofs.entity;

import oofs.container.ContainerEntity;
import oofs.container.ContainerMap;
import oofs.entity.Entitys.EntityType;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

public class DriveEntity extends AbstractEntity implements ContainerEntity
{
	final ContainerMap entitys = ContainerMap.create();
	
	public DriveEntity(String name)
	{
		super(EntityType.DRIVE, name);
	}

	@Override
	public String getPath()
	{
		return name;
	}
	
	@Override
	public int getSize()
	{
		return entitys.getContainerSize();
	}
	@Override /* ContainerEntity */
	public FileEntity getFileEntity(String fileName)
	{
		return entitys.get(fileName);
	}

	@Override /* ContainerEntity */
	public void addFileEntity(FileEntity fileEntity) throws PathExistsException
	{
		entitys.addEntity(fileEntity);
	}

	@Override /* ContainerEntity */
	public void removeFileEntity(String fileName) throws PathNotFoundException
	{
		entitys.removeEntity(fileName);
	}

	@Override /* ContainerEntity */
	public void removeAll()
	{
		entitys.clear();
	}
	
	@Override /* ContainerEntity */
	public AbstractEntity getEntity()
	{
		return this;
	}
}
