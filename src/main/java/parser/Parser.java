package main.java.parser;
//@author: wenbin 

import java.util.ArrayList;
import java.util.Scanner;

import main.java.resources.Task;

public class Parser {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String KEYWORD_BY = "by";
	private static final int LENGTH_OF_BY = KEYWORD_BY.length();
	private static final String KEYWORD_ON = "on";
	private static final int LENGTH_OF_ON = KEYWORD_ON.length();
	private static final String KEYWORD_FROM = "from";
	private static final int LENGTH_OF_FROM = KEYWORD_FROM.length();
	private static final String KEYWORD_TO = "to";
	private static final int LENGTH_OF_TO = KEYWORD_TO.length();

	//for testing purposes
	/*public static void main(String[] args) {
		System.out.println("Enter: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		ArrayList<String> contentListForLogic = retrieveCommand(input);
		contentListForLogic.add("deadline");
		for(int i=0; i<contentListForLogic.size(); i++) {
			System.out.println(contentListForLogic.get(i));
		}
		Task task = createTaskForUpdate(contentListForLogic);
		System.out.println("date: " + task.getDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime());
		
	}
	*/
	public static ArrayList<String> retrieveCommand(String inputFromLogic){
		
		ArrayList<String> contentListForLogic = new ArrayList<String>();
		inputFromLogic = formatInputForValidParsing(inputFromLogic);
		updateList(inputFromLogic, contentListForLogic);
		return contentListForLogic;
	}
	
	private static void updateList(String inputFromLogic, ArrayList<String> contentListForLogic) {
		
		if(isOneWord(inputFromLogic)) {
			contentListForLogic.add(inputFromLogic);
		}
		
		else {  
			String content[] = inputFromLogic.split(REGEX_WHITESPACE, 2);
			contentListForLogic.add(content[0]);
			if(content[1].contains(REGEX_WHITESPACE)) {
				content = content[1].split(REGEX_WHITESPACE, 2);
				contentListForLogic.add(content[0]);
				contentListForLogic.add(content[1]);
			}
			else 
				contentListForLogic.add(content[1]);
		}
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
		int indexToSplit = taskContent.lastIndexOf(KEYWORD_BY);
		String taskDescription = taskContent.substring(0, indexToSplit - 1);
		String taskDate = taskContent.substring(indexToSplit + (LENGTH_OF_BY + 1));
		
		Task task = new Task(taskType, taskDescription, taskDate, null, null);
		
		return task;
	}
	
	private static Task createEvent(String taskType, String taskContent) {
		int firstIndexToSplit = taskContent.lastIndexOf(KEYWORD_ON);
		int secondIndexToSplit = taskContent.lastIndexOf(KEYWORD_FROM);
		int thirdIndexToSplit = taskContent.lastIndexOf(KEYWORD_TO);
		
		String taskDescription = taskContent.substring(0, firstIndexToSplit - 1);
		String taskDate = taskContent.substring(firstIndexToSplit + (LENGTH_OF_ON + 1), secondIndexToSplit - 1);
		String startTime = taskContent.substring(secondIndexToSplit + (LENGTH_OF_FROM + 1), thirdIndexToSplit - 1);
		String endTime = taskContent.substring(thirdIndexToSplit + (LENGTH_OF_TO + 1));
		
		Task task = new Task(taskType, taskDescription, taskDate, startTime, endTime);
		
		return task;
	}
	
	private static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, taskContent, null, null, null);
	}
	
	//removes all unnecessary whitespaces to 1 whitespace
	private final static String formatInputForValidParsing (String input) {
		return input.replaceAll("\\s+", REGEX_WHITESPACE).trim();
	}
	
	//check if a string input is only a word
	private final static boolean isOneWord(String input) {
		if (input.contains(REGEX_WHITESPACE))
			return false;
		else 
			return true;
	}
	
}
