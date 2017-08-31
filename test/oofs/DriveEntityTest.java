package oofs;

import static oofs.SystemEntity.system;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import oofs.entity.AbstractEntity;
import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.exception.PathExistsException;

public class DriveEntityTest extends EntityTest
{
	FolderEntity folderEntity = null;
	
	@Before
	public void setup()
	{	
		super.setup();
		try
		{
			AbstractEntity entity = Entitys.create(EntityType.FOLDER, "docs", driveEntity.getPath());
			if (entity instanceof FolderEntity)
			{
				folderEntity = (FolderEntity)entity;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test(expected = PathExistsException.class)
	public void testDuplicateDrive() throws PathExistsException
	{
		system().createDriveEntity(DRIVE_NAME); /* Two drives cannot have the same name. */
	}
	
	@Test
	public void testFolderNotNull()
	{
		assertNotNull(folderEntity);
	}
	
	@Override
	public void testAttributes()
	{
		assertEquals(driveEntity.getName(), DRIVE_NAME);
		assertEquals(driveEntity.getType(), EntityType.DRIVE);
	}

	@Override
	public void testPath()
	{
		assertEquals(driveEntity.getPath(), DRIVE_NAME);
	}

	@Override
	public void testSize() throws Exception
	{
		assertEquals("\\home\\docs. Drive size should be 0.", 
							0, driveEntity.getSize());
			
		Entitys.createTextEntity("hello", "hello.txt", folderEntity);
		assertEquals("\\home\\docs\\hello.txt. Drive size should be 5.", 
							5, driveEntity.getSize());
			
		Entitys.createTextEntity("aaa", "aaa.txt", driveEntity);
		assertEquals("\\home\\docs\\hello.txt\n\\home\\aaa.txt. Drive size should be 8.",
							8, driveEntity.getSize());
			
		Entitys.delete(folderEntity.getPath());
		
		assertEquals("Folder is deleted.  Remaining: \\home\\aaa.txt. Drive size should be 3.", 
							3, driveEntity.getSize());
	}
}
