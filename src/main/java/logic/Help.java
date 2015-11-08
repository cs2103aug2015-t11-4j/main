/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.OutputToUI;
/*
 * This class is for display help
 * By creating the command with user input
 * Pass to storage for display help
 */
public class Help implements Command{
	public Help(){
		
	}
	
	
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = Controller.refreshScreen();
		outputToUI.setFeedbackMsg("Help is displayed");
		outputToUI.setTypeOfScreen("help");
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
