package oofs.entity;

import static oofs.entity.Entitys.PATH_JOINER;

import oofs.container.ContainerEntity;
import oofs.entity.Entitys.EntityType;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

/* Base class for anything that can be a FileEntity
 * Currently this is @link{TextFileEntity} and @link{ZipFileEntity}
 */
public abstract class FileEntity extends AbstractEntity
{
	private ContainerEntity parentContainer;
	
	public FileEntity(EntityType type, String name, ContainerEntity parentContainer)
	{
		super(type, name);
		this.parentContainer = parentContainer;
	}

	public AbstractEntity getParent()
	{
		return parentContainer.getEntity();
	}

	public ContainerEntity getParentContainer()
	{
		return parentContainer;
	}
	
	@Override
	public String getPath()
	{
		return PATH_JOINER.join(getParent().getPath(), getName());
	}
	
	public void setParent(ContainerEntity containerEntity)
	{
		try
		{
			this.parentContainer.removeFileEntity(getName());
			this.parentContainer = containerEntity;
			this.parentContainer.addFileEntity(this);
		}
		catch (PathNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (PathExistsException e)
		{
			e.printStackTrace();
		}
	}
}
