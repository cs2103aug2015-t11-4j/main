package main.java.parser;
//@author: wenbin 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import main.java.resources.Task;

public class Parser {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String KEYWORD_BY = "/b";
	private static final int LENGTH_OF_BY = KEYWORD_BY.length();
	private static final String KEYWORD_FROM = "/f";
	private static final int LENGTH_OF_FROM = KEYWORD_FROM.length();
	private static final String KEYWORD_TO = "/t";
	private static final int LENGTH_OF_TO = KEYWORD_TO.length();

	//for testing purposes
	public static void main(String[] args) {

		System.out.println("Enter: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		ArrayList<String> contentListForLogic = retrieveCommand(input);
//		for(int i=0; i<contentListForLogic.size(); i++) {
//			System.out.println(contentListForLogic.get(i));
//		}
//		String tasktype = identifyTaskType(contentListForLogic);
//		System.out.println(tasktype);
		
		Task task = createTaskForAdd(contentListForLogic);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime()); 
		
		
	}
	
	public static ArrayList<String> retrieveCommand(String inputFromLogic){
		
		ArrayList<String> contentListForLogic = new ArrayList<String>();
		inputFromLogic = formatInputForValidParsing(inputFromLogic);
		updateList(inputFromLogic, contentListForLogic);
		return contentListForLogic;
	}
	
	private static void updateList(String inputFromLogic, ArrayList<String> contentListForLogic) {
		
		//for commands: exit, help, undo
		if(isOneWord(inputFromLogic)) {
			contentListForLogic.add(inputFromLogic);
		}
		
		else {  
			//splitting first input from logic into 2: (command) (content)
			String content[] = inputFromLogic.split(REGEX_WHITESPACE, 2);
			contentListForLogic.add(content[0]);
			contentListForLogic.add(content[1]);
		}
	}
	
	public static Task createTaskForAdd(ArrayList<String> listFromLogic) {
		
		Task task = new Task();
		String taskType = identifyTaskType(listFromLogic);
		String taskContent = listFromLogic.get(1);
		
		switch(taskType) {
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
	
	public static Task createTaskForUpdate(ArrayList<String> listFromLogic) {
		
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
//			task = createFloating(taskType, taskContent);
			break;
		default:
			break;
		}
		return task;
	}
	
	private static String identifyTaskType(ArrayList<String> listFromLogic ) {
		String taskContent = listFromLogic.get(1);
		
		if(taskContent.contains(KEYWORD_BY))
			return "deadline";
		else if(taskContent.contains(KEYWORD_FROM) && taskContent.contains(KEYWORD_TO))
			return "event";
		else
			return "floating";
	}
	
	private static Task createDeadline(String taskType, String taskContent) {
		String[] dateTime;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Task task = new Task();
		
		int indexToSplit = taskContent.lastIndexOf(KEYWORD_BY);
		String taskDescription = taskContent.substring(0, indexToSplit - 1);
		String taskDateTime = taskContent.substring(indexToSplit + (LENGTH_OF_BY + 1));
		//taskDateTime contains both deadline date and deadline time
		if(taskDateTime.contains(";")) {
			dateTime = taskDateTime.split(";");
			dateTime[0] = DateAndTime.reformatDate(dateTime[0]);
			dateTime[1] = DateAndTime.reformatTime(dateTime[1]);

  			task = new Task(taskType, taskDescription, null, dateTime[0], null, dateTime[1], false);
		}
		//taskDateTime contains only deadline date
		else if(DateAndTime.isDate(taskDateTime)) {
			String date = DateAndTime.reformatDate(taskDateTime);
			task = new Task(taskType, taskDescription, null, date, null, null, false);
		}
		//taskDateTime contains only deadline time and set deadline date as today
		else if(DateAndTime.isTime(taskDateTime)) {
			String time = DateAndTime.reformatTime(taskDateTime);
			task = new Task(taskType, taskDescription, null, dateFormat.format(today), null, time, false);
		}
		else 
			return task;
		
		return task;
	}
	
	private static Task createEvent(String taskType, String taskContent) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Task task = new Task();
		
		int firstIndexToSplit = taskContent.lastIndexOf(KEYWORD_FROM);
		int secondIndexToSplit = taskContent.lastIndexOf(KEYWORD_TO);
		
		String taskDescription = taskContent.substring(0, firstIndexToSplit - 1);
		String taskStart = taskContent.substring(firstIndexToSplit + (LENGTH_OF_FROM + 1), secondIndexToSplit - 1);
		String taskEnd = taskContent.substring(secondIndexToSplit + (LENGTH_OF_TO + 1), taskContent.length());
		//taskStart and taskEnd contains both time and date
		if(taskStart.contains(";") && taskEnd.contains(";")) {
			String[] startDateTime;
			String[] endDateTime;
			startDateTime = taskStart.split(";");
			endDateTime = taskEnd.split(";");
			startDateTime[0] = DateAndTime.reformatDate(startDateTime[0]);
			startDateTime[1] = DateAndTime.reformatTime(startDateTime[1]);
			endDateTime[0] = DateAndTime.reformatDate(endDateTime[0]);
			endDateTime[1] = DateAndTime.reformatTime(endDateTime[1]);
			
			if(DateAndTime.compareDates(startDateTime[0], endDateTime[0]) && DateAndTime.compareTimes(startDateTime[1], endDateTime[1]))
				task = new Task(taskType, taskDescription, startDateTime[0], endDateTime[0], startDateTime[0], endDateTime[1], false);
		}
		//taskStart and taskEnd contains only date
		else if(DateAndTime.isDate(taskStart) && DateAndTime.isDate(taskEnd)) {
			String startDate = DateAndTime.reformatDate(taskStart);
			String endDate = DateAndTime.reformatDate(taskEnd);
			
			if(DateAndTime.compareDates(startDate, endDate))
					task = new Task(taskType, taskDescription, startDate, endDate, null, null, false);
		}
		//taskStart and taskEnd contains only time
		else if(DateAndTime.isTime(taskStart) && DateAndTime.isTime(taskEnd)) {
			String startTime = DateAndTime.reformatTime(taskStart);
			String endTime = DateAndTime.reformatTime(taskEnd);
			//auto assume is today's event; append today 
			if(DateAndTime.compareTimes(startTime, endTime))
				task = new Task(taskType, taskDescription, dateFormat.format(today), dateFormat.format(today), startTime, endTime, false);
		}
		else
			return task;	
		
		return task;
	}
	
	private static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, taskContent, null, null, null, null, false);
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
