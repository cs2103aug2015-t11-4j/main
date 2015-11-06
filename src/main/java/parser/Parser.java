package main.java.parser;
//@author: A0124524N; wenbin 

import java.util.ArrayList;

import main.java.resources.Task;

public class Parser {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String KEYWORD_BY = " by ";
	private static final String KEYWORD_FROM = " from ";
	private static final String KEYWORD_TO = " to ";
	
/*	//for testing purposes
	public static void main(String[] args) {
		
		String date = "21/12/2015";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse(date, formatter);
		date1 = date1.plusDays(1);
		System.out.println(date1.toString());
		System.out.println("Enter: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		ArrayList<String> contentListForLogic = retrieveCommand(input);
		ArrayList<Task> recurring = new ArrayList<Task>();
		recurring = createRecurringTasks(contentListForLogic);
		for(int i=0; i<recurring.size(); i++) {
			System.out.println(recurring.get(i).getTaskDescription() + " ; " +
					recurring.get(i).getStartDate() + " ; " +
					recurring.get(i).getEndDate() + " ; " +
					recurring.get(i).getStartTime() + " ; " +
					recurring.get(i).getEndTime() + " ; ");
		}

		String tasktype = identifyTaskType(contentListForLogic);
		System.out.println(tasktype);
		
		Task task = createTaskForAdd(contentListForLogic);
		System.out.println("startdate: " + task.getStartDate());
		System.out.println("enddate: " + task.getEndDate());
		System.out.println("task: " + task.getTaskDescription());
		System.out.println("TT: " + task.getTaskType());
		System.out.println("ST: " + task.getStartTime());
		System.out.println("ET: " + task.getEndTime()); 

		
	}//
*/	//FOR LOGIC USE ONLY
	public final static ArrayList<String> retrieveCommand(String inputFromLogic){
		
		ArrayList<String> contentListForLogic = new ArrayList<String>();
		inputFromLogic = formatInputForValidParsing(inputFromLogic);
		updateList(inputFromLogic, contentListForLogic);
		
		return contentListForLogic;
	}
	
	//FOR LOGIC USE ONLY
	public final static Task createTaskForAdd(ArrayList<String> listFromLogic) {
			
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
	
	//FOR LOGIC USE ONLY
	public final static ArrayList<Task> createRecurringTasks(ArrayList<String> listFromLogic) {
		ArrayList<Task> recurringTasks = new ArrayList<Task>();
		recurringTasks = RecurringTask.create(listFromLogic);
		return recurringTasks;
	}
	
	//FOR LOGIC USE ONLY
	//formats the display commands with flexicommands
	public final static ArrayList<String> flexiDisplay(ArrayList<String> listFromLogic) {
		String displayContent = listFromLogic.remove(1);
		displayContent = FlexiCommands.flexiDisplayCommands(displayContent);
		listFromLogic.add(displayContent);
		
		return listFromLogic;
	}
	
	private static void updateList(String inputFromLogic, ArrayList<String> contentListForLogic) {
		
		//for commands: exit, help, undo etc
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
	public static String identifyTaskType(ArrayList<String> listFromLogic ) {
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

	public static ArrayList<String> retrieveInputForUpdate(String string) {
		ArrayList<String> output = new ArrayList<String>();
		String content[] = string.split(REGEX_WHITESPACE, 2);
		System.out.println(content.length);
		output.add(content[0]);
		if (content.length==1){
			output.add("");
		}else{
		output.add(content[1]);
		}
		return output;
	}
	
}
