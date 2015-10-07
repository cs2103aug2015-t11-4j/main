import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.util.ArrayList;

/*
 * @@author Lim Yong Zhi
 */

public class Storage {
	private static final String MESSAGE_ADD = "added to %s: \"%s\"";
	private static final String MESSAGE_WRITE_ERROR = "unable to write file. check if read-only?";

	String filename = "My Calender.txt";
	
/*	public static ArrayList<String> initializeFile(){
		try {
			PrintWriter outputStream = new PrintWriter("My Calender.txt");
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> contentList = new ArrayList<String>();
		return contentList;
	}
*/
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
}