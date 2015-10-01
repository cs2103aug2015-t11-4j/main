import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.util.ArrayList;

public class Storage {
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
}
