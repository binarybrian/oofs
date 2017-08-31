package oofs;

import static oofs.entity.Entitys.PATH_SEP;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;
import oofs.exception.IllegalFileSystemOperation;
import oofs.exception.PathExistsException;

public class FolderEntityTest extends EntityTest
{
	FolderEntity folder;
	TextEntity text;
	
	@Before @Override
	public void setup()
	{
		super.setup();
		try
		{
			folder = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folder", driveEntity);
			text = Entitys.createTextEntity("12345", "text.txt", driveEntity);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(expected = IllegalFileSystemOperation.class) 
	public void testDriveIsNotChild() throws Exception
	{
		/* DRIVE cannot be contained in another Entity */
		Entitys.create(EntityType.DRIVE, "AnotherDrive", folder.getPath());
	}
	
	@Test(expected = IllegalFileSystemOperation.class)
	public void testFolderHasParent() throws IllegalFileSystemOperation, PathExistsException
	{
		/* Non-DRIVE must be contained in another entity */
		Entitys.createFileEntity(EntityType.FOLDER, "AnotherFolder", null);
	}
	
	@Override
	public void testAttributes()
	{
		assertEquals(folder.getName(), "folder");
		assertEquals(folder.getType(), EntityType.FOLDER);
	}

	@Override
	public void testPath()
	{
		assertEquals(folder.getPath(), DRIVE_NAME + PATH_SEP + folder.getName());
	}

	@Override
	public void testSize() throws Exception
	{
		assertEquals(0, folder.getSize());
		
		Entitys.move(text.getPath(), folder.getPath());
		
		assertEquals(text.getSize(), folder.getSize());
	}
	
	@Test
	public void testDelete() throws Exception
	{
		//folder = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folder", driveEntity);
		FolderEntity folderA = Entitys.createFolder("a", folder);
		FolderEntity folderB = Entitys.createFolder("b", folderA);
		FolderEntity folderC = Entitys.createFolder("c", folderB);
		
		/* folderC should contain textEntity and should have a size of 3 */
		TextEntity textEntity = Entitys.createTextEntity("abc", "abc", folderC);
		assertEquals("abc".length(), textEntity.getSize());
		assertEquals("abc".length(), folderA.getSize());
		
		
		Entitys.move(text.getPath(), folder.getPath());
		assertEquals("12345".length() + "abc".length(), folder.getSize());
		
		folder.clear();
		assertEquals(0, folder.getSize());
		assertEquals(0, folderC.getSize());
		assertEquals(0, driveEntity.getSize());
	}
}
