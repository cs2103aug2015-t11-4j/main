//@@ author A0104278

package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.logic.History;
import main.java.logic.Recur;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class RecurTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "task1", "-", "01/01/2015", "-", "0900", false, true, 1);
	Task task2 = new Task(TYPE_DEADLINE, "task1", "-", "02/01/2015", "-", "0900", false, true, 1);
	Task task3 = new Task(TYPE_DEADLINE, "task1", "-", "03/01/2015", "-", "0900", false, true, 1);
	Task task4 = new Task(TYPE_DEADLINE, "task2", "-", "09/01/2015", "-", "0900", false, true, 2);
	Task task5 = new Task(TYPE_DEADLINE, "task2", "-", "09/02/2015", "-", "0900", false, true, 2);
	Task task6 = new Task(TYPE_DEADLINE, "task2", "-", "09/02/2015", "-", "0900", false, true, 2);
	Task task7 = new Task(TYPE_DEADLINE, "task2", "-", "09/02/2015", "-", "0900", false, true, 2);
	
	ArrayList<String> userInput1 = new ArrayList<String>();
	String user1_1 = "recur";
	String user1_2 = "daily task1 by 0900;1/1 for 3 times";
	
	ArrayList<String> userInput2 = new ArrayList<String>();
	String user2_1 = "recur";
	String user2_2 = "monthly task2 by 0900;9/2 for 4 times";
	
	ArrayList<Task> recurList1 = new ArrayList<Task>();
	
	ArrayList<Task> recurList2 = new ArrayList<Task>();
	
	public OutputToUI outputToUI = new OutputToUI();
	
	
	//use when Alt4.txt is clean
	@Test
	public void test() {
		History history = History.getInstance();
		Storage storage = Storage.getInstance();
		//userInput1.add(user1_1);
		//userInput1.add(user1_2);
		Recur recur = new Recur();
		recurList1.add(task1);
		recurList1.add(task2);
		recurList1.add(task3);
		recur.setHistory(history);
		recur.setRecurList(recurList1);
		recur.setStorage(storage);
		Command recurCmd = recur;
		
		
		//userInput2.add(user2_1);
		//userInput2.add(user2_2);
		Recur recur2 = new Recur();
		recurList2.add(task4);
		recurList2.add(task5);
		recurList2.add(task6);
		recurList2.add(task7);
		recur2.setHistory(history);
		recur2.setRecurList(recurList2);
		recur2.setStorage(storage);
		Command recurCmd2 = recur;
		

		try {
			Controller.initializeProgram();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		history.setCurrentScreen("all");
		outputToUI = recur.execute();
		
		assertTrue(storage.getTaskList().contains(task1));
		assertTrue(storage.getTaskList().contains(task2));
		assertTrue(storage.getTaskList().contains(task3));
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		

		
		outputToUI = recur2.execute();
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		assertTrue(storage.getTaskList().contains(task4));
		assertTrue(storage.getTaskList().contains(task5));
		assertTrue(storage.getTaskList().contains(task6));
		assertTrue(storage.getTaskList().contains(task7));
		
		
	}

}
