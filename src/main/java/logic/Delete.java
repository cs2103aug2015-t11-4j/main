//@Author:Jiahuan
package main.java.logic;


import main.java.storage.Storage;

public class Delete implements Command{

	private int itemNum;
	
	public Delete(int itemNum){
		this.itemNum = itemNum;
	}
	@Override
	public void execute() {
		Storage.deleteOneItem(itemNum);
		
	}

}
