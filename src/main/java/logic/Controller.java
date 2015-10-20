//@Author:Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.parser.Parser;
import main.java.resources.DataDisplay;
import main.java.resources.Task;
import main.java.storage.Storage;
import main.java.ui.UI;

public class Controller {
	public static Command createCommand(String inputFromUser) {
		ArrayList<String> inputForAction = Parser.retrieveCommand(inputFromUser);
		Task task;
		Command command;
		int itemNum;
		switch (inputForAction.get(0).toLowerCase()) {
		// if add, need to create task object for storage
		case "add":
			task = Parser.createTaskForAdd(inputForAction);
			return command = new Add(task);
		case "update":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Update(itemNum);
		case "delete":
			itemNum = Integer.parseInt(inputForAction.get(1));
			return command = new Delete(itemNum);
		default:
			return command = new Display(inputForAction);
		}
	}
}
