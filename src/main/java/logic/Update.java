/*
 * @@author A0104278 
 */

package main.java.logic;

import java.util.ArrayList;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

/*
 * This class is for update
 * By creating the command with item number
 */

public class Update implements Command{
	private int itemNum;
	private Storage storage;
	private History history = History.getInstance();
	private ArrayList<Task> screenList;
	private Task task;
	
	public Update(int itemNum, Storage storage){
		this.itemNum=itemNum;
		this.storage = storage;
		screenList = history.getScreenList();
		Task task = new Task();
		if (itemNum !=0){
			task = Search.obtainTaskByItemNum(itemNum, screenList);
		}
		
		this.task = task;
	}
	
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		int code;
		if (itemNum == 0){
			code = -1;
			String feedbackMsg = DataDisplay.feedback("Update", code);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		}else if (task.equals(new Task())){
			code = 10; 
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum),code));
			return outputToUI;
		}
		storage.deleteOneItem(task);
		String inputBoxMsg = DataDisplay.displayTaskNeedForUpdate(task);
		String feedbackMsg = "Please edit the task to update"; 
		outputToUI = Controller.refreshScreen();
		outputToUI.setInputBoxMsg(inputBoxMsg);
		outputToUI.setFeedbackMsg(feedbackMsg);
		history.clearRedoList();
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		Command command = history.popCommandToUndoList();
		command.undo();
		OutputToUI outputToUI = Controller.refreshScreen();
		int code = storage.addOneItem(task);
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Undo", code));
		outputToUI = Controller.refreshScreen();
		return outputToUI;
	}

	@Override
	public OutputToUI redo() {
		return null;
	}

}

//@@Author: Jiahuan
//-unused
//unused as a new method of update is implemented

/*package main.java.logic;

import main.java.resources.Task;
import main.java.storage.Storage;
//Second step of update, user change its message and enter
public class UpdateWithTask implements Command{
  private Task task;
  
  public UpdateWithTask (Task task){
      this.task = task;
  }
  
  //recreate the task requested by user
  @Override
  public void execute() {
      Storage.addOneItem(task);
  }

}
*/