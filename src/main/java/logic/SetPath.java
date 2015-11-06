package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.storage.Storage;

public class SetPath implements Command{
	private Storage storage = Storage.getInstance();
	private History history = History.getInstance();
	private String newPath;
	
	public SetPath(String newPath){
		this.newPath = newPath;
	}
	
	@Override
	public OutputToUI execute() {
		storage.changeDirectory(newPath);
		OutputToUI outputToUI = Controller.refreshScreen();
		String feedbackMsg = DataDisplay.feedback("Set new Directory", 0);
		outputToUI.setFeedbackMsg(feedbackMsg);
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
