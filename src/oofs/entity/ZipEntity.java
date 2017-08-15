package oofs.entity;

import oofs.container.ContainerEntity;
import oofs.entity.Entitys.EntityType;

public class ZipEntity extends FolderEntity
{
	static final int COMPRESS_RATIO = 2;

	public ZipEntity(String name, ContainerEntity parentContainer)
	{
		super(EntityType.ZIP, name, parentContainer);
	}
	
	@Override
	public int getSize()
	{
		return super.getSize()/COMPRESS_RATIO;
	}

}
