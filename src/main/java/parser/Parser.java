package main.java.parser;
//@author: wenbin 

import java.util.ArrayList;
import java.util.Scanner;

import main.java.resources.Task;

public class Parser {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String KEYWORD_BY = "/b";
	private static final String KEYWORD_FROM = "/f";
	private static final String KEYWORD_TO = "/t";


	/*for testing purposes
	public static void main(String[] args) {

		System.out.println("Enter: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		ArrayList<String> contentListForLogic = retrieveCommand(input);
		for(int i=0; i<contentListForLogic.size(); i++) {
			System.out.println(contentListForLogic.get(i));
		}
//		String tasktype = identifyTaskType(contentListForLogic);
//		System.out.println(tasktype);
		
		Task task = createTaskForAdd(contentListForLogic);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime()); 
		
		
	}*/
	
	public static ArrayList<String> retrieveCommand(String inputFromLogic){
		
		ArrayList<String> contentListForLogic = new ArrayList<String>();
		inputFromLogic = formatInputForValidParsing(inputFromLogic);
		updateList(inputFromLogic, contentListForLogic);
		
		return contentListForLogic;
	}
	
	private static void updateList(String inputFromLogic, ArrayList<String> contentListForLogic) {
		
		//for commands: exit, help, undo
		if(isOneWord(inputFromLogic)) {
			contentListForLogic.add(FlexiCommands.flexiCommands(inputFromLogic));
		}
		
		else {  
			//splitting first input from logic into 2: (command) (content)
			String content[] = inputFromLogic.split(REGEX_WHITESPACE, 2);
			contentListForLogic.add(FlexiCommands.flexiCommands(content[0]));
			contentListForLogic.add(content[1]);
		}
	}
	
	public static Task createTaskForAdd(ArrayList<String> listFromLogic) {
		
		Task task = new Task();
		String taskType = identifyTaskType(listFromLogic);
		String taskContent = listFromLogic.get(1);
		
		switch(taskType) {
		case "deadline":
			task = CreateTask.createDeadline(taskType, taskContent);
			break;
		case "event":
			task = CreateTask.createEvent(taskType, taskContent);
			break;
		case "floating":
			task = CreateTask.createFloating(taskType, taskContent);
			break;
		default:
			break;
		}
		return task;
	}
	/*TODO: based on new format changes
	public static Task createTaskForUpdate(ArrayList<String> listFromLogic) {
		
		Task task = new Task();
		String taskType = listFromLogic.get(3);
		String taskContent = listFromLogic.get(2);
		
		switch(taskType.toLowerCase()) {
		case "deadline":
			task = CreateTask.createDeadline(taskType, taskContent);
			break;
		case "event":
			task = CreateTask.createEvent(taskType, taskContent);
			break;
		case "floating":
//			task = createFloating(taskType, taskContent);
			break;
		default:
			break;
		}
		return task;
	}*/
	
	private static String identifyTaskType(ArrayList<String> listFromLogic ) {
		String taskContent = listFromLogic.get(1);
		
		if(taskContent.contains(KEYWORD_BY))
			return "deadline";
		else if(taskContent.contains(KEYWORD_FROM) && taskContent.contains(KEYWORD_TO))
			return "event";
		else
			return "floating";
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
