package main.java.parser;
//@author: A0124524N; wenbin 

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
		Task task = createEvent("event", input);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime());
	}*/
	
	public final static Task createDeadline(String taskType, String taskContent) {
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
				String[] correctDateTime = new String[4];
				dateTime = taskDateTime.split(";");
				
				correctDateTime[0] = DateAndTime.reformatDate(dateTime[0]);
				correctDateTime[1] = DateAndTime.reformatTime(dateTime[1]);
				correctDateTime[2] = DateAndTime.reformatDate(dateTime[1]);
				correctDateTime[3] = DateAndTime.reformatTime(dateTime[0]);
				
				//input comes in date;time format
				if(DateAndTime.isDate(correctDateTime[0]) && (DateAndTime.isTime(correctDateTime[1]))) 
						task = new Task(taskType, taskDescription, null, correctDateTime[0], null, correctDateTime[1], false);
				//input comes in time;date format
				else if(DateAndTime.isDate(correctDateTime[2]) && (DateAndTime.isTime(correctDateTime[3]))) 
					task = new Task(taskType, taskDescription, null, correctDateTime[2], null, correctDateTime[3], false);
				//input is invalid format
				else 
					task = new Task(taskType, taskDescription, null, "incorrect date format", null , "incorrect time format", false);
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
	
	public final static Task createEvent(String taskType, String taskContent) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Task task = new Task();
		
		//taskContent must contains KEYWORD_FROM and KEYWORD_TO
		if(taskContent.contains(KEYWORD_FROM) && taskContent.contains(KEYWORD_TO)) {
			int firstIndexToSplit = taskContent.lastIndexOf(KEYWORD_FROM);
			int secondIndexToSplit = taskContent.lastIndexOf(KEYWORD_TO);
		
			String taskDescription = taskContent.substring(0, firstIndexToSplit);
			String taskStart = taskContent.substring(firstIndexToSplit + (LENGTH_OF_FROM ), secondIndexToSplit);
			String taskEnd = taskContent.substring(secondIndexToSplit + (LENGTH_OF_TO), taskContent.length());
			
			//taskStart and taskEnd contains both time and date
			if(taskStart.contains(";") && taskEnd.contains(";")) {
				String[] correctStartDateTime = new String[4];
				String[] startDateTime = taskStart.split(";");
				correctStartDateTime[0] = DateAndTime.reformatDate(startDateTime[0]);
				correctStartDateTime[1] = DateAndTime.reformatTime(startDateTime[1]);
				correctStartDateTime[2] = DateAndTime.reformatDate(startDateTime[1]);
				correctStartDateTime[3] = DateAndTime.reformatTime(startDateTime[0]);
				
				String[] correctEndDateTime = new String[4];
				String[] endDateTime = taskEnd.split(";");
				correctEndDateTime[0] = DateAndTime.reformatDate(endDateTime[0]);
				correctEndDateTime[1] = DateAndTime.reformatTime(endDateTime[1]);
				correctEndDateTime[2] = DateAndTime.reformatDate(endDateTime[1]);
				correctEndDateTime[3] = DateAndTime.reformatTime(endDateTime[0]);
				
				for(int i=0; i<4; i++) {
					//System.out.println(correctStartDateTime[i]);
					System.out.println(correctEndDateTime[i]);
				}
				//input comes in date;time format for both start and end
				if(DateAndTime.isDate(correctStartDateTime[0]) && DateAndTime.isTime(correctStartDateTime[1]) && 
											DateAndTime.isDate(correctEndDateTime[0]) && DateAndTime.isTime(correctEndDateTime[1])) {
					task = correctDateComparison(taskType, taskDescription, correctStartDateTime[0], correctEndDateTime[0], 
											correctStartDateTime[1], correctEndDateTime[1]);
				}
				//input comes in time;date format for both start and end
				else if(DateAndTime.isDate(correctStartDateTime[2]) && DateAndTime.isTime(correctStartDateTime[3]) && 
							DateAndTime.isDate(correctEndDateTime[2]) && DateAndTime.isTime(correctEndDateTime[3])) {
					task = correctDateComparison(taskType, taskDescription, correctStartDateTime[2], correctEndDateTime[2], 
											correctStartDateTime[3], correctEndDateTime[3]);
				}
				//start input comes in date;time and end input comes in time;date
				else if(DateAndTime.isDate(correctStartDateTime[0]) && DateAndTime.isTime(correctStartDateTime[1]) && 
						DateAndTime.isDate(correctEndDateTime[2]) && DateAndTime.isTime(correctEndDateTime[3])) {
					task = correctDateComparison(taskType, taskDescription, correctStartDateTime[0], correctEndDateTime[2], 
						correctStartDateTime[1], correctEndDateTime[3]);
				}
				//start input comes in time;date and end input comes in date;time
				else if(DateAndTime.isDate(correctStartDateTime[2]) && DateAndTime.isTime(correctStartDateTime[3]) && 
						DateAndTime.isDate(correctEndDateTime[0]) && DateAndTime.isTime(correctEndDateTime[1])) {
					task = correctDateComparison(taskType, taskDescription, correctStartDateTime[2], correctEndDateTime[0], 
						correctStartDateTime[3], correctEndDateTime[1]);
				}
				else
					task = new Task(taskType, taskDescription, "incorrect date format", "incorrect date format", "incorrect time format" , "incorrect time format", false);
			}
			//taskStart and taskEnd contains only date
			else if(DateAndTime.isDate(taskStart) && DateAndTime.isDate(taskEnd)) {
				String startDate = DateAndTime.reformatDate(taskStart);
				String endDate = DateAndTime.reformatDate(taskEnd);
				taskDescription = removeAllSemiColon(taskDescription);
			
				if(DateAndTime.compareDates(startDate, endDate))
					task = new Task(taskType, taskDescription, startDate, endDate, null, null, false);
				else 
					task = new Task(taskType,"end date/time is earlier than start date/time", "invalid", "invalid", null, null, false);
			}
			//taskStart and taskEnd contains only time
			else if(DateAndTime.isTime(taskStart) && DateAndTime.isTime(taskEnd)) {
				String startTime = DateAndTime.reformatTime(taskStart);
				String endTime = DateAndTime.reformatTime(taskEnd);
				taskDescription = removeAllSemiColon(taskDescription);
				//auto assume is today's event; append today 
				if(DateAndTime.compareTimes(startTime, endTime))
					task = new Task(taskType, taskDescription, dateFormat.format(today), dateFormat.format(today), startTime, endTime, false);
				else 
					task = new Task(taskType,"end date/time is earlier than start date/time", null, null, "invalid", "invalid", false);
			}
			else
				return task;	
		}
		return task;
	}
	
	public final static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, removeAllSemiColon(taskContent), null, null, null, null, false);
	}
	
	private static Task correctDateComparison(String taskType, String taskDescription, String startDate, 
													String endDate, String startTime, String endTime) {
		taskDescription = removeAllSemiColon(taskDescription);
		if(DateAndTime.compareDates(startDate, endDate) && DateAndTime.compareTimes(startTime, endTime)) 
			return new Task(taskType, taskDescription, startDate, endDate, startTime, endTime, false);
		else
			return new Task(taskType, "end date/time is earlier than start date/time", "invalid", "invalid", "invalid", "invalid", false);		
	}
	private static String removeAllSemiColon(String input) {
		return input.replaceAll(";", "");
	}
}
