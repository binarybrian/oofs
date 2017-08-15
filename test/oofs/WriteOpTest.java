package oofs;

import static oofs.EntityTest.DRIVE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oofs.entity.DriveEntity;
import oofs.entity.Entitys;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;
import oofs.entity.Entitys.EntityType;

public class WriteOpTest
{
	DriveEntity driveEntity;
	FolderEntity folderA;
	TextEntity text1;
	
	@Before
	public void setup()
	{
		try
		{
			driveEntity = (DriveEntity)Entitys.create(EntityType.DRIVE, DRIVE_NAME, "");
			folderA = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, "folderA", driveEntity);		
			text1 = Entitys.createTextEntity("text1", "text1.txt", folderA);
	
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
		assertNotNull(text1);
	}
	
	@Test
	public void testWrite()
	{
		assertEquals(text1.getContent(), "text1");
		assertEquals(text1.getSize(), driveEntity.getSize());
		
		String newContent = "The rain in Spain falls mostly on the plains";
		try
		{
			Entitys.writeToFile(text1.getPath(), newContent);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		assertEquals(text1.getContent(), newContent);
		assertEquals(driveEntity.getSize(), newContent.length());
	}
}
