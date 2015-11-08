//@@author Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputToUI redo() {
		// TODO Auto-generated method stub
		return null;
	}

}
