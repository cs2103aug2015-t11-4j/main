import java.util.ArrayList;

public class Parser {
	public static ArrayList<String> analyzeCommand(String command){
		ArrayList<String> list = new ArrayList<String>();
		updateList(command, list);
		return list;
	}



	private static void updateList(String command, ArrayList<String> list) {
		String action = getAction(command);
		String content = getContent(command);
		String time = getTime(command);
		String date = getDate(command);
		list.add(action);
		list.add(content);
		list.add(time);
		list.add(date);
	}

	private static String getAction(String command) {
		// TODO Auto-generated method stub
		return "Add";
	}
	
	private static String getContent(String command) {
		// TODO Auto-generated method stub
		return "meeting";
	}

	private static String getTime(String command) {
		// TODO Auto-generated method stub
		return "9am";
	}
	
	private static String getDate(String command) {
		// TODO Auto-generated method stub
		return "21st Oct";
	}









}
