//@author : Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.FlexiCommands;
import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

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
			taskList = Search.obtainTommorrowSummary(storage);
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
		case "all":
			taskList = Search.obtainAllTasks(storage);
			typeOfScreen = "all";
			break;
		}
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		history.setScreenList(taskList);
		for (int i = 0; i < taskList.size(); i++) {
			itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), taskList.get(i).getTaskType(),
					printOnScreenMsgList.get(i)));
		}
		feedbackMsg = "Display successfully";
		outputToUI.setFeedbackMsg(feedbackMsg);
		outputToUI.setItemList(itemList);
		outputToUI.setTypeOfScreen(typeOfScreen);
		return outputToUI;
	}

}
