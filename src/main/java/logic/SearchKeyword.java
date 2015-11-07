//@@Author: wenbin
package main.java.logic;

import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class SearchKeyword implements Command {
	private ArrayList<String> inputForAction = new ArrayList<String>();
	private Storage storage;
	private History history = History.getInstance();

	public SearchKeyword(ArrayList<String> userInput, Storage storage) {
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
		
		taskList = Search.obtainSearchResults(inputForAction.get(1).toLowerCase(), storage);
		typeOfScreen = "search";
		
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		history.setScreenList(taskList);
		history.setCurrentScreen(typeOfScreen);
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
