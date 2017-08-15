package oofs.entity;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * This class contains static utility methods that operate on or
 * return file system objects extending from {@code AbstractEntity}.
 * 
 * The primary operations include:
 * "create", "delete", "move" and "writeToFile"
 **/
public class Entitys
{
	public static enum EntityType {DRIVE, FOLDER, TEXT, ZIP}
	
	public static final char SEP = '\\'; //TODO(bmerrill): Rename to PATH_SEP
	public static final Joiner PATH_JOINER = Joiner.on(SEP); 
	public static final Splitter PATH_SPLITTER = Splitter.on(SEP);
	

	
	private Entitys(){}
}
