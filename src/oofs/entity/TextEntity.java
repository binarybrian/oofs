package oofs.entity;

import java.util.Collection;

import com.google.common.base.Strings;

public class TextEntity extends FileEntity
{
	final String content;
	
	public TextEntity(String name, Collection <String> paths, AbstractEntity parent, String content)
	{
		super(EntityType.TEXT, name, paths, parent);
		this.content = Strings.nullToEmpty(content);
	}
	
	@Override
	public int getSize()
	{
		return content.length();
	}

}
