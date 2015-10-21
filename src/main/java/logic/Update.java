//@Author:Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Update implements Command{
	private int itemNum;
	private Storage storage;
	
	public Update(int itemNum, Storage storage){
		this.itemNum=itemNum;
		this.storage = storage;
	}
	
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		Task task = Search.obtainTaskByItemNum(itemNum); // Put in history so it can be restored
		storage.deleteOneItem(itemNum);
		//TODO: Display this task on to the screen
		String inputBoxMsg = DataDisplay.displayTaskNeedForUpdate(task);
		String feedbackMsg = "Please edit the task to update"; //TODO: Include in DataDisplay.feedback
		outputToUI.setInputBoxMsg(inputBoxMsg);
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}

}
