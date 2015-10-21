//@Author:Jiahuan
package main.java.logic;


import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Delete implements Command{

	//private Task task;
	
	private int itemNum;
	private Storage storage;
	
	public Delete(int itemNum, Storage storage){
		//this.task = task;
		this.storage = storage;
		this.itemNum = itemNum;
	}
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();

		storage.deleteOneItem(itemNum); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI.setFeedbackMsg(DataDisplay.feedback("delete",0));
		return outputToUI;
		
	}

}
