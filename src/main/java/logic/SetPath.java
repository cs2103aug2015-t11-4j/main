/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.storage.Storage;
/*
 * This class is for set new directory
 * By creating the command with task input of the new path
 * Pass to storage for update directory
 */
public class SetPath implements Command{
	private Storage storage = Storage.getInstance();
	private History history = History.getInstance();
	private String newPath;
	
	public SetPath(String newPath){
		this.newPath = newPath;
	}
	
	@Override
	public OutputToUI execute() {
	    int code;
	    
		code = storage.changeDirectory(newPath);
		OutputToUI outputToUI = Controller.refreshScreen();
		String feedbackMsg = DataDisplay.feedback("Set new directory", code);
		outputToUI.setFeedbackMsg(feedbackMsg);
		return outputToUI;
	}

	@Override
	public OutputToUI undo() {
		return null;
	}

	@Override
	public OutputToUI redo() {
		return null;
	}

}
