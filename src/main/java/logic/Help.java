package main.java.logic;

import main.java.resources.OutputToUI;
import main.java.storage.Storage;

public class Help implements Command{
	private History history  = History.getInstance();
	private Storage storage = Storage.getInstance();
	
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
