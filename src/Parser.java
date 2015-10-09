//@author: wenbin 

import java.util.ArrayList;

public class Parser {
	
	public static ArrayList<String> retrieveCommand(String inputFromLogic){
		
		ArrayList<String> contentListForLogic = new ArrayList<String>();
		updateList(inputFromLogic, contentListForLogic);
		return contentListForLogic;
	}
	
	private static void updateList(String inputFromLogic, ArrayList<String> contentListForLogic) {
		
		String content[] = inputFromLogic.split(" ", 2);
		contentListForLogic.add(content[0]);
		if(content[1].contains(" ")) {
			content = content[1].split(" ", 2);
			contentListForLogic.add(content[0]);
			contentListForLogic.add(content[1]);
		}
		else 
			contentListForLogic.add(content[1]);
	}
	
	public static Task createTaskForAdd (ArrayList<String> listFromLogic) {
		
		Task task = new Task();
		String taskType = listFromLogic.get(1);
		String taskContent = listFromLogic.get(2);
		
		switch(taskType.toLowerCase()) {
		case "deadline":
			task = createDeadline(taskType, taskContent);
			break;
		case "event":
			task = createEvent(taskType, taskContent);
			break;
		case "floating":
			task = createFloating(taskType, taskContent);
			break;
		default:
			break;
		}
		return task;
	}
	
	public static Task createTaskForUpdate (ArrayList<String> listFromLogic) {
		
		Task task = new Task();
		String taskType = listFromLogic.get(3);
		String taskContent = listFromLogic.get(2);
		
		switch(taskType.toLowerCase()) {
		case "deadline":
			task = createDeadline(taskType, taskContent);
			break;
		case "event":
			task = createEvent(taskType, taskContent);
			break;
		case "floating":
			task = createFloating(taskType, taskContent);
			break;
		default:
			break;
		}
		return task;
	}

	private static Task createDeadline(String taskType, String taskContent) {
		
		int indexToSplit = taskContent.lastIndexOf("by");
		String taskDescription = taskContent.substring(0, indexToSplit-1);
		String taskDate = taskContent.substring(indexToSplit+3);
		Task task = new Task(taskType, taskDescription, taskDate, null, null);
		
		return task;
	}
	
	private static Task createEvent(String taskType, String taskContent) {
		
		int firstIndexToSplit = taskContent.lastIndexOf("on");
		int secondIndexToSplit = taskContent.lastIndexOf("from");
		int thirdIndexToSplit = taskContent.lastIndexOf("to");
		String taskDescription = taskContent.substring(0, firstIndexToSplit-1);
		String taskDate = taskContent.substring(firstIndexToSplit+3, secondIndexToSplit-1);
		String startTime = taskContent.substring(secondIndexToSplit+5, thirdIndexToSplit-1);
		String endTime = taskContent.substring(thirdIndexToSplit+3);
		Task task = new Task(taskType, taskDescription, taskDate, startTime, endTime);
		
		return task;
	}
	
	private static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, taskContent, null, null, null);
	}

}
