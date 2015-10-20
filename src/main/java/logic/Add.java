//@Author:Jiahuan
package main.java.logic;

import main.java.resources.Task;
import main.java.storage.Storage;

public class Add implements Command{
	private Task task; 

	
	
	public Add(Task task){
		this.task=task;
	}
	
	@Override
	public void execute() {
		Storage.addOneItem(task);
	}

}
