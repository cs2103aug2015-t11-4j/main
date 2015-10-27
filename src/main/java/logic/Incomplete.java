//@Author:Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Incomplete implements Command{
	//private boolean ifComplete = false;
	private History history = History.getInstance();
	private int itemNum;
	private OutputToUI outputToUI = new OutputToUI();
	private Storage storage = Storage.getInstance();
	
	public Incomplete(int itemNum, Storage storage){
		this.itemNum = itemNum;
		this.storage = storage;
	}
	
	@Override
	public OutputToUI execute() {
		int code;
		Task task = Search.obtainTaskByItemNum(itemNum, history.getScreenList());
		code = storage.incompleteOneItem(task);
/*		for (int i = 0; i < storage.getTaskList().size(); i++){
			if (storage.getTaskList().get(i).equals(task)){
				storage.getTaskList().get(i).setCompleted(ifComplete);
			}
		}*/
		String feedbackMsg = DataDisplay.feedback("Incomplete", code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}
}
