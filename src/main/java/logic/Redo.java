/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
/*
 * This class is for redo task
 * By creating the command with no input
 * Redo the command
 */
public class Redo implements Command{
	private History history = History.getInstance();
	
	public Redo(){}
	
	@Override
	public OutputToUI execute() {
		int code = 0;
		if (history.getRedoCommandList().isEmpty()){
			OutputToUI outputToUI = Controller.refreshScreen();
			String feedbackMsg = DataDisplay.feedback("Redo", 9);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		}
		Command command = history.popCommandToRedoList();
		OutputToUI outputToUI = command.redo();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Redo", code));
		
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
