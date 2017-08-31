package oofs;

import static oofs.EntityTest.DRIVE_NAME;
import static oofs.entity.Entitys.PATH_SEP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oofs.entity.DriveEntity;
import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;

public class MoveOpTest
{
	DriveEntity driveEntity;
	FolderEntity folderA;
	FolderEntity folderB;
	
	TextEntity text1;
	TextEntity text2;

	@Before
	public void setup()
	{
		try
		{
			driveEntity = (DriveEntity)Entitys.create(EntityType.DRIVE, DRIVE_NAME, "");
			folderA = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folderA", driveEntity);
			folderB = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folderB", driveEntity);
			
			/* Path of text1 is "home/text1.txt" */
			text1 = Entitys.createTextEntity("text1", "text1.txt", driveEntity);
			
			/* Path of text2 is "home/folderB/text2.txt" */
			text2 = Entitys.createTextEntity("text2", "text2.txt", folderB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@After
	public void teardown()
	{
		SystemEntity.system().clear();
	}
	
	@Test
	public void testNotNull()
	{
		assertNotNull(driveEntity);
		assertNotNull(folderA);
		assertNotNull(folderB);
		assertNotNull(text1);
		assertNotNull(text2);
	}

	@Test
	public void testTextFileMove() throws Exception
	{
		/* Folder A is empty, Folder B contains text2 */
		assertEquals(text2.getPath(), DRIVE_NAME + PATH_SEP + folderB.getName() + PATH_SEP + text2.getName());
		assertEquals(folderA.getPath(), DRIVE_NAME + PATH_SEP + folderA.getName());
		
		assertEquals(0, folderA.getSize());
		assertEquals(text2.getSize(), folderB.getSize());

		Entitys.move(text2.getPath(), folderA.getPath());
		
		/* Folder A contains text2.  Folder B is empty */
		assertEquals(text2.getPath(), DRIVE_NAME + PATH_SEP + folderA.getName() + PATH_SEP + text2.getName());
		assertEquals(text2.getSize(), folderA.getSize());
		assertEquals(0, folderB.getSize());
		
		Entitys.move(text1.getPath(), folderB.getPath());
		
		/* Folder B contains text1 */
		assertEquals(text1.getSize(), folderB.getSize());
		
		Entitys.move(folderB.getPath(), folderA.getPath());
		
		/* Folder A contains text2 and Folder B 
		 * text1 has a path of "home\folderA\folderB\text1.txt" */
		assertEquals(text1.getPath(), DRIVE_NAME + PATH_SEP + folderA.getName() + PATH_SEP + folderB.getName() + PATH_SEP + text1.getName());
		assertEquals(folderA.getSize(), (text1.getSize() + text2.getSize()));
	}
	
	@Test
	public void testMoveParentFolder() throws Exception
	{
		/* Move "folderB" into "folderA" */
		Entitys.move(folderB.getPath(), folderA.getPath());
		assertEquals(text2.getPath(), DRIVE_NAME + PATH_SEP + folderA.getName() + PATH_SEP + folderB.getName() + PATH_SEP + text2.getName());
			
		/* Move "folderB" from "folderA" to "home" drive */
		Entitys.move(folderB.getPath(), driveEntity.getPath());
		assertEquals(folderB.getPath(), DRIVE_NAME + PATH_SEP + folderB.getName());
			
			/* text2 should still have folderB as a parent, i.e. "home/folderB/text2.txt" */
		assertEquals(text2.getPath(), DRIVE_NAME + PATH_SEP + folderB.getName() + PATH_SEP + text2.getName());
	}
}
