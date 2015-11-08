/*
 * @@author A0104278 
 */

package main.java.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

/*
 * This class is to delete task
 * By creating the command with item number and delete keyword input
 * Pass to storage to delete respective task object
 */

public class Delete implements Command {
	private static final Logger log = Logger.getLogger(Delete.class.getName());
	private static final String KEYWORD_DELETE = "Delete";
	private static final String KEYWORD_UNDO = "Undo";
	private static final String KEYWORD_REDO = "Redo";
	private static final String KEYWORD_DELETE_RECUR ="Delete recurring tasks";
	private History history = History.getInstance();
	private int itemNum;
	private Storage storage;
	private ArrayList<Task> screenList;
	private Task task;
	private ArrayList<Task> recurTaskList;
	private String deletePara;

	public Delete(int itemNum, String deletePara, Storage storage) {
		// this.task = task;
		this.itemNum = itemNum;
		this.storage = storage;
		this.deletePara = deletePara;
		this.screenList = history.getScreenList();

		Task task = Search.obtainTaskByItemNum(itemNum, screenList);
		this.task = task;
		if (deletePara.equals("all")) {
			recurTaskList = Search.obtainRecurTaskListByItemNum(itemNum, screenList);
		} else {
			recurTaskList = new ArrayList<Task>();
		}
	}


	/*
	 * To delete the task inside the storage
	 * 
	 */
	@Override
	public OutputToUI execute() {
		log.log(Level.INFO, "start to execute delete");
		int code;
		OutputToUI outputToUI = new OutputToUI();

		if (task.equals(new Task())) {
			log.log(Level.WARNING, "task in not valid");
			code = 10;
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum), code));
			return outputToUI;
		} else if (recurTaskList.isEmpty() && deletePara.equals("all")) {
			log.log(Level.WARNING, "task in not valid");
			code = 11;
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum), code));
			return outputToUI;
		} else if (!recurTaskList.isEmpty()) {
			log.log(Level.INFO, "start delete recur in storage");
			code = -1;

			int size = recurTaskList.size();
			for (int i = 0; i < size; i++) {
				Task recurTask = recurTaskList.get(i);
				code = storage.deleteOneItem(recurTask);
			}
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_DELETE_RECUR, code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
			System.out.println("Inside delete recur");
			DataDisplay.printUndoCommandList();
			DataDisplay.printRedoCommandList();
			return outputToUI;
		} else {
			log.log(Level.INFO, "start delete in storage");
			code = storage.deleteOneItem(task);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_DELETE, code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
			System.out.println("Inside delete ");
			DataDisplay.printUndoCommandList();
			DataDisplay.printRedoCommandList();
		}
		return outputToUI;

	}

	/*
	 * To undo this delete action. To add the task inside the storage.
	 * 
	 */

	@Override
	public OutputToUI undo() {
		log.log(Level.INFO, "start to execute delete.undo");
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!recurTaskList.isEmpty()) {
			log.log(Level.INFO, "start add recur in storage");
			code = -1;
			try {
				history.setRecurID();
			} catch (IOException e) {
				log.log(Level.WARNING, "Invalid recur ID");
			}
			int recurID = history.getNextRecurID();
			int size = recurTaskList.size();
			for (int i = 0; i < size; i++) {
				recurTaskList.get(i).setRecurringID(recurID);
				Task recurTask = recurTaskList.get(i);
				code = storage.addOneItem(recurTask);
			}
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_UNDO, code));
			System.out.println("Inside delete undo recur");
			DataDisplay.printUndoCommandList();
			DataDisplay.printRedoCommandList();
			return outputToUI;
		}
		log.log(Level.INFO, "start add in storage");
		code = storage.addOneItem(task);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_UNDO, code));
		System.out.println("Inside delete undo ");
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		return outputToUI;
	}

	/*
	 * To redo this delete action. To delete the task back inside the storage.
	 * 
	 */

	@Override
	public OutputToUI redo() {
		log.log(Level.INFO, "start to execute add.redo");
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (task.equals(new Task())) {
			code = 10;
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum), code));
			return outputToUI;
		} else if (recurTaskList.isEmpty() && deletePara.equals("all")) {
			code = 11;
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(String.valueOf(itemNum), code));
			return outputToUI;
		} else if (!recurTaskList.isEmpty()) {
			log.log(Level.INFO, "start delete recur in storage");
			code = -1;

			int size = recurTaskList.size();
			for (int i = 0; i < size; i++) {
				Task recurTask = recurTaskList.get(i);
				code = storage.deleteOneItem(recurTask);
			}
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_REDO, code));
			history.pushCommandToUndoList(this);
			System.out.println("Inside delete redo recur");
			DataDisplay.printUndoCommandList();
			DataDisplay.printRedoCommandList();
			return outputToUI;
		}
		log.log(Level.INFO, "start delete in storage");
		code = storage.deleteOneItem(task);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_REDO, code));
		history.pushCommandToUndoList(this);
		System.out.println("Inside delete redo");
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		return outputToUI;
	}
	// @@author A0104278-unused
	/*
	 * if (itemNum == 0){ code = -1; outputToUI = Controller.refreshScreen();
	 * outputToUI.setFeedbackMsg(DataDisplay.feedback("Delete",code));
	 * history.pushCommandToUndoList(this); history.clearRedoList(); return
	 * outputToUI; }else
	 */

}
