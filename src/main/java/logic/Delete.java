//@Author:Jiahuan
package main.java.logic;


import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Delete implements Command{

	private Task task;
	private Storage storage;
	
	public Delete(Task task, Storage storage){
		this.task = task;
		this.storage = storage;
	}
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();

		storage.deleteOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI.setFeedbackMsg(DataDisplay.feedback("delete",0));
		return outputToUI;
		
	}

}
