package oofs;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

public class ZipFileEntity extends AbstractFileEntity
{
	static final int COMPRESS_RATIO = 2;
	
	private final Collection <AbstractFileEntity> fileEntities;
	
	public ZipFileEntity(EntityType type, String name, Collection<String> paths, AbstractEntity parent, Collection <AbstractFileEntity> fileEntities)
	{
		super(type, name, paths, parent);
		this.fileEntities = ImmutableList.copyOf(fileEntities);
	}

	public Collection <AbstractFileEntity> getFileEntities()
	{
		return fileEntities;
	}
	
	@Override
	public int getSize()
	{
		return fileEntities.stream().mapToInt(f -> f.getSize()).sum() / COMPRESS_RATIO;
	}

}
