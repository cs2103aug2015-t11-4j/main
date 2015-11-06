//@Author: Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;

public class Undo implements Command{
	
	private History history = History.getInstance();
	private Command command;
	
	public Undo(){
	}
	
	@Override
	public OutputToUI execute() {
		command = history.popCommandToUndoList();
		OutputToUI outputToUI = command.undo();
		history.pushCommandToRedoList(command);
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
