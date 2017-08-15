package oofs;

import static oofs.entity.Entitys.PATH_SEP;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;

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
	
	@Override
	public void testAttributes()
	{
		assertEquals(folder.getName(), "folder");
		assertEquals(folder.getType(), EntityType.FOLDER);
	}

	@Override
	public void testPath()
	{
		assertEquals(folder.getPath(), DRIVE_NAME + PATH_SEP + folder.getPath());
	}

	@Override
	public void testSize()
	{
		assertEquals(0, folder.getSize());
		
		try
		{
			Entitys.move(text.getPath(), folder.getPath());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		assertEquals(text.getSize(), folder.getSize());
	}
}
