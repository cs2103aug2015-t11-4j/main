/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.OutputToUI;

/*
 * This class is for quit alt4
 * By creating the command with user input
 * Pass to storage to quit alt4
 */

public class Exit implements Command {

	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		outputToUI.setTypeOfScreen("exit");
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
