package oofs.entity;

import static oofs.SystemEntity.system;

import java.util.Optional;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import oofs.container.ContainerEntity;
import oofs.exception.IllegalFileSystemOperation;
import oofs.exception.PathExistsException;
import oofs.exception.PathNotFoundException;

/**
 * This class contains static utility methods that operate on or
 * return file system objects extending from {@code AbstractEntity}.
 * 
 * The primary operations include:
 * "create", "delete", "move" and "writeToFile"
 **/
public class Entitys
{
	public static enum EntityType {DRIVE, FOLDER, TEXT, ZIP}
	
	public static final char PATH_SEP = '\\';
	public static final Joiner PATH_JOINER = Joiner.on(PATH_SEP); 
	public static final Splitter PATH_SPLITTER = Splitter.on(PATH_SEP);
	
	public static AbstractEntity create(EntityType entityType, String name, String parentPath) throws Exception
	{
		boolean isPathEmpty = isPathEmpty(parentPath);
		if (isPathEmpty)
		{
			if (entityType == EntityType.DRIVE)
			{
				return system().createDriveEntity(name);
			}
			throw new PathNotFoundException("Parent path cannot be empty.");
		}
		
		Iterable <String> pathTokens = PATH_SPLITTER.split(parentPath);
		
		Optional <ContainerEntity> containerEntityOptional = system().findContainerEntity(pathTokens);
		if (!containerEntityOptional.isPresent())
		{
			throw new PathNotFoundException("Parent path not found in system");
		}
		
		ContainerEntity containerEntity = containerEntityOptional.get();
		FileEntity fileEntity = createFileEntity(entityType, name, containerEntity.getEntity());
		
		return fileEntity;
	}
	
	public static void delete(String path) throws Exception
	{
		if (isPathEmpty(path))
		{
			throw new Exception("Path not found: Empty Path");
		}
		
		Iterable <String> pathTokens = PATH_SPLITTER.split(path);
		Optional <AbstractEntity> entityOptional = system().findEntity(pathTokens);
		if (!entityOptional.isPresent())
		{
			throw new Exception("Path not found: " + path);
		}
		
		AbstractEntity entity = entityOptional.get();
		switch(entity.getType())
		{
		case FOLDER:
		case ZIP:
			deleteContainerEntity(entity);
			deleteFileEntity(entity);
			break;
		case DRIVE:
			deleteContainerEntity(entity);
			system().deleteDrive(entity.getName());
			break;
		case TEXT:
			deleteFileEntity(entity);
			break;
		default:
			throw new Exception("Unhandled Type: " + entity.getType());
		}
	}
	
	public static void move(String sourcePath, String destPath) throws Exception
	{
		if (isPathEmpty(sourcePath))
		{
			throw new Exception("Path not found: Source path cannot be empty");
		}
		
		if (isPathEmpty(destPath))
		{
			throw new Exception("Path not found: Destination path cannot be empty");
		}
		
		Iterable <String> sourceTokens = PATH_SPLITTER.split(sourcePath);
		Optional <AbstractEntity> entityOptional = system().findEntity(sourceTokens);
		if (!entityOptional.isPresent())
		{
			throw new Exception("Source path not found: " + sourcePath);
		}
		
		AbstractEntity entity = entityOptional.get();
		if (entity.getType() == EntityType.DRIVE)
		{
			throw new IllegalFileSystemOperation("Cannot move a " + EntityType.DRIVE);
		}
		
		FileEntity fileEntity = (FileEntity)entity;
		
		Iterable <String> destTokens = PATH_SPLITTER.split(destPath);
		Optional <ContainerEntity> destContainerOptional = system().findContainerEntity(destTokens);
		if (!destContainerOptional.isPresent())
		{
			throw new Exception("Destination path not found: " + destPath);
		}
		
		ContainerEntity destContainer = destContainerOptional.get();
		fileEntity.setParent(destContainer);
	}
	
	public static void writeToFile(String path, String content) throws PathNotFoundException
	{
		if (isPathEmpty(path))
		{
			throw new PathNotFoundException("Path is empty.");
		}
		
		Iterable <String> pathTokens = PATH_SPLITTER.split(path);
		Optional <AbstractEntity> entityOptional = system().findEntity(pathTokens);
		if (!entityOptional.isPresent())
		{
			throw new PathNotFoundException("Path not found: " + path);
		}
		
		AbstractEntity entity = entityOptional.get();
		if (entity.getType() != EntityType.TEXT)
		{
			throw new UnsupportedOperationException("Not a text file.");
		}
		
		if (!(entity instanceof TextEntity))
		{
			throw new UnsupportedOperationException("Not a text file.");
		}
		
		TextEntity textEntity = (TextEntity)entity;
		textEntity.setContent(content);
	}
	
	public static FileEntity createFileEntity(EntityType entityType, String name, AbstractEntity parentEntity) throws IllegalFileSystemOperation, PathExistsException
	{
		if (parentEntity == null)
		{
			throw new IllegalFileSystemOperation(entityType + " cannot have a null container entity");
		}
		
		if (!(parentEntity instanceof ContainerEntity))
		{
			throw new IllegalFileSystemOperation(entityType + " must must be contained by another entity but parent entity is of type " + parentEntity.getType() + ".  Expected " + ContainerEntity.class.getSimpleName());
		}
		
		ContainerEntity containerEntity = (ContainerEntity)parentEntity;
		final FileEntity fileEntity;
		switch(entityType)
		{
		case DRIVE:
			throw new IllegalFileSystemOperation(entityType + " cannot be be contained in another entity.");
		case FOLDER: default:
			fileEntity = new FolderEntity(name, containerEntity);
			break;
		case ZIP:
			fileEntity = new ZipEntity(name, containerEntity);
			break;
		case TEXT:
			fileEntity = new TextEntity("", name, containerEntity);
			break;
		}
		
		containerEntity.addFileEntity(fileEntity);
		return fileEntity;
	}
	
	public static TextEntity createTextEntity(String content, String name, ContainerEntity containerEntity) throws PathExistsException
	{
		TextEntity textEntity = new TextEntity(content, name, containerEntity);
		containerEntity.addFileEntity(textEntity);
		return textEntity;
	}
	
	public static DriveEntity createDriveEntity(String name)
	{
		return new DriveEntity(name);
	}
	
	private static void deleteFileEntity(AbstractEntity entity) throws Exception
	{
		if (!(entity instanceof FileEntity))
		{
			throw new Exception("In deleteContainerEntity.  Expected Type " + FileEntity.class.getSimpleName());
		}
		
		FileEntity fileEntity = (FileEntity)entity;
		fileEntity.getParentContainer().removeFileEntity(fileEntity.getName());
	}

	private static void deleteContainerEntity(AbstractEntity entity) throws Exception
	{
		if (!(entity instanceof ContainerEntity))
		{
			throw new Exception("In deleteContainerEntity.  Expected Type " + ContainerEntity.class.getSimpleName());
		}
		
		ContainerEntity containerEntity = (ContainerEntity)entity;
		containerEntity.removeAll();
	}
	
	private static boolean isPathEmpty(String path)
	{
		return Strings.nullToEmpty(path).trim().isEmpty();
	}
	
	private Entitys(){}
}
