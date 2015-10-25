//@author : Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.FlexiCommands;
import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Display implements Command {
	private ArrayList<String> userInput = new ArrayList<String>();
	private Storage storage;

	public Display(ArrayList<String> userInput, Storage storage) {
		this.userInput = userInput;
		this.storage = storage;
	}

	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		ArrayList<String> printOnScreenMsgList = new ArrayList<String>();
		ArrayList<Task> taskList = new ArrayList<Task>();
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		String feedbackMsg;
		String typeOfListView = null;
		switch (FlexiCommands.flexiDisplayCommands(userInput.get(1).toLowerCase())) {
		case "today":
			taskList = Search.obtainTodaySummary(storage);
			typeOfListView = "today";
			break;
		case "tomorrow":
			taskList = Search.obtainTommorrowSummary(storage);
			typeOfListView = "tomorrow";
			break;
		case "floating":
			taskList = Search.obtainFloatingTasks(storage);
			typeOfListView = "floating";
			break;
		case "event":
			taskList = Search.obtainEventTasks(storage);
			typeOfListView = "event";
			break;
		case "deadline":
			taskList = Search.obtainDeadlineTasks(storage);
			typeOfListView = "deadline";
			break;
		case "complete":
			taskList = Search.obtainAllCompleteTasks(storage);
			typeOfListView = "display complete";
			break;
		case "incomplete":
			taskList = Search.obtainAllIncompleteTasks(storage);
			typeOfListView = "display incomplete";
			break;
		case "all":
			taskList = Search.obtainAllTasks(storage);
			typeOfListView = "display all";
			break;
		}
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		for (int i = 0; i < taskList.size(); i++) {
			itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), taskList.get(i).getTaskType(),
					printOnScreenMsgList.get(i)));
		}
		feedbackMsg = "Display successfully";
		outputToUI.setFeedbackMsg(feedbackMsg);
		outputToUI.setItemList(itemList);
		outputToUI.setTypeOfListView(typeOfListView);
		return outputToUI;
	}

}
