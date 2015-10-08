import java.util.ArrayList;

public class Logic {
	public static ArrayList<String> passToParser(String command){
		ArrayList<String> commandAfterParser = Parser.retrieveCommand(command);
		return commandAfterParser;
	}

	public static void takeAction(ArrayList<String> commandAfterParser, ArrayList<String> contentList) {
		int code = -1;
		switch (commandAfterParser.get(0).toLowerCase()){
		case "add":
			String content = createContentForAdd(commandAfterParser, contentList);
			code = Storage.addOneItem(content);
			break;
		case "update":
			content = createContentForUpdate(commandAfterParser, contentList);
			Storage.updateOneItem(Integer.parseInt(commandAfterParser.get(1)), content); //pass in item number
			break;
		case "delete":
			Storage.deleteOneItem(Integer.parseInt(commandAfterParser.get(1)));//pass in item number
		case "display":
			Storage.display();
			
		}
		UI.feedback(commandAfterParser.get(0),code);
	}




	private static String createContentForUpdate(ArrayList<String> commandAfterParser, ArrayList<String> contentList) {
		
		return createContentForAdd(commandAfterParser, contentList);
	}

	private static String createContentForAdd(ArrayList<String> commandAfterParser, ArrayList<String> contentList) {
		Task task = Parser.createTask(commandAfterParser);
		String content = null;
		switch (task.getTaskType().toLowerCase()){
		case "deadline":
			content = createContentForDeadline(task);
			break;
		case "event":
			content = createContentForEvent(task);
			break;
		case "floating":
			content = createContentForfloating(task);
			break;
		}
		contentList.add(content);
		return content;
		
	}

	private static String createContentForfloating(Task task) {
		
		return task.getTaskDescription();
	}

	private static String createContentForEvent(Task task) {
		
		return task.getStartTime()+" - "+task.getEndTime()+ " on "+ task.getDate()+": "+task.getTaskDescription();
	}

	private static String createContentForDeadline(Task task) {

		return "By "+task.getDate()+": "+ task.getTaskDescription();
	}
	

	
	
}
