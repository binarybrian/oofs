package oofs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import oofs.entity.Entitys.EntityType;
import oofs.exception.PathExistsException;
import oofs.entity.Entitys;
import oofs.entity.TextEntity;

public class TextEntityTest extends EntityTest
{
	TextEntity textEntity;
	
	@Before
	public void setup()
	{
		super.setup();
		try
		{
			textEntity = Entitys.createTextEntity("Hello World", "hello.txt", driveEntity);
		}
		catch (PathExistsException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNotNull()
	{
		assertNotNull(textEntity);
	}
	
	@Test
	public void testAttributes()
	{
		assertEquals(textEntity.getName(), "hello.txt");
		assertEquals(textEntity.getContent(), "Hello World");
		assertEquals(textEntity.getType(), EntityType.TEXT);
		assertEquals(textEntity.getParent(), driveEntity);
	}
	
	@Test
	public void testPath()
	{
		assertEquals(textEntity.getPath(), DRIVE_NAME + Entitys.PATH_SEP + "hello.txt");
	}
	
	@Test
	public void testSize()
	{
		assertEquals(textEntity.getSize(), "Hello World".length());
	}
}
