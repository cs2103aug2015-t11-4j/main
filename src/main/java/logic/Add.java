//@@Author: Jiahuan
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

		code = storage.addOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		
		outputToUI = Controller.refreshScreen();
				
		outputToUI.setFeedbackMsg(DataDisplay.feedback("add",code));
		
/*		//Below is the part for Undo
		int itemNum = Search.obtainItemNumByTask(task, storage.getTaskList());
		Command cmd = new Delete(itemNum, storage);
		history.getUndoCommandList().push(cmd);*/
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
		OutputToUI outputToUI = this.execute();
		return outputToUI;
	}




}
