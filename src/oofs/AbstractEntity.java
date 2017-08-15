package oofs;

import java.util.Collection;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public abstract class AbstractEntity
{
	static enum EntityType {DRIVE, FOLDER, TEXT, ZIP}
	
	static final Joiner PATH_JOINER = Joiner.on('\\');
	
	final EntityType type;
	final String name;
	final Collection <String> paths;
	
	public abstract int getSize();
	
	public AbstractEntity(EntityType type, String name, Collection <String> paths)
	{
		this.type = type;
		this.name = name;
		this.paths = ImmutableList.copyOf(paths);
	}

	public String getName()
	{
		return name;
	}

	public String getPath()
	{
		return PATH_JOINER.join(paths);
	}
}
