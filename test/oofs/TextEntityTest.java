package oofs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import oofs.entity.AbstractEntity.EntityType;
import oofs.entity.DriveEntity;
import oofs.entity.TextEntity;

public class TextEntityTest
{
	TextEntity textEntity = new TextEntity(
										"aTextFile", 
										ImmutableList.of("home", "user"), 
										new DriveEntity("drive", ImmutableList.of("root"), ImmutableList.of()),
										"12345");
	
	@Test
	public void testName()
	{
		assertNotNull(textEntity);
		assertEquals(textEntity.getName(), "aTextFile");
	}
	
	@Test 
	public void testSize()
	{
		assertEquals(textEntity.getSize(), "12345".length());
	}
	
	@Test
	public void testPath()
	{
		assertEquals(textEntity.getPath(), "home\\user");
	}
	
	@Test
	public void testType()
	{
		assertEquals(textEntity.getType(), EntityType.TEXT);
	}
}
