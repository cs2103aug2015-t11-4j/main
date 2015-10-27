package main.java.logic;

import java.util.ArrayList;

import main.java.resources.Task;
import main.java.storage.Storage;

public class History {
	private ArrayList<Task> screenList;

	//private constructor
	private History(){
	screenList = new ArrayList<Task>();
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
    
    public void setScreenList(ArrayList<Task> screenList){
    	this.screenList = screenList;
    }
    
    //mutator 
}
