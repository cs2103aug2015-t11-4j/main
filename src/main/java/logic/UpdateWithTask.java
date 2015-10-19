//@Author:Jiahuan
package main.java.logic;

import main.java.resources.Task;
import main.java.storage.Storage;
//Second step of update, user change its message and enter
public class UpdateWithTask implements Command{
	private Task task;
	
	public UpdateWithTask (Task task){
		this.task = task;
	}
	
	//recreate the task requested by user
	@Override
	public void execute() {
		Storage.addOneItem(task);
	}

}
