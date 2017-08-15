package oofs.entity;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import oofs.container.ContainerEntity;

public class DriveEntity extends AbstractEntity implements ContainerEntity
{
	final List <FileEntity> fileEntities;
	public DriveEntity(String name, Collection <String> paths, Collection <FileEntity> fileEntities)
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

	@Override
	public FileEntity getFileEntity(String fileName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFileEntity(FileEntity fileEntity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFileEntity(FileEntity fileName)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractEntity getEntity()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
