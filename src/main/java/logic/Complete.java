/*
 * @@author A0104278 
 */

package main.java.logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

/*
 * This class is for completing task
 * By creating the command with item number input
 * Pass to storage for update respective task object
 */

public class Complete implements Command {
	private static final Logger log = Logger.getLogger(Complete.class.getName());
	private static final String KEYWORD_COMPLETE = "Complete";
	private static final String KEYWORD_UNDO = "Undo";
	private static final String KEYWORD_REDO = "Redo";
	private History history = History.getInstance();
	private int itemNum;
	private OutputToUI outputToUI = new OutputToUI();
	private Storage storage = Storage.getInstance();
	private Task task;

	public Complete(int itemNum, Storage storage) {
		this.itemNum = itemNum;
		this.storage = storage;
		Task task = Search.obtainTaskByItemNum(itemNum, history.getScreenList());
		this.task = task;
	}

	/*
	 * To complete the task inside the storage
	 * 
	 */
	@Override
	public OutputToUI execute() {
		log.log(Level.INFO, "start to execute complete");
		int code;
		if (task.equals(new Task())) {
			log.log(Level.WARNING, "task in not valid");
			code = 10;
			String feedbackMsg = DataDisplay.feedback(String.valueOf(itemNum), code);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} else if (!task.equals(new Task())) {
			log.log(Level.INFO, "start complete in storage");
			code = storage.completeOneItem(task);
			String feedbackMsg = DataDisplay.feedback(KEYWORD_COMPLETE, code);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(feedbackMsg);
			history.pushCommandToUndoList(this);
			// DataDisplay.printUndoCommandList();
			history.clearRedoList();
		}
		return outputToUI;
	}

	/*
	 * To undo this complete action. To incomplete the task inside the storage.
	 * 
	 */

	@Override
	public OutputToUI undo() {
		log.log(Level.INFO, "start to execute undo");
		int code;
		task.setCompleted(true);
		code = storage.incompleteOneItem(task);
		String feedbackMsg = DataDisplay.feedback(KEYWORD_UNDO, code);
		outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}

	/*
	 * To redo this add action. To complete the task back inside the storage.
	 * 
	 */
	@Override
	public OutputToUI redo() {
		log.log(Level.INFO, "start to execute redo");
		int code;
		if (task.equals(new Task())) {
			log.log(Level.WARNING, "task in not valid");
			code = 10;
			String feedbackMsg = DataDisplay.feedback(String.valueOf(itemNum), code);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		} else if (!task.equals(new Task())) {
			log.log(Level.INFO, "start add into storage");
			task.setCompleted(false);
			code = storage.completeOneItem(task);
			String feedbackMsg = DataDisplay.feedback(KEYWORD_REDO, code);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(feedbackMsg);
			history.pushCommandToUndoList(this);
		}
		return outputToUI;
	}

	// unused due to change of plan
	/*
	 * for (int i = 0; i < storage.getTaskList().size(); i++){ if
	 * (storage.getTaskList().get(i).equals(task)){
	 * storage.getTaskList().get(i).setCompleted(ifComplete); } }
	 */

}
