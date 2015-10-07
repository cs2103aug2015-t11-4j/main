import java.util.ArrayList;

public class Logic {
	public static ArrayList<String> passToParser(String command){
		ArrayList<String> commandAfterParser = Parser.analyzeCommand(command);
		return commandAfterParser;
	}

	public static void takeAction(ArrayList<String> commandAfterParser, ArrayList<String> contentList) {
		int code = -1;
		switch (commandAfterParser.get(0)){
		case "Add":
			String content = addOneItem(commandAfterParser, contentList);
			code = Storage.modify(commandAfterParser.get(0), content);
			break;
		}
		UI.feedback(commandAfterParser.get(0),code);
	}


	private static String addOneItem(ArrayList<String> commandAfterParser, ArrayList<String> contentList) {
		String content = commandAfterParser.get(3)+" at "+commandAfterParser.get(2)+": "+commandAfterParser.get(1);
		contentList.add(content);
		return content;
		
	}
	

	
	
}
