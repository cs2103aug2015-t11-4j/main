/*
 * @@author A0104278 
 */

package main.java.logic;

import main.java.resources.OutputToUI;

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
