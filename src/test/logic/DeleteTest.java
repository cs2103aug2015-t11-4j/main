package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.logic.Delete;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class DeleteTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false, false);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true, false);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, false);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true, false);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false, false);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true, false);
	Task task7 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false, false);	Storage storage = Storage.getInstance();
	Command command1 = new Add(task1, storage);
	Command command2 = new Add(task2, storage);
	Command command3 = new Add(task3, storage);
	Command command4 = new Add(task4, storage);
	int itemNum = 1;
	Command displayCommand = Controller.createCommand("display all");

	
	@Test
	public void test() throws IOException {
		Controller.initializeProgram();
		DataDisplay.displayList(storage.getTaskList());
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		OutputToUI outputToUI=displayCommand.execute();
		DataDisplay.printOutputToUI(outputToUI);
		//DataDisplay.displayList(storage.getTaskList());
		Command command_del = new Delete(itemNum, storage);
		command_del.execute();
		DataDisplay.displayList(storage.getTaskList());
		assertTrue(!storage.getTaskList().contains(task1));
	}

}
