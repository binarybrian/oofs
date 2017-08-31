package oofs;

import static oofs.entity.Entitys.PATH_SEP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;
import oofs.entity.ZipEntity;
import oofs.exception.PathNotFoundException;

public class ZipEntityTest extends EntityTest
{
	FolderEntity folderEntity;
	ZipEntity zipEntity;
	TextEntity textEntity;
	
	String folderName = "files";
	String zipName = "name.zip";
	String textName = "name.txt";
	String textContent = "123456";
	
	@Before @Override
	public void setup()
	{
		super.setup();
		try
		{
			folderEntity = (FolderEntity)Entitys.createFileEntity(EntityType.FOLDER, folderName, driveEntity);
			zipEntity = (ZipEntity)Entitys.create(EntityType.ZIP, zipName, driveEntity.getPath());
			textEntity = Entitys.createTextEntity(textContent, textName, folderEntity);
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
	
	@Test(expected = PathNotFoundException.class)
	public void PathNotFoundException() throws Exception 
	{
	    Entitys.create(EntityType.ZIP, "aZipFile", "bad_parent");
	}
	
	@Test
	public void testNotNull()
	{
		assertNotNull(driveEntity);
		assertNotNull(folderEntity);
		assertNotNull(zipEntity);
		assertNotNull(textEntity);
	}
	
	@Override
	public void testAttributes()
	{
		assertEquals(zipEntity.getName(), zipName);
		assertEquals(zipEntity.getType(), EntityType.ZIP);
	}

	@Override
	public void testPath()
	{
		assertEquals(zipEntity.getPath(), DRIVE_NAME + PATH_SEP + zipEntity.getName());
	}

	@Override
	public void testSize()
	{
		assertEquals(0, zipEntity.getSize());
	}
	
	@Test /* Compress a file, i.e. move it from a folder container to a zip container. */
	public void testZipMove() throws Exception
	{
		assertEquals(textContent.length(), folderEntity.getSize());
		
		Entitys.move(textEntity.getPath(), zipEntity.getPath()); 
		
		assertEquals("Folder should be empty", 0, folderEntity.getSize());
		assertEquals(textContent.length()/2, zipEntity.getSize());
	}
}
