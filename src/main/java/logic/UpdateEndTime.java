//@@author Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.DateAndTime;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateEndTime implements Command{
	private Storage storage = Storage.getInstance();
	private String newEndTime;//TODO change each copy
	private int itemNum;
	private Task oldTask;
	private Task newTask;
	private ArrayList<Task> screenList;
	private History history = History.getInstance();
	private ArrayList<Task> oldRecurTaskGroup = new ArrayList<Task>();
	private ArrayList<Task> newRecurTaskGroup = new ArrayList<Task>();
	
	
	public UpdateEndTime(int itemNum, String newEndTime){
		this.newEndTime = newEndTime;
		this.itemNum = itemNum;
		screenList = history.getScreenList();
		this.oldTask = Search.obtainTaskByItemNum(itemNum, screenList);
		if (oldTask.getEndTime().equals("-")||DateAndTime.reformatTime(newEndTime).equals("invalid time format")||!DateAndTime.isValidUpdateET(oldTask, newEndTime)){
			newTask = new Task();
		} 
		else if(oldTask.getRecurringID() == 0) {
			newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
					oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
					oldTask.getIsDateTimeValid(), oldTask.getRecurringID());
			newTask.setEndTime(DateAndTime.reformatTime(newEndTime));
			history.pushCommandToUndoList(this);
		}
		//@@author: A0124524N; wenbin 
		else {
			this.oldRecurTaskGroup = Search.obtainRecurTaskListByItemNum(itemNum, screenList);
			for(int i=0; i<oldRecurTaskGroup.size(); i++) {
				this.oldTask = oldRecurTaskGroup.get(i);
				newTask = new Task (oldTask.getTaskType(), oldTask.getTaskDescription(), oldTask.getStartDate(), oldTask.getEndDate(),
						oldTask.getStartTime(), oldTask.getEndTime(), oldTask.getIsCompleted(),
						oldTask.getIsDateTimeValid(), oldTask.getRecurringID());
				newTask.setEndTime(DateAndTime.reformatTime(newEndTime));
				this.newRecurTaskGroup.add(newTask);
			}
			
		}
	}
	//@@author
	//@@author Jiahuan
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
		if (newEndTime.isEmpty()){
			//System.out.println("Inside empty");
			code = 7;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} else if (newTask.equals(new Task())){
			code = 8;
			feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		}
		if(this.oldRecurTaskGroup.isEmpty()) {
			storage.deleteOneItem(oldTask);
			System.out.println("Ouside empty");
			storage.addOneItem(newTask);
		}
		//@@author: A0124524N; wenbin 
		else {
			for(int i=0; i<this.newRecurTaskGroup.size(); i++) {
				storage.deleteOneItem(oldRecurTaskGroup.get(i));
				System.out.println("Outside empty");
				storage.addOneItem(newRecurTaskGroup.get(i));
			}
		}
		//@@Author: Jiahuan
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
