package oofs;

import java.util.Collection;

/* Base class for anything that can be a FileEntity
 * Currently this is @link{TextFileEntity} and @link{ZipFileEntity}
 */
public abstract class AbstractFileEntity extends AbstractEntity
{
	final AbstractEntity parent;
	
	public AbstractFileEntity(EntityType type, String name, Collection<String> paths, AbstractEntity parent)
	{
		super(type, name, paths);
		this.parent = parent;
	}

	public AbstractEntity getParent()
	{
		return parent;
	}

}
