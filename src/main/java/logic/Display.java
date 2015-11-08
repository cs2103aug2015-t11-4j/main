/*
 * @@author A0104278 
 */

package main.java.logic;

import java.util.ArrayList;

import main.java.parser.FlexiCommands;
import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

/*
 * This class is to display task
 * By creating the command with user input
 * Pass to UI for display respective task objects
 */

public class Display implements Command {
	private ArrayList<String> inputForAction = new ArrayList<String>();
	private Storage storage;
	private History history = History.getInstance();

	public Display(ArrayList<String> userInput, Storage storage) {
		this.inputForAction = userInput;
		this.storage = storage;
	}

	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		ArrayList<String> printOnScreenMsgList = new ArrayList<String>();
		ArrayList<Task> taskList = new ArrayList<Task>();
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		String feedbackMsg;
		String typeOfScreen = null;
		switch (FlexiCommands.flexiDisplayCommands(inputForAction.get(1).toLowerCase())) {
		//switch (inputForAction.get(1).toLowerCase()) {
		case "today":
			taskList = Search.obtainTodaySummary(storage);
			typeOfScreen = "today";
			break;
		case "tomorrow":
			taskList = Search.obtainTomorrowSummary(storage);
			typeOfScreen = "tomorrow";
			break;
		case "floating":
			taskList = Search.obtainFloatingTasks(storage);
			typeOfScreen = "floating";
			break;
		case "event":
			taskList = Search.obtainEventTasks(storage);
			typeOfScreen = "event";
			break;
		case "deadline":
			taskList = Search.obtainDeadlineTasks(storage);
			typeOfScreen = "deadline";
			break;
		case "complete":
			taskList = Search.obtainAllCompleteTasks(storage);
			typeOfScreen = "complete";
			break;
		case "incomplete":
			taskList = Search.obtainAllIncompleteTasks(storage);
			typeOfScreen = "incomplete";
			break;
		case "search":
		/*
			outputToUI = history.gerSearchCommand().execute();
			itemList = outputToUI.getItemList();
			typeOfScreen = outputToUI.getTypeOfScreen();
			
			break;
        */
		case "all":
			taskList = Search.obtainAllTasks(storage);
			typeOfScreen = "all";
			break;
		case "help":
			typeOfScreen = "help";
			break;
		case "invalid display":
		default:
			typeOfScreen = "null";
			break;
		}
		
		//System.out.println("Inside display.execute() : ");
		
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		history.setScreenList(taskList);
		history.setCurrentScreen(typeOfScreen);
		if (!taskList.isEmpty()){
			for (int i = 0; i < taskList.size(); i++) {
				itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), 
						taskList.get(i).getTaskType(), printOnScreenMsgList.get(i)));
			}
		}
		if (typeOfScreen == "null"){
			feedbackMsg = DataDisplay.feedback("Display", -1);
		} else if(typeOfScreen == "help"){
			feedbackMsg = "Help is displayed";
		} else {
			feedbackMsg = DataDisplay.feedback("Display", 0);
		}
		outputToUI.setFeedbackMsg(feedbackMsg);
		outputToUI.setItemList(itemList);
		outputToUI.setTypeOfScreen(typeOfScreen);
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputToUI redo() {
		// TODO Auto-generated method stub
		return null;
	}

}
