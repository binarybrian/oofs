package oofs;

import static oofs.EntityTest.DRIVE_NAME;
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

public class WriteOpTest
{
	DriveEntity driveEntity;
	FolderEntity folderEntity;
	TextEntity text1;
	
	@Before
	public void setup()
	{
		try
		{
			driveEntity = (DriveEntity)Entitys.create(EntityType.DRIVE, DRIVE_NAME, "");
			folderEntity = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folderEntity", driveEntity);		
			text1 = Entitys.createTextEntity("text1", "text1.txt", folderEntity);
	
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
		assertNotNull(folderEntity);
		assertNotNull(text1);
	}
	
	@Test
	public void testWrite() throws Exception
	{
		assertEquals(text1.getContent(), "text1");
		assertEquals(text1.getSize(), driveEntity.getSize());
		
		String newContent = "The rain in Spain falls mostly on the plains";
		
		Entitys.writeToFile(text1.getPath(), newContent);
		
		assertEquals(text1.getContent(), newContent);
		assertEquals(driveEntity.getSize(), newContent.length());
	}
}
