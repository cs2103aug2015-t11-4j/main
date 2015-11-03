//@Author:Jiahuan
package main.java.logic;

import java.io.IOException;
import java.util.ArrayList;

import main.java.parser.FlexiCommands;
import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Controller {

	private static History history = History.getInstance();

	/*
	 * //Screen list is the list of tasks appeared on screen private
	 * ArrayList<Task> screenList;
	 * 
	 * //constructor for controller to pass around a screenList; public
	 * Controller(ArrayList<Task> screenList){ this.screenList = screenList; }
	 */

	static Storage storage = Storage.getInstance();

	// UI used for create command to be executed
	public static Command createCommand(String inputFromUser) {
		ArrayList<String> inputForAction = Parser.retrieveCommand(inputFromUser);
		Task task = null;
		
		@SuppressWarnings("unused")
        Command command;
		
		Storage storage = Storage.getInstance();
		int itemNum;
		switch (FlexiCommands.flexiCommands(inputForAction.get(0).toLowerCase())) {
		// if add, need to create task object for storage
		case "add":
			task = Parser.createTaskForAdd(inputForAction);
			return command = new Add(task, storage);
		case "update":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Update(itemNum, storage);
		case "delete":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Delete(itemNum, storage);
		case "exit":
			return command = new Exit();
		case "complete":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Complete(itemNum, storage);
		case "incomplete":
			itemNum =Integer.parseInt(inputForAction.get(1));
			return command = new Incomplete(itemNum, storage);
		default:
			return command = new Display(inputForAction, storage);
		}
	}

	// Testing
	/*
	 * public static final String TYPE_DEADLINE = "deadline"; public static
	 * final String TYPE_EVENT = "event"; public static final String
	 * TYPE_FLOATING = "floating"; public static void main(String arg[]) throws
	 * IOException{ Storage storage = Storage.getInstance(); Task task1 = new
	 * Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
	 * Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null,
	 * "02/02/2015", null, "1100", true); Task task3 = new Task(TYPE_EVENT,
	 * "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false); Task
	 * task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015",
	 * "03/02/2015", "0915", "1100", true); Task task5 = new Task(TYPE_FLOATING,
	 * "meet with bob", null, null, null, null, false); Task task6 = new
	 * Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
	 * Command command1 = new Add(task1, storage); Command command2 = new
	 * Add(task2, storage); Command command3 = new Add(task3, storage); Command
	 * command4 = new Add(task4, storage); Command command5 = new Add(task5,
	 * storage); Command command6 = new Add(task6, storage); command1.execute();
	 * command2.execute(); command3.execute(); command4.execute();
	 * command5.execute(); command6.execute();
	 * DataDisplay.displayList(storage.getTaskList());
	 * ArrayList<ItemForUserScreen> itemList = new
	 * ArrayList<ItemForUserScreen>(); itemList = getItemList(); for (int i =0;
	 * i < itemList.size();i++){
	 * System.out.println(itemList.get(i).getIfComplete()+"_"+itemList.get(i).
	 * getTaskType()+"_"+itemList.get(i).getPrintOnScreenMsg()); } OutputToUI
	 * outputToUI = initializeProgram();
	 * DataDisplay.printOutputToUI(outputToUI); }
	 */
	// Above is testing block

	public static ArrayList<ItemForUserScreen> getItemList() {
		ArrayList<String> printOnScreenMsgList = new ArrayList<String>();

		ArrayList<Task> taskList = storage.getTaskList();
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		for (int i = 0; i < taskList.size(); i++) {
			itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), taskList.get(i).getTaskType(),
					printOnScreenMsgList.get(i)));
		}
		return itemList;
	}

	public static OutputToUI initializeProgram() throws IOException {
		storage.regenerateTaskList(); //TODO, Involve this when storage is correct
		
		ArrayList<Task> taskList = Search.obtainTodaySummary(storage);
		history.setScreenList(taskList);
		OutputToUI outputToUI = new OutputToUI();
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		ArrayList<String> printOnScreenMsgList = DataDisplay.displayList(taskList);
		String typeOfScreen = "today";
		history.setCurrentScreen(typeOfScreen);
		String feedbackMsg = DataDisplay.feedback("init", 0);
		if (taskList.size() != 0) {
			for (int i = 0; i < taskList.size(); i++) {
				itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), taskList.get(i).getTaskType(),
						printOnScreenMsgList.get(i)));
			}
		}
		outputToUI.setFeedbackMsg(feedbackMsg);
		outputToUI.setItemList(itemList);
		outputToUI.setTypeOfScreen(typeOfScreen);
		return outputToUI;
	}
	
	public static OutputToUI refreshScreen() {
		OutputToUI outputToUI;
		String typeOfScreen = history.getCurrentScreen();
		ArrayList<String> displayInput = new ArrayList<String>();
		displayInput.add("display");
		displayInput.add(typeOfScreen);
		Command displayCmd = new Display(displayInput, storage);
		outputToUI = displayCmd.execute();
		return outputToUI;
	}
}
