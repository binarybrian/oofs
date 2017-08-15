package oofs;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class DriveEntity extends AbstractEntity
{
	final List <AbstractFileEntity> fileEntities;
	public DriveEntity(String name, Collection <String> paths, Collection <AbstractFileEntity> fileEntities)
	{
		super(EntityType.DRIVE, name, paths);
		
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
