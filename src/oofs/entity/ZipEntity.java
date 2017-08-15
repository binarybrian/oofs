package oofs.entity;

import java.util.Collection;

public class ZipEntity extends FolderEntity
{
	static final int COMPRESS_RATIO = 2;

	public ZipEntity(String name, Collection<String> paths, AbstractEntity parent, Collection <FileEntity> fileEntities)
	{
		super(EntityType.ZIP, name, paths, parent, fileEntities);
	}

	public Collection <FileEntity> getFileEntities()
	{
		return fileEntities;
	}
	
	@Override
	public int getSize()
	{
		return fileEntities.stream().mapToInt(f -> f.getSize()).sum() / COMPRESS_RATIO;
	}

}
