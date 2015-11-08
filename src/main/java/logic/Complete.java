//@@author Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Complete implements Command{
	//private boolean ifComplete = true;
	private History history = History.getInstance();
	//private int itemNum;
	private OutputToUI outputToUI = new OutputToUI();
	private Storage storage = Storage.getInstance();
	private Task task;
	
	public Complete(int itemNum, Storage storage){
		//this.itemNum = itemNum;
		this.storage = storage;
		Task task = Search.obtainTaskByItemNum(itemNum, history.getScreenList());
		this.task = task;
	}
	
	
	@Override
	public OutputToUI execute() {
		int code;
		
		code = storage.completeOneItem(task);
		/*for (int i = 0; i < storage.getTaskList().size(); i++){
			if (storage.getTaskList().get(i).equals(task)){
				storage.getTaskList().get(i).setCompleted(ifComplete);
			}
		}*/
		String feedbackMsg = DataDisplay.feedback("Complete", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.pushCommandToUndoList(this);
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		int code;
		code = storage.incompleteOneItem(task);
		String feedbackMsg = DataDisplay.feedback("Undo", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.clearRedoList();
		return outputToUI;
	}


	@Override
	public OutputToUI redo() {
		OutputToUI outputToUI = this.execute();
		
		return outputToUI;
	}
	
	
	
}
