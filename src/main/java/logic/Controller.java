//@Author:Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.FlexiCommands;
import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.ItemForUserScreen;
import main.java.resources.Task;
import main.java.storage.Storage;

public class Controller {
	public static Command createCommand(String inputFromUser) {
		ArrayList<String> inputForAction = Parser.retrieveCommand(inputFromUser);
		Task task = null;
		Command command;
		Storage storage = Storage.getInstance();
		int itemNum;
		switch (FlexiCommands.flexiCommands(inputForAction.get(0).toLowerCase())) {
		// if add, need to create task object for storage
		case "add":
			task = Parser.createTaskForAdd(inputForAction);
			return command = new Add(task, storage);
		case "update":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Update(itemNum, storage);
		case "delete":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Delete(itemNum,storage);
		case "exit":
			
		default:
			return command = new Display(inputForAction, storage);
		}
	}
/*	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	public static void main(String arg[]){
		Storage storage = Storage.getInstance();
		Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
		Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true);
		Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false);
		Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true);
		Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false);
		Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
		Command command1 = new Add(task1, storage);
		Command command2 = new Add(task2, storage);
		Command command3 = new Add(task3, storage);
		Command command4 = new Add(task4, storage);
		Command command5 = new Add(task5, storage);
		Command command6 = new Add(task6, storage);
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		DataDisplay.displayList(storage.getTaskList());
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		itemList = getItemList();
		for (int i =0; i < itemList.size();i++){
		System.out.println(itemList.get(i).getIfComplete()+"_"+itemList.get(i).getTaskType()+"_"+itemList.get(i).getPrintOnScreenMsg());
		}
	}*/
	
	public static ArrayList<ItemForUserScreen> getItemList(){
		ArrayList<String> printOnScreenMsgList = new ArrayList<String>();
		Storage storage = Storage.getInstance();
		ArrayList<Task> taskList = Storage.getTaskList();
		ArrayList<ItemForUserScreen> itemList = new ArrayList<ItemForUserScreen>();
		printOnScreenMsgList = DataDisplay.displayList(taskList);
		for (int i = 0; i < taskList.size(); i++) {
			itemList.add(new ItemForUserScreen(taskList.get(i).getIsCompleted(), taskList.get(i).getTaskType(),
					printOnScreenMsgList.get(i)));
		}
		return itemList;
	}
}
