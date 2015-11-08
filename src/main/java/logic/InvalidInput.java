//@@author Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;

public class InvalidInput implements Command{
	private History history;
	
	
	public InvalidInput(){
		history = History.getInstance();
	}
	
	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = Controller.refreshScreen();
		
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Invalid input",1));
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
