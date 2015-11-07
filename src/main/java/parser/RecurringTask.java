package main.java.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.java.logic.History;
import main.java.resources.Task;

public class RecurringTask {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String KEYWORD_FOR = " for ";
	private static final int LENGTH_OF_FOR = KEYWORD_FOR.length();
	private static final String KEYWORD_TIMES = " times";

	public final static ArrayList<Task> create(ArrayList<String> listFromLogic) {
		History history = History.getInstance();
		ArrayList<Task> recurringTasks = new ArrayList<Task>();
		int recurID = history.getNextRecurID();
		
		//input must contain keywords for and times
		if (listFromLogic.get(1).contains(KEYWORD_FOR) && listFromLogic.get(1).contains(KEYWORD_TIMES)) {
			String content[] = listFromLogic.get(1).split(REGEX_WHITESPACE, 2);
			int firstIndexToSplit = content[1].lastIndexOf(KEYWORD_FOR);
			int secondIndexToSplit = content[1].lastIndexOf(KEYWORD_TIMES);
			
			String recurringType = content[0];
			String taskContent = content[1].substring(0, firstIndexToSplit);
			int recurTimes = Integer.parseInt(content[1].substring(firstIndexToSplit + (LENGTH_OF_FOR), secondIndexToSplit));
			
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(recurringType);
			temp.add(taskContent);
			Task task = new Task();
			
			/*System.out.println("******");
			System.out.println(temp.get(0));
			System.out.println(temp.get(1));
			System.out.println("******");*/
			
			switch(Parser.identifyTaskType(temp)) {
				case "deadline":
					/*System.out.println("******");
					System.out.println("Inside recur deadline");
					System.out.println("******");*/
					task = CreateTask.createDeadline(Parser.identifyTaskType(temp), taskContent); 
					break;
				case "event":
					task = CreateTask.createEvent(Parser.identifyTaskType(temp), taskContent);
					break;
				default:
					task = new Task();
					break;
			}
			
			String taskDescription = task.getTaskDescription();
			String taskType = task.getTaskType();
			String recurringStartDate;
			String recurringEndDate;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate recurEndDate;
			LocalDate recurStartDate;
			System.out.println(taskType);
			if(taskType.equals("deadline")) {
				String startTime = task.getStartTime();
				String endTime = task.getEndTime();
				String startDate = task.getStartDate();
				switch(recurringType){
					case "daily":
						recurringEndDate = task.getEndDate();
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);

						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("deadline", taskDescription, startDate, recurringEndDate, startTime, endTime, false, true, recurID ));
							recurEndDate = recurEndDate.plusDays(1);
							recurringEndDate = recurEndDate.format(formatter);
//							task.setEndDate(recurringEndDate);
						}
						break;
					case "weekly":
						recurringEndDate = task.getEndDate();
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);

						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("deadline", taskDescription, startDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurEndDate = recurEndDate.plusWeeks(1);
							recurringEndDate = recurEndDate.format(formatter);
//							task.setEndDate(recurringEndDate);
						}
						break;
					case "monthly":
						recurringEndDate = task.getEndDate();
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);

						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("deadline", taskDescription, startDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurEndDate = recurEndDate.plusMonths(1);
							recurringEndDate = recurEndDate.format(formatter);
//							task.setEndDate(recurringEndDate);
						}
						break;
					case "yearly":
						recurringEndDate = task.getEndDate();
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);

						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("deadline", taskDescription, startDate, recurringEndDate, startTime, endTime, false, true , recurID ));
							recurEndDate = recurEndDate.plusYears(1);
							recurringEndDate = recurEndDate.format(formatter);
//							task.setEndDate(recurringEndDate);
						}
						break;
					default:
						break;
					}
			}
			//task is event type
			else if(taskType.equals("event")) {
				String startTime = task.getStartTime();
				String endTime = task.getEndTime();
				switch(recurringType){
					case "daily":
						recurringStartDate = task.getStartDate();
						recurringEndDate = task.getEndDate();
						recurStartDate = LocalDate.parse(recurringStartDate, formatter);
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);
						
						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("event", taskDescription, recurringStartDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurStartDate = recurStartDate.plusDays(1);
							recurEndDate = recurEndDate.plusDays(1);
							recurringStartDate = recurStartDate.format(formatter);
							recurringEndDate = recurEndDate.format(formatter);
						}
						break;
					case "weekly":
						recurringStartDate = task.getStartDate();
						recurringEndDate = task.getEndDate();
						recurStartDate = LocalDate.parse(recurringStartDate, formatter);
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);
						
						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("event", taskDescription, recurringStartDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurStartDate = recurStartDate.plusWeeks(1);
							recurEndDate = recurEndDate.plusWeeks(1);
							recurringStartDate = recurStartDate.format(formatter);
							recurringEndDate = recurEndDate.format(formatter);
						}
						break;
					case "monthly":
						recurringStartDate = task.getStartDate();
						recurringEndDate = task.getEndDate();
						recurStartDate = LocalDate.parse(recurringStartDate, formatter);
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);
						
						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("event", taskDescription, recurringStartDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurStartDate = recurStartDate.plusMonths(1);
							recurEndDate = recurEndDate.plusMonths(1);
							recurringStartDate = recurStartDate.format(formatter);
							recurringEndDate = recurEndDate.format(formatter);
						}
						break;
					case "yearly":
						recurringStartDate = task.getStartDate();
						recurringEndDate = task.getEndDate();
						recurStartDate = LocalDate.parse(recurringStartDate, formatter);
						recurEndDate = LocalDate.parse(recurringEndDate, formatter);
						
						for(int i=0; i<recurTimes; i++) {
							recurringTasks.add(new Task("event", taskDescription, recurringStartDate, recurringEndDate, startTime, endTime, false, true , recurID));
							recurStartDate = recurStartDate.plusYears(1);
							recurEndDate = recurEndDate.plusYears(1);
							recurringStartDate = recurStartDate.format(formatter);
							recurringEndDate = recurEndDate.format(formatter);
						}
						break;
				}
			}
			else 
				return recurringTasks;
		}
		return recurringTasks;
	}
}
