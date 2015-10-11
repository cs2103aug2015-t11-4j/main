package main.java.logic;
//@author : Jiahuan
import java.util.ArrayList;

import main.java.parser.Parser;
import main.java.resources.Task;
import main.java.resources.dataDisplay;
import main.java.storage.Storage;
import main.java.ui.UI;

public class Logic {
	
	//Pass the User Input to parser and parser return a arraylist of parsing result
	public static ArrayList<String> passToParser(String command){
		ArrayList<String> commandAfterParser = Parser.retrieveCommand(command);
		return commandAfterParser;
	}
	//Switch case to decide which action to carry forward after first parsing
	//public static void takeAction(ArrayList<String> inputForSecondParsing, ArrayList<String> contentList) {
	public static void takeAction(ArrayList<String> inputForSecondParsing) {
		int code = -1;
		Task task;
		int itemNum;
		String taskType;

			switch (inputForSecondParsing.get(0).toLowerCase()){
			//if add, need to create task object for storage
			case "add":
				code = addInLogic(inputForSecondParsing);
				break;
			case "update":
				code = updateInLogic(inputForSecondParsing);
				break;
			case "delete":
				code = deleteInLogic(inputForSecondParsing);
				break;
			case "display":
				dataDisplay.displayAll();
				break;
			default:
				UI.feedbackWrongCommand();
			}
		UI.feedback(inputForSecondParsing.get(0),code);  //not used for now (Yu Ju)
	}
	private static int deleteInLogic(ArrayList<String> inputForSecondParsing) {
		int code;
		int itemNum;
		itemNum = Integer.parseInt(inputForSecondParsing.get(1));
		code = Storage.deleteOneItem(itemNum);//pass in item number
		//System.out.println("deleted: " + code);  //prints out 0 (yj)
		return code;
	}
	private static int updateInLogic(ArrayList<String> inputForSecondParsing) {
		int code;
		Task task;
		int itemNum;
		String taskType;
		itemNum = Integer.parseInt(inputForSecondParsing.get(1)); 
		taskType = Storage.getTaskTypeByItemNum(itemNum); //get tasktype from logic
		inputForSecondParsing.add(taskType); //append tasktype to arraylist
		task = Parser.createTaskForUpdate(inputForSecondParsing); //create task obj 
		code = Storage.updateOneItem(itemNum, task); //pass in item number and task obj
		return code;
	}
	private static int addInLogic(ArrayList<String> inputForSecondParsing) {
		int code;
		Task task;
		task = Parser.createTaskForAdd(inputForSecondParsing);
		code = Storage.addOneItem(task);
		//System.out.println("added: " + code);  //prints out 0 (yj)
		return code;
	}


//**************Below part can be used by Yong Zhi for storage analysis************************//	
	
/*

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
	
*/
	
	
}
