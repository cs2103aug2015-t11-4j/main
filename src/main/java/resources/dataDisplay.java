package main.java.resources;

import main.java.storage.Storage;

public class dataDisplay {
	//To display all items in the current list
	public static void displayAll(){
		int size = Storage.taskList.size();
		for (int i = 0; i < size; i++){
			int itemNum = i+1;
			switch (Storage.taskList.get(i).getTaskType()){
			case "deadline":
				System.out.println(itemNum + ": " +createContentForDeadline(Storage.taskList.get(i)));
				break;
			case "event":
				System.out.println(itemNum + ": " +createContentForEvent(Storage.taskList.get(i)));
				break; 
			case "floating":
				System.out.println(itemNum + ": " +createContentForfloating(Storage.taskList.get(i)));
				break;
			}
		}
	}
private static String createContentForfloating(Task task) {
		
		return task.getTaskDescription();
	}

	private static String createContentForEvent(Task task) {
		
		return task.getStartTime()+" - "+task.getEndTime()+ " on "+ task.getDate()+": "+task.getTaskDescription();
	}

	private static String createContentForDeadline(Task task) {

		return "By "+task.getDate()+": "+ task.getTaskDescription();
	}

}
