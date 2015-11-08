/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Add implements Command{
	private Task task; 
	private Storage storage;
	private History history = History.getInstance();

	//private DataDisplay dataDisplay;
	
	public Add(Task task, Storage storage){
		this.task=task;
		this.storage = storage;
//		this.dataDisplay = dataDisplay;
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!task.getIsDateTimeValid()){
			outputToUI = Controller.refreshScreen();
			//DataDisplay.printOutputToUI(outputToUI);
			outputToUI.setFeedbackMsg(DataDisplay.feedback("Add",-1));
			DataDisplay.printOutputToUI(outputToUI);
			return outputToUI;
		}
		code = storage.addOneItem(task); 
		
		outputToUI = Controller.refreshScreen();
		//DataDisplay.printOutputToUI(outputToUI);
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Add",code));
		history.pushCommandToUndoList(this);
		history.clearRedoList();
		//DataDisplay.printOutputToUI(outputToUI);
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		int code;
		
		OutputToUI outputToUI = new OutputToUI();
		
		code = storage.deleteOneItem(task);
		
		outputToUI = Controller.refreshScreen();
		
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Undo", code));
		return outputToUI;
	}

	@Override
	public OutputToUI redo() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!task.getIsDateTimeValid()){
			outputToUI = Controller.refreshScreen();
			//DataDisplay.printOutputToUI(outputToUI);
			outputToUI.setFeedbackMsg(DataDisplay.feedback("Add",-1));
			DataDisplay.printOutputToUI(outputToUI);
			return outputToUI;
		}
		code = storage.addOneItem(task); 
		
		outputToUI = Controller.refreshScreen();
		//DataDisplay.printOutputToUI(outputToUI);
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Add",code));
		history.pushCommandToUndoList(this);
		//DataDisplay.printOutputToUI(outputToUI);
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Redo", code));
		return outputToUI;
	}




}
