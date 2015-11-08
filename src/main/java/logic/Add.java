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
 * This class is for adding task
 * By creating the command with task input
 * Pass to storage for add respective task object
 */

public class Add implements Command {
	private static final Logger log = Logger.getLogger(Add.class.getName());
	private static final String KEYWORD_ADD = "Add";
	private static final String KEYWORD_UNDO = "Undo";
	private static final String KEYWORD_REDO = "Redo";
	private Task task;
	private Storage storage;
	private History history = History.getInstance();

	public Add(Task task, Storage storage) {
		this.task = task;
		this.storage = storage;

	}

	/*
	 * To add the task inside the storage
	 * 
	 */
	@Override
	public OutputToUI execute() {
		log.log(Level.INFO, "start to execute add");
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!task.getIsDateTimeValid()) {
			log.log(Level.WARNING, "task in not valid");
			outputToUI = Controller.refreshScreen();
			// DataDisplay.printOutputToUI(outputToUI);
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_ADD, -1));
			DataDisplay.printOutputToUI(outputToUI);
			return outputToUI;
		} else if (task.getIsDateTimeValid()) {
			log.log(Level.INFO, "start add into storage");
			code = storage.addOneItem(task);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_ADD, code));
			history.pushCommandToUndoList(this);
			history.clearRedoList();
		}
		return outputToUI;
	}

	/*
	 * To undo this add action. To delete the task inside the storage.
	 * 
	 */

	@Override
	public OutputToUI undo() {
		log.log(Level.INFO, "start to undo add");
		int code;

		OutputToUI outputToUI = new OutputToUI();
		log.log(Level.INFO, "start delete into storage");
		code = storage.deleteOneItem(task);

		outputToUI = Controller.refreshScreen();

		outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_UNDO, code));
		return outputToUI;
	}

	/*
	 * To redo this add action. To add the task back inside the storage.
	 * 
	 */

	@Override
	public OutputToUI redo() {
		log.log(Level.INFO, "start to redo add");
		int code;
		OutputToUI outputToUI = new OutputToUI();
		if (!task.getIsDateTimeValid()) {
			log.log(Level.WARNING, "task in not valid");
			outputToUI = Controller.refreshScreen();
			// DataDisplay.printOutputToUI(outputToUI);
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_ADD, -1));
			DataDisplay.printOutputToUI(outputToUI);
			return outputToUI;
		} else if (task.getIsDateTimeValid()) {
			log.log(Level.INFO, "start add into storage");
			code = storage.addOneItem(task);
			outputToUI = Controller.refreshScreen();
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_ADD, code));
			history.pushCommandToUndoList(this);
			outputToUI.setFeedbackMsg(DataDisplay.feedback(KEYWORD_REDO, code));
		}
		return outputToUI;
	}

}
