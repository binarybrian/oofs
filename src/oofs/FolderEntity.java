package oofs;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class FolderEntity extends AbstractFileEntity
{
	Collection <AbstractFileEntity> fileEntities;
	
	public FolderEntity(String name, Collection<String> paths, AbstractEntity parent, Collection <AbstractFileEntity> fileEntities)
	{
		super(EntityType.FOLDER, name, paths, parent);
		this.fileEntities = ImmutableList.copyOf(fileEntities); 
	}

	@Override
	public int getSize()
	{
		return fileEntities
				.stream()
				.mapToInt(f -> f.getSize()).sum();
	}

}
