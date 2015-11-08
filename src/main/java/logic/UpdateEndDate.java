//@@author Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.DateAndTime;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateEndDate implements Command{
	private Storage storage = Storage.getInstance();
	private String newEndDate;//TODO change each copy
	private int itemNum;
	private Task oldTask;
	private Task newTask;
	private ArrayList<Task> screenList;
	private History history = History.getInstance();
	
	
	public UpdateEndDate(int itemNum, String newEndDate){
		this.newEndDate = newEndDate;
		this.itemNum = itemNum;
		screenList = history.getScreenList();
		this.oldTask = Search.obtainTaskByItemNum(itemNum, screenList);
		if (oldTask.getEndDate().equals("-")||DateAndTime.reformatDate(newEndDate).equals("invalid date format")){
			newTask = new Task();
		} else {
		newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
				oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
				oldTask.getIsDateTimeValid(), oldTask.getRecurringID());
		newTask.setEndDate(DateAndTime.reformatDate(newEndDate));
		}
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		String feedbackMsg;
		if (oldTask.equals(new Task())){
			code = 10; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		}
		//If empty, return feedback msg saying task description cannot be empty
		if (newEndDate.isEmpty()){
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
		System.out.printf(newEndDate);
		storage.deleteOneItem(oldTask);
		System.out.println("Ouside empty");
		storage.addOneItem(newTask);
		outputToUI = Controller.refreshScreen();
		code = 0;
		feedbackMsg = DataDisplay.feedback("Update", code);
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.pushCommandToUndoList(this);
		history.clearRedoList();
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
