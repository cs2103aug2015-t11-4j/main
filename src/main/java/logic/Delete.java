//@Author:Jiahuan
package main.java.logic;


import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.storage.Storage;

public class Delete implements Command{

	private int itemNum;
	private Storage storage;
	
	public Delete(int itemNum, Storage storage){
		this.itemNum = itemNum;
		this.storage = storage;
	}
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();

		storage.deleteOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI.setFeedbackMsg(DataDisplay.feedback("add",0));
		return outputToUI;
		
	}

}
