//@@Author: Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.DateAndTime;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateStartDate implements Command{

	private Storage storage = Storage.getInstance();
	private String newStartDate;//TODO change each copy
	//private int itemNum;
	private Task oldTask;
	private Task newTask;
	private ArrayList<Task> screenList;
	private History history = History.getInstance();
	
	
	public UpdateStartDate(int itemNum, String newStartDate){
		this.newStartDate = newStartDate;
		screenList = history.getScreenList();
		this.oldTask = Search.obtainTaskByItemNum(itemNum, screenList);
		if (oldTask.getStartDate().equals("-")||DateAndTime.reformatDate(newStartDate).equals("invalid date format")){
			newTask = new Task();
		} else {
		newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
				oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
				oldTask.getIsDateTimeValid()/*, int newRecurringID*/);
		newTask.setStartDate(DateAndTime.reformatDate(newStartDate));
		}
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		
		String feedbackMsg;
		//If empty, return feedback msg saying task description cannot be empty
		if (newStartDate.isEmpty()){
			//System.out.println("Inside empty");
			code = 5;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} else if (newTask.equals(new Task())){
			code = 6;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		}
		System.out.printf(newStartDate);
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
