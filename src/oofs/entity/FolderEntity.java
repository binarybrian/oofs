package oofs.entity;

import java.util.Collection;

import oofs.container.ContainerEntity;

public class FolderEntity extends FileEntity implements ContainerEntity
{
	Collection <FileEntity> fileEntities;
	
	public FolderEntity(String name, Collection<String> paths, AbstractEntity parent, Collection <FileEntity> fileEntities)
	{ 
		this(EntityType.FOLDER, name, paths, parent, fileEntities);
	}

	public FolderEntity(EntityType entityType, String name, Collection<String> paths, AbstractEntity parent, Collection <FileEntity> fileEntities)
	{
		super(entityType, name, paths, parent);
		this.fileEntities = fileEntities;
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
