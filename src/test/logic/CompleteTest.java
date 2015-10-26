package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Complete;
import main.java.logic.Controller;
import main.java.logic.Update;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class CompleteTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
	Task task7 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
	Storage storage = Storage.getInstance();
	Command command1 = new Add(task1, storage);
	Command command2 = new Add(task2, storage);
	Command command3 = new Add(task3, storage);
	Command command4 = new Add(task4, storage);
	Command command5 = new Add(task5, storage);
	Command command6 = new Add(task6, storage);
	Command displayCommand = Controller.createCommand("display all");
	int itemNum = 1;
	
	@Test
	public void test() throws IOException {
		Controller.initializeProgram();
		DataDisplay.displayList(storage.getTaskList());
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		OutputToUI outputToUI=displayCommand.execute();
		DataDisplay.printOutputToUI(outputToUI);
		//DataDisplay.displayList(storage.getTaskList());
		Command command_update = new Complete(itemNum, storage);
		OutputToUI outputToUI2=command_update.execute();
		DataDisplay.displayList(storage.getTaskList());
		DataDisplay.printOutputToUI(outputToUI2);
		task7.setCompleted(true);
		OutputToUI outputToUI3=displayCommand.execute();
		DataDisplay.printOutputToUI(outputToUI3);
		System.out.println(task7.getIsCompleted());
		assertTrue(storage.getTaskList().contains(task7));
	}

}
