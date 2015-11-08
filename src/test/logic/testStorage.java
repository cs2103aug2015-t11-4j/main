/*
 * @@author A0104278 
 */

package test.logic;

import java.util.ArrayList;

import main.java.resources.Task;

public class testStorage {

	private static ArrayList<Task> list = new ArrayList<Task>();;
	
	public testStorage(){
	}
	
	
	public static void addOneItem(Task task) {
		list.add(task);
		
	}


	public ArrayList<Task> getTaskList() {
		// TODO Auto-generated method stub
		return list;
	}


	public static void deleteOneItem(int itemNum) {
		list.remove(itemNum-1);
		
	}

}
