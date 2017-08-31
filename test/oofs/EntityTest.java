package oofs;

import static oofs.SystemEntity.system;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import oofs.entity.DriveEntity;

public abstract class EntityTest
{
	static final String DRIVE_NAME = "home";
	
	DriveEntity driveEntity;

	@Before
	public void setup()
	{
		try
		{
			driveEntity = system().createDriveEntity(DRIVE_NAME);
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
	public abstract void testSize() throws Exception;
}
