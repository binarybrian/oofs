package oofs.entity;

import com.google.common.base.Strings;

import oofs.container.ContainerEntity;
import oofs.entity.Entitys.EntityType;

public class TextEntity extends FileEntity
{
	private String content;
	
	public TextEntity(String content, String name, ContainerEntity parentContainer)
	{
		super(EntityType.TEXT, name, parentContainer);
		setContent(content);
	}
	
	public void setContent(String content)
	{
		this.content = Strings.nullToEmpty(content);
	}
	
	public String getContent()
	{
		return content;
	}
	
	@Override
	public int getSize()
	{
		return content.length();
	}
}
