//@@Author: Jiahuan
package main.java.logic;

import java.io.IOException;
import java.util.ArrayList;

import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Recur implements Command{
	private History history;
	private Storage storage;
	private ArrayList<Task> recurList;
	private int recurID;
	
	
	public Recur(ArrayList<String> inputForAction) {
		storage = Storage.getInstance();
		history = History.getInstance();
		try {
			history.setRecurID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recurID = history.getNextRecurID();
		this.recurList = Parser.createRecurringTasks(inputForAction);;

		//history.setRecurID();
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
		
		//history.updateRecurID();
		try {
			history.setRecurID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outputToUI = Controller.refreshScreen();
				
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Recurring",code));
		history.pushCommandToUndoList(this);
		history.clearRedoList();
		return outputToUI;

	}

	@Override
	public OutputToUI undo() {
		int code = 0;
		
		OutputToUI outputToUI = new OutputToUI();
		
		for (int i = 0; i < recurList.size(); i++){
			Task task = recurList.get(i);
			code = storage.deleteOneItem(task); 
			//needs to handle if some task is added while others are not
			/*if (code != 0){
				
			}*/
		}
		outputToUI = Controller.refreshScreen();
		
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Undo", code));
		return outputToUI;

	}

	@Override
	public OutputToUI redo() {
		int code = 0;
		OutputToUI outputToUI = this.execute();
		outputToUI.setFeedbackMsg(DataDisplay.feedback("Redo", code));
		return outputToUI;
	}
	

}
