package oofs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import oofs.entity.DriveEntity;
import oofs.entity.TextEntity;
import oofs.entity.ZipEntity;

public class ZipEntityTest
{
//	ZipFileEntity textEntity = new ZipFileEntity(
//			"aTextFile", 
//			ImmutableList.of("home", "user"), 
//			new DriveEntity("drive", ImmutableList.of("root"), ImmutableList.of()),
//			"12345");
	
	
	TextEntity textEntity = new TextEntity(
			"aTextFile", 
			ImmutableList.of("home", "user"), 
			new DriveEntity("drive", ImmutableList.of("root"), ImmutableList.of()),
			"123456");
	
	ZipEntity zipEntity = new ZipEntity(
			"zip", 
			ImmutableList.of("home", "user"),
			new DriveEntity("drive", ImmutableList.of("root"), ImmutableList.of()),
			ImmutableList.of(textEntity));
	
	@Test
	public void testZipSize()
	{
		assertEquals(3, zipEntity.getSize());
	}
}
