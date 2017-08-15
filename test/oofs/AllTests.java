package oofs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import oofs.DriveEntityTest;
import oofs.MoveOpTest;
import oofs.TextEntityTest;
import oofs.WriteOpTest;
import oofs.ZipEntityTest;

@RunWith(Suite.class)
@SuiteClasses({
	DriveEntityTest.class,
	FolderEntityTest.class, 
	TextEntityTest.class, 
	ZipEntityTest.class,
	MoveOpTest.class, 
	WriteOpTest.class
})

public class AllTests {}
