//@Author:Jiahuan
package main.java.logic;

import main.java.resources.DataDisplay;
import main.java.resources.Task;
import main.java.storage.Storage;

public class UpdateWithNum implements Command{
	private int itemNum;
	
	public UpdateWithNum(int itemNum){
		this.itemNum=itemNum;
	}
	
	@Override
	public void execute() {
		Task task = Search.obtainTaskByItemNum(itemNum); // Put in history so it can be restored
		Storage.deleteOneItem(itemNum);
		//TODO: Display this task on to the screen
		DataDisplay.displayTaskNeedForUpdate(task);
	}

}
