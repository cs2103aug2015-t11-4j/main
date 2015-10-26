//@Author:Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Complete implements Command{
	private boolean ifComplete = true;
	private History history = History.getInstance();
	private int itemNum;
	private OutputToUI outputToUI = new OutputToUI();
	private Storage storage = Storage.getInstance();
	
	public Complete(int itemNum, Storage storage){
		this.itemNum = itemNum;
		this.storage = storage;
	}
	
	@Override
	public OutputToUI execute() {
		Task task = Search.obtainTaskByItemNum(itemNum, history.getScreenList());
		for (int i = 0; i < storage.getTaskList().size(); i++){
			if (storage.getTaskList().get(i).equals(task)){
				storage.getTaskList().get(i).setCompleted(ifComplete);
			}
		}
		String feedbackMsg = DataDisplay.feedback("Complete", 0);
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}
	
	
	
}
