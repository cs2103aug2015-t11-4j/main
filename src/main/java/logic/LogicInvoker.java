//@author : Jiahuan

//Unused after implementing command pattern

/*package main.java.logic;
import java.util.ArrayList;

import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.Task;
import main.java.storage.Storage;
import main.java.ui.UI;
//invoker class
public class LogicInvoker {
	

	//Switch case to decide which action to carry forward after first parsing
	//public static void takeAction(ArrayList<String> inputForSecondParsing, ArrayList<String> contentList) {
	public static void takeAction(String inputFromUser) {
		ArrayList<String> inputForAction = Parser.retrieveCommand(inputFromUser);
		int code = -1;
			switch (inputForAction.get(0).toLowerCase()){
			//if add, need to create task object for storage
			case "add":
				code = addInLogic(inputForAction);
				break;
			case "update":
				code = updateInLogic(inputForAction);
				break;
			case "delete":
				code = deleteInLogic(inputForAction);
				break;
			case "display":
				DataDisplay.displaySummary();
				break;
			default:
				UI.feedbackWrongCommand();
			}
		UI.feedback(inputForAction.get(0),code);  //not used for now (Yu Ju)
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

}
*/