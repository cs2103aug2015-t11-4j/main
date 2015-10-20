package main.java.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import main.java.resources.Task;

public class CreateTask {
	
	private static final String KEYWORD_BY = " by ";
	private static final int LENGTH_OF_BY = KEYWORD_BY.length();
	private static final String KEYWORD_FROM = " from ";
	private static final int LENGTH_OF_FROM = KEYWORD_FROM.length();
	private static final String KEYWORD_TO = " to ";
	private static final int LENGTH_OF_TO = KEYWORD_TO.length();
	
	/* for testing purposes
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter :");
		String input = sc.nextLine();
		System.out.println(removeAllSemiColon(input));
		Task task = createDeadline("deadline", input);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime()); 
	}*/
	
	public static Task createDeadline(String taskType, String taskContent) {
		String[] dateTime;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Task task = new Task();
		
		//taskcontent must contain keyword by
		if(taskContent.contains(KEYWORD_BY)) {
			int indexToSplit = taskContent.lastIndexOf(KEYWORD_BY);
			String taskDescription = taskContent.substring(0, indexToSplit);
			taskDescription = removeAllSemiColon(taskDescription);
			String taskDateTime = taskContent.substring(indexToSplit + (LENGTH_OF_BY));
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
		}
			return task;
	}
	
	public static Task createEvent(String taskType, String taskContent) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Task task = new Task();
		
		//taskContent must contains KEYWORD_FROM and KEYWORD_TO
		if(taskContent.contains(KEYWORD_FROM) && taskContent.contains(KEYWORD_TO)) {
			int firstIndexToSplit = taskContent.lastIndexOf(KEYWORD_FROM);
			int secondIndexToSplit = taskContent.lastIndexOf(KEYWORD_TO);
		
			String taskDescription = taskContent.substring(0, firstIndexToSplit);
			taskDescription = removeAllSemiColon(taskDescription);
			String taskStart = taskContent.substring(firstIndexToSplit + (LENGTH_OF_FROM ), secondIndexToSplit);
			String taskEnd = taskContent.substring(secondIndexToSplit + (LENGTH_OF_TO), taskContent.length());
		
			//taskStart and taskEnd contains both time and date
			if(taskStart.contains(";") && taskEnd.contains(";")) {
				String[] startDateTime = taskStart.split(";");
				String[] endDateTime = taskEnd.split(";");

				startDateTime[0] = DateAndTime.reformatDate(startDateTime[0]);
				startDateTime[1] = DateAndTime.reformatTime(startDateTime[1]);
				endDateTime[0] = DateAndTime.reformatDate(endDateTime[0]);
				endDateTime[1] = DateAndTime.reformatTime(endDateTime[1]);
			
				if(DateAndTime.compareDates(startDateTime[0], endDateTime[0]) && DateAndTime.compareTimes(startDateTime[1], endDateTime[1]))
					task = new Task(taskType, taskDescription, startDateTime[0], endDateTime[0], startDateTime[1], endDateTime[1], false);
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
		}
		return task;
	}
	
	public static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, removeAllSemiColon(taskContent), null, null, null, null, false);
	}
	
	private static String removeAllSemiColon(String input) {
		return input.replaceAll(";", "");
	}
}
