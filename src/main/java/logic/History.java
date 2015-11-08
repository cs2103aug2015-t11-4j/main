/*
 * @@author A0104278 
 */

package main.java.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import main.java.resources.Task;

/*
 * This class is for storing most of the history
 * Called only by logic
 */

public class History {
	private ArrayList<Task> screenList;
	private String currentScreen;
	private Stack<Command> undoCommandList;
	private Stack<Command> redoCommandList;
	private int nextRecurID = 1;
	private Command searchCommand;
	
	//private constructor
	private History(){
	screenList = new ArrayList<Task>();
	redoCommandList = new Stack<Command>();
	undoCommandList = new Stack<Command>();
	
	
	}

	//create history object
	private static History history = new History();
	
    //access to object, create one if there is none
    public static History getInstance(){
        if (history == null){
            history = new History();
        }
        return history;
    }
    
    //accessor
    public ArrayList<Task> getScreenList(){
    	return screenList;
    }
    
    public String getCurrentScreen(){
    	return currentScreen;
    }
    
    public Stack<Command> getUndoCommandList(){
    	return undoCommandList;
    }
    
    public Stack<Command> getRedoCommandList(){
    	return redoCommandList;
    }
    
    public Command popCommandToRedoList(){
    	Command command = redoCommandList.pop();
    	return command;
    }
    
    public Command popCommandToUndoList(){
    	Command command = undoCommandList.pop();
    	return command;
    }
    
    public int getNextRecurID(){
    	return nextRecurID;
    }
    
    public Command gerSearchCommand(){
    	return searchCommand;
    }
    
  //mutator 
    public void setScreenList(ArrayList<Task> screenList){
    	this.screenList = screenList;
    }
    
    public void setCurrentScreen(String currentScreen){
    	this.currentScreen = currentScreen;
    }
    
    public void pushCommandToUndoList(Command command){
    	undoCommandList.push(command);
    }

    
    public void pushCommandToRedoList(Command command){
    	redoCommandList.push(command);
    }

	public void clearRedoList() {
		redoCommandList.clear();
		
	}
	
    public void setRecurID() throws IOException{
    	nextRecurID = Search.obtainLargestRecurID()+1;
    }
    
    public void updateRecurID(){
    	nextRecurID++;
    }
    
    public void setSearchCommand(Command command){
    	searchCommand = command;
    }

}
