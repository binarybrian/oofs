package oofs;

import oofs.entity.DriveEntity;
import oofs.entity.Entitys;
import oofs.entity.Entitys.EntityType;
import oofs.entity.FolderEntity;
import oofs.entity.TextEntity;
import oofs.entity.ZipEntity;

/** 
 * This is a basic "proof-of-concept" demo of an OO file system implementation. 
 * The operations shown here are fairly trivial.  More robust tests are 
 * implemented as JUnit tests.  Please refer to and run the test suite
 * located in {@link oofs.AllTests}.  
 **/
public class Demo
{
	public static void main(String[] args)
	{
		System.out.println("File System is empty.\n");
		
		try
		{
			DriveEntity rootDrive = (DriveEntity)Entitys.create(EntityType.DRIVE, "root", "");
			System.out.println("Adding " + rootDrive.getName() + " " + rootDrive.getType());
			
			FolderEntity docFolder = (FolderEntity)Entitys.create(EntityType.FOLDER, "docs", rootDrive.getPath());
			System.out.println("Adding " + docFolder.getName() + " " + docFolder.getType());
			
			TextEntity infoText = (TextEntity)Entitys.create(EntityType.TEXT, "info.txt", docFolder.getPath());
			System.out.println("Adding " + infoText.getName() + " " + infoText.getType());
			
			ZipEntity infoZip = (ZipEntity)Entitys.create(EntityType.ZIP, "info.zip", docFolder.getPath());
			System.out.println("Adding " + infoZip.getName() + " " + infoZip.getType());
			
			System.out.println();
			System.out.println(infoText.getPath());
			System.out.println(infoZip.getPath());
			
			System.out.println();
			
			String infoData = "0123456789";
			System.out.println("Writing " + infoData + " to " + infoText.getName());
			Entitys.writeToFile(infoText.getPath(), infoData);
			System.out.println("Size of " + infoText.getName() + " is " + infoText.getSize());
			
			System.out.println();
			
			System.out.println("Zipping " + infoText.getName());
			Entitys.move(infoText.getPath(), infoZip.getPath());
			System.out.println("Size of " + infoZip.getName() + " is " + infoZip.getSize());
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
