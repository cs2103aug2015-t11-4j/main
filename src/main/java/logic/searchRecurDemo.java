//@@author Jiahuan
package main.java.logic;

import java.io.IOException;

import main.java.storage.Storage;

public class searchRecurDemo {
	public static void main (String[] arg) throws IOException{
		History history = History.getInstance();
		Storage storage = Storage.getInstance();
		System.out.println(obtainLargestRecurID());
	}
	
	public static int obtainLargestRecurID() throws IOException {
		Storage storage = Storage.getInstance();
		storage.regenerateTaskList();
		System.out.println("INSIDE: " + storage.getTaskList().size());
		int temp, largestRecurID = 0;
		for (int i = 0; i < storage.getTaskList().size(); i++){
			System.out.println(i);
			System.out.println(i + ": RecurID is" + storage.getTaskList().get(i).getRecurringID());
			if (storage.getTaskList().get(i).getRecurringID() > largestRecurID){
				
				temp = storage.getTaskList().get(i).getRecurringID();
				largestRecurID = temp;
			}
		}
		return largestRecurID;
	}
}
