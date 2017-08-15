package oofs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import oofs.entity.Entitys.EntityType;
import oofs.entity.TextEntity;

public class TextEntityTest
{
	final String name = "name";
	final String content = "123456";
	TextEntity textEntity = new TextEntity(content, name, null);
	
	@Test
	public void testName()
	{
		assertNotNull(textEntity);
		assertEquals(textEntity.getName(), name);
	}
	
	@Test 
	public void testSize()
	{
		assertEquals(textEntity.getSize(), content.length());
	}
	
	@Test
	public void testType()
	{
		assertEquals(textEntity.getType(), EntityType.TEXT);
	}
}
