//@@Author: Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.DateAndTime;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateStartTime implements Command{

	private Storage storage = Storage.getInstance();
	private String newStartTime;//TODO change each copy
	//private int itemNum;
	private Task oldTask;
	private Task newTask;
	private ArrayList<Task> screenList;
	private History history = History.getInstance();
	
	
	public UpdateStartTime(int itemNum, String newStartTime){
		this.newStartTime = newStartTime;
		screenList = history.getScreenList();
		this.oldTask = Search.obtainTaskByItemNum(itemNum, screenList);
		if (oldTask.getStartTime().equals("-")||DateAndTime.reformatTime(newStartTime).equals("invalid time format")){
			newTask = new Task();
		} else {
		newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
				oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
				oldTask.getIsDateTimeValid(), oldTask.getRecurringID());
		newTask.setStartTime(DateAndTime.reformatTime(newStartTime));
		}
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		
		String feedbackMsg;
		//If empty, return feedback msg saying task description cannot be empty
		if (newStartTime.isEmpty()){
			//System.out.println("Inside empty");
			code = 3;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} else if (newTask.equals(new Task())){
			code = 4;
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
