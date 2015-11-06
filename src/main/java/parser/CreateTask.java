package main.java.parser;
//@author: A0124524N; wenbin 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.resources.Task;

public class CreateTask {
	
	private static final String KEYWORD_BY = " by ";
	private static final int LENGTH_OF_BY = KEYWORD_BY.length();
	private static final String KEYWORD_FROM = " from ";
	private static final int LENGTH_OF_FROM = KEYWORD_FROM.length();
	private static final String KEYWORD_TO = " to ";
	private static final int LENGTH_OF_TO = KEYWORD_TO.length();
	
/*	// for testing purposes
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter :");
		String inputForAction = sc.nextLine();
		Task task = createEvent("event", input);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime());
		System.out.println(FlexiCommands.flexiDisplayCommands(inputForAction.toLowerCase()));		
	}//
*/	
	public final static Task createDeadline(String taskType, String taskContent) {
		String[] dateTime;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		
		//taskcontent must contain keyword by
		int indexToSplit = taskContent.lastIndexOf(KEYWORD_BY);
		String taskDescription = taskContent.substring(0, indexToSplit);
		taskDescription = removeSymbol(taskDescription);
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
				return new Task(taskType, taskDescription, "-", correctDateTime[0], "-", correctDateTime[1], false, true, 0);
			//input comes in time;date format
			else if(DateAndTime.isDate(correctDateTime[2]) && (DateAndTime.isTime(correctDateTime[3]))) 
				return new Task(taskType, taskDescription, "-", correctDateTime[2], "-", correctDateTime[3], false, true, 0);
			//input format is both invalid
			else 
				return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
		}
		//taskDateTime contains only deadline date
		else if(DateAndTime.isDate(taskDateTime)) {
			String date = DateAndTime.reformatDate(taskDateTime);
			return new Task(taskType, taskDescription, "-", date, "-", "-", false, true, 0);
		}
		//taskDateTime contains only deadline time and set deadline date as today
		else if(DateAndTime.isTime(taskDateTime)) {
			String time = DateAndTime.reformatTime(taskDateTime);
			return new Task(taskType, taskDescription, "-", dateFormat.format(today), "-", time, false, true, 0);
		}
		else
			return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
	}
	
	public final static Task createEvent(String taskType, String taskContent) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		
		//taskContent must contains KEYWORD_FROM and KEYWORD_TO
		int firstIndexToSplit = taskContent.lastIndexOf(KEYWORD_FROM);
		int secondIndexToSplit = taskContent.lastIndexOf(KEYWORD_TO);
		
		String taskDescription = taskContent.substring(0, firstIndexToSplit);
		String taskStart = taskContent.substring(firstIndexToSplit + (LENGTH_OF_FROM), secondIndexToSplit);
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
				
			//input comes in date;time format for both start and end
			if(DateAndTime.isDate(correctStartDateTime[0]) && DateAndTime.isTime(correctStartDateTime[1]) && 
										DateAndTime.isDate(correctEndDateTime[0]) && DateAndTime.isTime(correctEndDateTime[1])) {
				return correctDateComparison(taskType, taskDescription, correctStartDateTime[0], correctEndDateTime[0], 
											correctStartDateTime[1], correctEndDateTime[1]);
				}
			//input comes in time;date format for both start and end
			else if(DateAndTime.isDate(correctStartDateTime[2]) && DateAndTime.isTime(correctStartDateTime[3]) && 
							DateAndTime.isDate(correctEndDateTime[2]) && DateAndTime.isTime(correctEndDateTime[3])) {
				return correctDateComparison(taskType, taskDescription, correctStartDateTime[2], correctEndDateTime[2], 
											correctStartDateTime[3], correctEndDateTime[3]);
				}
			//start input comes in date;time and end input comes in time;date
			else if(DateAndTime.isDate(correctStartDateTime[0]) && DateAndTime.isTime(correctStartDateTime[1]) && 
						DateAndTime.isDate(correctEndDateTime[2]) && DateAndTime.isTime(correctEndDateTime[3])) {
				return correctDateComparison(taskType, taskDescription, correctStartDateTime[0], correctEndDateTime[2], 
						correctStartDateTime[1], correctEndDateTime[3]);
				}
			//start input comes in time;date and end input comes in date;time
			else if(DateAndTime.isDate(correctStartDateTime[2]) && DateAndTime.isTime(correctStartDateTime[3]) && 
					DateAndTime.isDate(correctEndDateTime[0]) && DateAndTime.isTime(correctEndDateTime[1])) {
					return correctDateComparison(taskType, taskDescription, correctStartDateTime[2], correctEndDateTime[0], 
						correctStartDateTime[3], correctEndDateTime[1]);
			}
			else
				return new Task(taskType, taskDescription, "-", "-", "-" , "-", false, false, 0);
			}
			//taskStart and taskEnd contains only date
		else if(DateAndTime.isDate(taskStart) && DateAndTime.isDate(taskEnd)) {
			String startDate = DateAndTime.reformatDate(taskStart);
			String endDate = DateAndTime.reformatDate(taskEnd);
			taskDescription = removeSymbol(taskDescription);
		
			if(DateAndTime.compareDates(startDate, endDate))
				return new Task(taskType, taskDescription, startDate, endDate, "-", "-", false, true, 0);
			else 
				return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
		}
		//taskStart and taskEnd contains only time
		else if(DateAndTime.isTime(taskStart) && DateAndTime.isTime(taskEnd)) {
			String startTime = DateAndTime.reformatTime(taskStart);		
			String endTime = DateAndTime.reformatTime(taskEnd);
			taskDescription = removeSymbol(taskDescription);
			//auto assume is today's event; append today 
			if(DateAndTime.compareTimes(startTime, endTime))
				return new Task(taskType, taskDescription, dateFormat.format(today), dateFormat.format(today), startTime, endTime, false, true, 0);
			else 
				return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
		}
		else			
			return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
	}
	
	public final static Task createFloating(String taskType, String taskContent) {
		return new Task(taskType, removeSymbol(taskContent), "-", "-", "-", "-", false, true, 0);
	}
	
	private static Task correctDateComparison(String taskType, String taskDescription, String startDate, 
													String endDate, String startTime, String endTime) {
		taskDescription = removeSymbol(taskDescription);
		if(DateAndTime.compareDates(startDate, endDate)) 
			return new Task(taskType, taskDescription, startDate, endDate, startTime, endTime, false, true, 0);
		else if(startDate.equals(endDate)  && DateAndTime.compareTimes(startTime, endTime))
			return new Task(taskType, taskDescription, startDate, endDate, startTime, endTime, false, true, 0);
		else
			return new Task(taskType, "-", "-", "-", "-", "-", false, false, 0);
	}
	
	private static String removeSymbol(String input) {
		return input.replaceAll(";", "").replaceAll("/", "");
	}
}
