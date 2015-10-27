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
	public Delete(int itemNum, Storage storage){
		//this.task = task;
		this.storage = storage;
		this.itemNum = itemNum;
	}
	@Override
	public OutputToUI execute() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		screenList = history.getScreenList();
		Task task = Search.obtainTaskByItemNum(itemNum, screenList);
		code = storage.deleteOneItem(task); //TODO: Storage shall make its methods all non-static
								  //TODO: Storage returns success or not, a if loop to return feedback respectively
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("delete",code));
		return outputToUI;
		
	}

}
