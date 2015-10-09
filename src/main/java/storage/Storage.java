package main.java.storage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import main.java.resources.Task;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */

public class Storage {
	/* 
	 * Temporary placeholder for creation of file - To be replaced with initializeFile
	 */
	private static String filename = "MyCalender.txt";
	
	public static int addOneItem(Task task) {
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(task.getTaskDescription());
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	public static int updateOneItem(int itemNumber, Task task) {
		try {
			FileWriter fw = new FileWriter(filename, true);
			FileReader fr = new FileReader(filename);
			
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(fr);
			
			int lineNumber = 0;
						
			while ((br.readLine()) != null) {
				lineNumber += 1;
				if (lineNumber == itemNumber) {
					bw.write(task.getTaskDescription());
					bw.newLine();
				}
			}
			
			br.close();
			bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	public static int deleteOneItem(int itemNumber) {
		try {
			File original = new File(filename);
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			File temp = new File("Alt4.tmp");
			FileWriter fw = new FileWriter("Alt4.tmp", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int lineNumber = 0;
			String line = "";
			
			while ((line = br.readLine()) != null) {
				lineNumber += 1;
				if (lineNumber == itemNumber) {
					bw.write(line);
					bw.newLine();
				}
			}
			
			br.close();
			bw.close();
			original.delete();
			temp.renameTo(original);
			
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static String getTaskTypeByItemNum(int itemNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void display() {
		// TODO Auto-generated method stub
		// May not be required as told by Jiahuan
	}
}