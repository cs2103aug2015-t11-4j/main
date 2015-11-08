/*
 * @@author A0126058 
 */

package test.logic;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.logic.History;
import main.java.logic.Redo;
import main.java.logic.Undo;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class RedoTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	

	Task task1 = new Task(TYPE_DEADLINE, "wake up", "-", "01/01/2015", "-", "0900", false, true, 0);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", "-", "02/02/2015", "null", "1100", true, true, 0);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, true, 0);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true, true, 0);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", "-", "-", "-", "-", false, true, 0);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", "-", "-", "-", "-", true, true, 0);
	Task task7 = new Task(TYPE_DEADLINE, "wake up", "-", "01/01/2015", "-", "0900", false, true, 0);	
	
	
	
	Storage storage = Storage.getInstance();
	Command command1 = new Add(task1, storage);
	Command command2 = new Add(task2, storage);
	Command command3 = new Add(task3, storage);
	Command command4 = new Add(task4, storage);
	Command command5 = new Add(task5, storage);
	Command command6 = new Add(task6, storage);
	Command undo = new Undo();
	Command redo = new Redo();
	
	public OutputToUI outputToUI = new OutputToUI();
	
	@Test
	public void test() {
		History history = History.getInstance();
		try {
			Controller.initializeProgram();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		history.setCurrentScreen("all");
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		outputToUI = command6.execute();
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		DataDisplay.displayList(storage.getTaskList());
		
		outputToUI = undo.execute();
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		
		outputToUI = undo.execute();
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		
		outputToUI = undo.execute();
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		
		outputToUI = redo.execute();
		
		assertTrue(storage.getTaskList().contains(task4));
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		
		outputToUI = redo.execute();
		
		assertTrue(storage.getTaskList().contains(task5));
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
		
		outputToUI = redo.execute();
		
		assertTrue(storage.getTaskList().contains(task6));
		
		DataDisplay.printOutputToUI(outputToUI);
		DataDisplay.printUndoCommandList();
		DataDisplay.printRedoCommandList();
	}
}
