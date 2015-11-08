//@@author Jiahuan
package main.java.logic;


import java.io.IOException;
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
	private ArrayList<Task> recurTaskList;
	private String deletePara;
	
	public Delete(int itemNum, String deletePara, Storage storage){
		//this.task = task;
		this.itemNum = itemNum;
		this.storage = storage;
		this.deletePara = deletePara;
		this.screenList = history.getScreenList();
		/*Task task = new Task();
		if (itemNum != 0){
			task = Search.obtainTaskByItemNum(itemNum, screenList);
		}*/
        Task task = Search.obtainTaskByItemNum(itemNum, screenList);
		this.task = task;
		if (deletePara.equals("all")){
			recurTaskList = Search.obtainRecurTaskListByItemNum(itemNum, screenList);
		}else{
			recurTaskList = new ArrayList<Task>();
		}
	}
	@Override
	public OutputToUI execute() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		/*if (itemNum == 0){
			code = -1;
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback("Delete",code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
			return outputToUI;
		}else*/ 
		if (task.equals(new Task())){
			code = 10; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		} else if (recurTaskList.isEmpty()){
			code = 11; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		} else if (!recurTaskList.isEmpty()){
			code = -1;
			
			int size = recurTaskList.size();
			for (int i = 0; i < size; i++){
				Task recurTask = recurTaskList.get(i);
				code = storage.deleteOneItem(recurTask);
			}
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback("Delete recurring tasks",code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
			return outputToUI;
		} 
		
		code = storage.deleteOneItem(task); 
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Delete",code));
		history.pushCommandToUndoList(this);
		history.clearRedoList();
		return outputToUI;
		
	}
	@Override
	public OutputToUI undo() {
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!recurTaskList.isEmpty()){
			code = -1;
			try {
				history.setRecurID();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int recurID = history.getNextRecurID();
			int size = recurTaskList.size();
			for (int i = 0; i < size; i++){
				recurTaskList.get(i).setRecurringID(recurID);
				Task recurTask = recurTaskList.get(i);
				code = storage.addOneItem(recurTask);
			}
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback("Delete recurring tasks",code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
			return outputToUI;
		}
		
		code = storage.addOneItem(task); 
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
