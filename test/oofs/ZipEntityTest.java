package oofs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import oofs.entity.TextEntity;
import oofs.entity.ZipEntity;
import oofs.exception.PathExistsException;

public class ZipEntityTest
{
	final String textName = "text";
	final String content = "123456";
	final TextEntity textEntity = new TextEntity(content, textName, null);
	
	final String zipName = "zip";
	final ZipEntity zipEntity = new ZipEntity(zipName, null);
	
	public ZipEntityTest() throws PathExistsException
	{
		zipEntity.addFileEntity(textEntity);
	}
	
	@Test
	public void testZipSize()
	{
		assertEquals(3, zipEntity.getSize());
	}
}
