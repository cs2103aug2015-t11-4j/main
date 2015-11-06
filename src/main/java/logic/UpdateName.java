//@@Author: Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateName implements Command{

	private Storage storage = Storage.getInstance();
	private String newName;
	//private int itemNum;
	private Task oldTask;
	private Task newTask;
	private ArrayList<Task> screenList;
	private History history = History.getInstance();
	
	
	public UpdateName(int itemNum, String newName ){
		this.newName = newName;
		screenList = history.getScreenList();
		this.oldTask = Search.obtainTaskByItemNum(itemNum, screenList);
		newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
				oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
				oldTask.getIsDateTimeValid()/*, int newRecurringID*/);
		newTask.setTaskDescription(newName);
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		
		String feedbackMsg;
		//If empty, return feedback msg saying task description cannot be empty
		if (newName.isEmpty()){
			//System.out.println("Inside empty");
			code = 2;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} 
		storage.deleteOneItem(oldTask);
		System.out.println("Ouside empty");
		storage.addOneItem(newTask);
		OutputToUI outputToUI = Controller.refreshScreen();
		code = 0;
		feedbackMsg = DataDisplay.feedback("Update", code);
		outputToUI.setFeedbackMsg(feedbackMsg);

		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		int code;
		
		String feedbackMsg;
		storage.deleteOneItem(newTask);
		storage.addOneItem(oldTask);
		OutputToUI outputToUI = Controller.refreshScreen();
		code = 0;
		feedbackMsg = DataDisplay.feedback("Update", code);
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}

	@Override
	public OutputToUI redo() {
		OutputToUI outputToUI = this.execute();
		return outputToUI;
	}

}
