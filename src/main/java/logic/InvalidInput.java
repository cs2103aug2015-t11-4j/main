/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;

/*
 * This class is for handle invalid inputs
 * By creating the command with user input
 * Pass to storage to acknowledge the invalid input
 */

public class InvalidInput implements Command{
	@SuppressWarnings("unused")
    private History history;
	
	private String invalidInput;
	
	
	public InvalidInput(String invalidInput){
		history = History.getInstance();
		this.invalidInput = invalidInput;
	}
	
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = Controller.refreshScreen();
		
		outputToUI.setFeedbackMsg(DataDisplay.feedback(invalidInput,1));
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
