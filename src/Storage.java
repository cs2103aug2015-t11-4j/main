import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.util.ArrayList;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */

public class Storage {
	private static final String MESSAGE_ADD = "added to %s: \"%s\"";
	private static final String MESSAGE_DELETE = "deleted from %s: \"%s\"";
	private static final String MESSAGE_DISPLAY_EMPTY = "%s is empty.";
	private static final String MESSAGE_READ_ERROR = "unable to read file. check if file exists?";
	private static final String MESSAGE_WRITE_ERROR = "unable to write file. check if read-only?";
	private static final String MESSAGE_UPDATE = "updated!";

	/* 
	 * Temporary placeholder for creation of file - To be replaced with initializeFile
	 */
	private static String filename = "MyCalender.txt";
	
	/*	
	public static ArrayList<String> initializeFile(){
		try {
			PrintWriter outputStream = new PrintWriter("My Calender.txt");
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> contentList = new ArrayList<String>();
		return contentList;
	}

	public static int modify(String action, String content) {
		switch (action){
		case "Add":
			addOneItemToStorage(content);	
		}
		return 0;
	}

	private static void addOneItemToStorage(String content) {
		try {
			PrintWriter outputStream = new PrintWriter("My Calender.txt");
			outputStream.print(content);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	//adds a particular line to the file
	private static void addToFile(String filename, String input) {
		try {
		FileWriter fw = new FileWriter(filename, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(input);
		bw.newLine();
		bw.close();
		
		System.out.println(String.format(MESSAGE_ADD, filename, input));
		} catch (Exception e) {
			System.out.println(MESSAGE_WRITE_ERROR);
		}
	}
	*/	
	
	/*
	 * From Skype with Jiahuan
	 * [1:12:31 AM] addOneItem(content)
	 * [1:12:49 AM] updateOneItem(itemNum, content)
	 * [1:13:04 AM] deleteOneItem(itemNum)
	 * [1:13:09 AM] display()
	 * 
	 * Adds an item to the file
	 */
	public static int addOneItem(String content) {
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(content);
			bw.newLine();
			bw.close();
			
			System.out.println(String.format(MESSAGE_ADD, filename, content));
		} catch (Exception e) {
			System.out.println(MESSAGE_WRITE_ERROR);
		}
		return 0;
	}
	
	public static void updateOneItem(int itemNumber, String content) {
		try {
			File original = new File(filename);
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			File temp = new File("TextBuddy.tmp");
			FileWriter fw = new FileWriter("TextBuddy.tmp", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = "";
			String updatedLine = "";
			
			int lineNumber = 0;
			
			while ((line = br.readLine()) != null) {
				lineNumber += 1;
				if (lineNumber != itemNumber) {
					bw.write(line);
					bw.newLine();
				} else {
					updatedLine = content;
				}
			}
			System.out.println(String.format(MESSAGE_UPDATE, filename, updatedLine));
			br.close();
			bw.close();
			original.delete();
			temp.renameTo(original);
			
		} catch (Exception e) {
			System.out.println(MESSAGE_WRITE_ERROR);
		}
	}
	
	public static void deleteOneItem(int itemNumber) {
		try {
			File original = new File(filename);
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			File temp = new File("TextBuddy.tmp");
			FileWriter fw = new FileWriter("TextBuddy.tmp", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = "";
			String deletedLine = "";
			
			int lineNumber = 0;
			
			while ((line = br.readLine()) != null) {
				lineNumber += 1;
				if (lineNumber != itemNumber) {
					bw.write(line);
					bw.newLine();
				} else {
					deletedLine = line;
				}
			}
			System.out.println(String.format(MESSAGE_DELETE, filename, deletedLine));
			br.close();
			bw.close();
			original.delete();
			temp.renameTo(original);
			
		} catch (Exception e) {
			System.out.println(MESSAGE_WRITE_ERROR);
		}
	}
	
	public static void display() {
		try {
			File file = new File(filename);
			if (file.length() == 0) {
				System.out.println(String.format(MESSAGE_DISPLAY_EMPTY, filename));
			} else {
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				int lineNum = 0;
				while ((line = br.readLine()) != null) {
					lineNum += 1;
					System.out.println(lineNum + ". " + line);
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println(MESSAGE_READ_ERROR);
		}
	}
}