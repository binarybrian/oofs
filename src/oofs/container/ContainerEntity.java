package oofs.container;

import oofs.entity.AbstractEntity;
import oofs.entity.FileEntity;

public interface ContainerEntity
{
	public FileEntity getFileEntity(String fileName);
	
	public void addFileEntity(FileEntity fileEntity);
	public void removeFileEntity(FileEntity fileName);
	
	public AbstractEntity getEntity();
}
