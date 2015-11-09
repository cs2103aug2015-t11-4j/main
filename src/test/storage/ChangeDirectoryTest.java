/*
 * @@author A0126058
 */

package test.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.storage.Storage;

public class ChangeDirectoryTest {
	
	public static String defaultDirectory;
	public static String newDirectory;
	public int result = -1;
	
	Storage storage = Storage.getInstance();
	
	@Test
	public void test1() {
		defaultDirectory = "default";
		result = storage.changeDirectory(defaultDirectory);
		assertEquals(0, result);
	}
	
	@Test
	public void test2() {
		defaultDirectory = "/";
		result = storage.changeDirectory(defaultDirectory);
		assertEquals(0, result);
	}
	
	@Test
	public void test3() {
		defaultDirectory = "\\";
		result = storage.changeDirectory(defaultDirectory);
		assertEquals(0, result);
	}
	
	@Test
	public void test4() {
		newDirectory = "C:" + "\\" + "Users" + "\\" + "UX305" + "\\" + "Desktop";
		result = storage.changeDirectory(newDirectory);
		assertEquals(0, result);
	}
	
}