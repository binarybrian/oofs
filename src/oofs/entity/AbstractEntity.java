package oofs.entity;

import oofs.entity.Entitys.EntityType;

public abstract class AbstractEntity
{
	final EntityType type;
	final String name;
	
	public abstract int getSize();
	public abstract String getPath();
	
	public AbstractEntity(EntityType type, String name)
	{
		this.type = type;
		this.name = name;
	}

	public EntityType getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
}
