/*
 * @@author A0126058 
 */
package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.logic.Sort;
import main.java.resources.Task;
import main.java.storage.Storage;

public class SortTest {

	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", "-", "01/01/2015", "-", "0900", false, true, 0);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", "-", "02/02/2015", "null", "1100", true, true, 0);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, true, 0);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true, true, 0);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", "-", "-", "-", "-", false, true, 0);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", "-", "-", "-", "-", true, true, 0);
	
	ArrayList<Task> sortResult = new ArrayList<Task>();
	
	Storage storage = Storage.getInstance();
	
	Command command1 = new Add(task1, storage);
	Command command2 = new Add(task2, storage);
	Command command3 = new Add(task3, storage);
	Command command4 = new Add(task4, storage);
	Command command5 = new Add(task5, storage);
	Command command6 = new Add(task6, storage);
	
	public void execute() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
	}
	
	@Test
	public void testSortAll() throws IOException {
		execute();
		
		sortResult.add(task2);
		sortResult.add(task4);
		sortResult.add(task6);
		sortResult.add(task3);
		sortResult.add(task1);
		sortResult.add(task5);
		assertEquals(sortResult, Sort.sortAll());
	}

}

