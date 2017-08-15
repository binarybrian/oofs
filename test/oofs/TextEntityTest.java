package oofs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TextEntityTest
{
	TextFileEntity textEntity = new TextFileEntity(
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
}
