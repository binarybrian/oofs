package oofs.entity;

import java.util.Collection;

/* Base class for anything that can be a FileEntity
 * Currently this is @link{TextFileEntity} and @link{ZipFileEntity}
 */
public abstract class FileEntity extends AbstractEntity
{
	final AbstractEntity parent;
	
	public FileEntity(EntityType type, String name, Collection<String> paths, AbstractEntity parent)
	{
		super(type, name, paths);
		this.parent = parent;
	}

	public AbstractEntity getParent()
	{
		return parent;
	}

}
