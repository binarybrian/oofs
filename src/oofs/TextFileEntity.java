package oofs;

import java.util.Collection;

import com.google.common.base.Strings;

public class TextFileEntity extends AbstractFileEntity
{
	final String content;
	
	public TextFileEntity(String name, Collection <String> paths, AbstractEntity parent, String content)
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
