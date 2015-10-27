//@Author:Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Add implements Command{
	private Task task; 
	private Storage storage;

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
		outputToUI.setFeedbackMsg(DataDisplay.feedback("add",code));
		return outputToUI;
	}

}
