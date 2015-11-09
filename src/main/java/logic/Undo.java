/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
/*
 * This class is for undo
 * By creating the command with no input
 */
public class Undo implements Command{
	
	private History history = History.getInstance();
	private Command command;
	
	public Undo(){
	}
	
	@Override
	public OutputToUI execute() {
		int code = 0;
		if (history.getUndoCommandList().isEmpty()){
			OutputToUI outputToUI = Controller.refreshScreen();
			String feedbackMsg = DataDisplay.feedback("Undo", 9);
			outputToUI.setFeedbackMsg(feedbackMsg);
			return outputToUI;
		}
		command = history.popCommandToUndoList();
		OutputToUI outputToUI = command.undo();
		System.out.println("inside undo success");
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Undo", code));
		history.pushCommandToRedoList(command);
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
