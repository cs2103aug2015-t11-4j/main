package main.java.logic;

import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Recur implements Command{
	private History history;
	private Storage storage;
	private ArrayList<Task> recurList;
	private int recurID;
	
	
	public Recur(ArrayList<Task> recurList){
		this.recurList = recurList;
		storage = Storage.getInstance();
		history = History.getInstance();
		//history.setRecurID();
		recurID = history.getNextRecurID();
		//System.out.println("recur ID in recur is = " + history.getNextRecurID());
	}
	
	@Override
	public OutputToUI execute() {
		int code = 0;
		OutputToUI outputToUI = new OutputToUI();
		for (int i = 0; i < recurList.size(); i++){
			Task task = recurList.get(i);
			code = storage.addOneItem(task); 
			//needs to handle if some task is added while others are not
			/*if (code != 0){
				
			}*/
		}
		
		history.updateRecurID();
		outputToUI = Controller.refreshScreen();
				
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Recurring",code));
		history.pushCommandToUndoList(this);
		history.clearRedoList();
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
