package test.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Delete;
import main.java.resources.Task;
import main.java.storage.Storage;

public class DeleteTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
	Storage storage = new Storage();
	Command command1 = new Add(task1);
	Command command2 = new Add(task1);
	Command command3 = new Add(task1);
	Command command4 = new Add(task1);
	int itemNum = 1;
	Command command_del = new Delete(itemNum);

	
	@Test
	public void test() {
		storage.getTaskList().clear();
		storage.getTaskList().add(task1);
		command_del.execute();
		assertTrue(!storage.getTaskList().contains(task1));
	}

}
