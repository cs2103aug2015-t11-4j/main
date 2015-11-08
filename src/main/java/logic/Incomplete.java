/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;
/*
 * This class is for incomplete task
 * By creating the command with item Number
 * Pass to storage for update respective task object
 */
public class Incomplete implements Command{
	//private boolean ifComplete = false;
	private History history = History.getInstance();
	//private int itemNum;
	private OutputToUI outputToUI = new OutputToUI();
	private Storage storage = Storage.getInstance();
	private Task task;
	private int itemNum;
	
	public Incomplete(int itemNum, Storage storage){
		this.itemNum = itemNum;
		this.storage = storage;
		Task task = Search.obtainTaskByItemNum(itemNum, history.getScreenList());
		this.task = task;
		
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		//un-used
		//System.out.println("***");
		//DataDisplay.displayList(history.getScreenList());
		//System.out.println("***");
		
		code = storage.incompleteOneItem(task);
		if (task.equals(new Task())){
			code = 10; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		}
		String feedbackMsg = DataDisplay.feedback("Incomplete", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.pushCommandToUndoList(this);
		history.clearRedoList();
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		int code;
		code = storage.completeOneItem(task);
		String feedbackMsg = DataDisplay.feedback("Undo", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}

	@Override
	public OutputToUI redo() {
		int code;
		//un-used
		//System.out.println("***");
		//DataDisplay.displayList(history.getScreenList());
		//System.out.println("***");
		
		code = storage.incompleteOneItem(task);
		if (task.equals(new Task())){
			code = 10; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		}
		String feedbackMsg = DataDisplay.feedback("Redo", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.pushCommandToUndoList(this);
		return outputToUI;
	}
}
