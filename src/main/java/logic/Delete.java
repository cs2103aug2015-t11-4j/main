//@Author:Jiahuan
package main.java.logic;


import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Delete implements Command{

	//private Task task;
	private History history = History.getInstance();
	private int itemNum;
	private Storage storage;
	private ArrayList<Task> screenList;
	private Task task;
	
	public Delete(int itemNum, Storage storage){
		//this.task = task;
		this.storage = storage;
		this.screenList = history.getScreenList();
		Task task = Search.obtainTaskByItemNum(itemNum, screenList);
		this.task = task;
	}
	@Override
	public OutputToUI execute() {
		int code;
		OutputToUI outputToUI = new OutputToUI();

		code = storage.deleteOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("delete",code));
		return outputToUI;
		
	}
	@Override
	public OutputToUI undo() {
		int code;
		OutputToUI outputToUI = new OutputToUI();

		code = storage.addOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Undo",code));
		return outputToUI;
	}
	@Override
	public OutputToUI redo() {
		OutputToUI outputToUI = this.execute();
		return outputToUI;
	}

}
