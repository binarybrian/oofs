package oofs;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oofs.entity.AbstractEntity;
import oofs.entity.DriveEntity;
import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;

public abstract class EntityTest
{
	static final String DRIVE_NAME = "home";
	
	DriveEntity driveEntity;

	@Before
	public void setup()
	{
		try
		{
			AbstractEntity entity = Entitys.create(EntityType.DRIVE, DRIVE_NAME, "");
			if (entity instanceof DriveEntity)
			{
				driveEntity = ((DriveEntity)entity);
			}
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
	public void testDriveNotNull()
	{
		assertNotNull(driveEntity);
	}
	
	@Test
	public abstract void testAttributes();
	@Test
	public abstract void testPath();
	@Test
	public abstract void testSize();
}
