package oofs.container;

import oofs.entity.AbstractEntity;
import oofs.entity.FileEntity;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

public interface ContainerEntity
{
	public FileEntity getFileEntity(String fileName);
	
	public void addFileEntity(FileEntity fileEntity) throws PathExistsException;
	public void removeFileEntity(String fileName) throws PathNotFoundException;
	
	//public void removeAll();
	public void clear();
	
	public AbstractEntity getEntity();
}
