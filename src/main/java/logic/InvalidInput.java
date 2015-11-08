/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputToUI redo() {
		// TODO Auto-generated method stub
		return null;
	}

}
