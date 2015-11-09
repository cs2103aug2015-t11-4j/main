/*
 * @@author A0126058
 */

package test.storage;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.resources.Task;
import main.java.storage.Storage;

public class ChangeDirectoryTest {
    public static final String TYPE_DEADLINE = "deadline";
    public static final String TYPE_EVENT = "event";
    public static final String TYPE_FLOATING = "floating";
    
    Task task1 = new Task(TYPE_DEADLINE, "wake up", "null", "01/01/2015", "null", "0900", false, false, 0);
    Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", "null", "02/02/2015", "null", "1100", true, false, 0);
    Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, false, 0);
    Storage storage = Storage.getInstance();
    Command command1 = new Add(task1, storage);
    Command command2 = new Add(task2, storage);
    Command command3 = new Add(task3, storage);
    public OutputToUI outputToUI = new OutputToUI();
    
    @Test
    public void test() throws IOException {
        Controller.initializeProgram();
        command1.execute();
        command2.execute();
        command3.execute();
        DataDisplay.printOutputToUI(outputToUI);
        DataDisplay.displayList(storage.getTaskList());
        assertTrue(storage.getTaskList().contains(task1));
        
    }
}