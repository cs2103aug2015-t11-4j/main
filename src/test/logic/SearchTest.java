/**
 * @@author yuju
 */
package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import main.java.logic.Add;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.logic.Search;
import main.java.resources.Task;
import main.java.storage.Storage;

public class SearchTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static Date date = new Date();
    
    Task task1 = new Task(TYPE_DEADLINE, "report submission", "null", dateFormat.format(date), "null", "2359", false, false, 0);
	Task task2 = new Task(TYPE_DEADLINE, "essay", "null", "09/11/2015", "null", "1200", false, false, 0);
	Task task3 = new Task(TYPE_EVENT, "family outing", dateFormat.format(date), dateFormat.format(date), "0900", "1300", true, false, 0);
	Task task4 = new Task(TYPE_EVENT, "welfare pack distribution", "28/11/2015", "29/11/2015", "1200", "1800", false, false, 0);
	Task task5 = new Task(TYPE_FLOATING, "meeting with friends", "null", "null", "null", "null", false, false, 0);
	Task task6 = new Task(TYPE_FLOATING, "dinner with family", "null", "null", "null", "null", true, false, 0);
	
	Task task7 = new Task(TYPE_DEADLINE, "report submission", "null", "09/11/2015", "null", "2359", true, false, 0);
	Task task8 = new Task(TYPE_DEADLINE, "essay", "null", "11/11/2015", "null", "1200", false, false, 0);
	Task task9 = new Task(TYPE_EVENT, "family outing", "09/11/2015", "09/11/2015", "0900", "1300", false, false, 0);
	Task task10 = new Task(TYPE_EVENT, "welfare pack distribution", "28/11/2015", "29/11/2015", "1200", "1800", false, false, 0);
	Task task11 = new Task(TYPE_FLOATING, "class gathering", "null", "null", "null", "null", false, false, 0);
	Task task12 = new Task(TYPE_FLOATING, "dinner with relatives", "null", "null", "null", "null", true, false, 0);
	
	Storage storage = Storage.getInstance();
	ArrayList<Task> list = storage.getTaskList();
	
	Command command1 = new Add(task1, storage);
	Command command2 = new Add(task2, storage);
	Command command3 = new Add(task3, storage);
	Command command4 = new Add(task4, storage);
	Command command5 = new Add(task5, storage);
	Command command6 = new Add(task6, storage);
	Command command7 = new Add(task7, storage);
	Command command8 = new Add(task8, storage);
	Command command9 = new Add(task9, storage);
	Command command10 = new Add(task10, storage);
	Command command11 = new Add(task11, storage);
	Command command12 = new Add(task12, storage);

	@Test
	public void testObtainTodaySummary() throws IOException {		
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		ArrayList<Task> todaySummary = new ArrayList<Task>();
		todaySummary.add(task1);  //deadline
		todaySummary.add(task2);  //deadline
		todaySummary.add(task5);  //floating
		todaySummary.add(task11);  //floating
		assertEquals(todaySummary, Search.obtainTodaySummary(storage));
	}
	
	@Test
	public void testObtainTomorrowSummary() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		ArrayList<Task> tmrSummary = new ArrayList<Task>();
		tmrSummary.add(task2);  //deadline
		tmrSummary.add(task8);  //deadline
		tmrSummary.add(task9);  //event
		tmrSummary.add(task5);  //floating
		tmrSummary.add(task11);  //floating
		assertEquals(tmrSummary, Search.obtainTomorrowSummary(storage));
	}
	
	@Test
	public void testObtainAllTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*go by date -> time*/
		ArrayList<Task> allTasks = new ArrayList<Task>();
		allTasks.add(task3);  //event completed
		allTasks.add(task7);  //deadline completed
		allTasks.add(task6);  //floating completed
		allTasks.add(task12);  //floating completed
		allTasks.add(task1);  //deadline
		allTasks.add(task9);  //event
		allTasks.add(task2);  //deadline
		allTasks.add(task8);  //deadline
		allTasks.add(task4);  //event
		allTasks.add(task10);  //event
		allTasks.add(task5);  //floating
		allTasks.add(task11);  //floating
		
		
		assertEquals(allTasks, Search.obtainAllTasks(storage));
	}
	
	@Test
	public void testObtainAllIncompleteTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*go by deadline -> event -> floating
		go by date -> time
		go by alphabetical order except for floating*/
		ArrayList<Task> incomplete = new ArrayList<Task>();
		incomplete.add(task1);  //deadline
		incomplete.add(task9);  //event
		incomplete.add(task2);  //deadline
		incomplete.add(task8);  //deadline
		incomplete.add(task4);  //event
		incomplete.add(task10);  //event
		incomplete.add(task5);  //floating
		incomplete.add(task11);  //floating
		assertEquals(incomplete, Search.obtainAllIncompleteTasks(storage));
	}
	
	@Test
	public void testObtainAllCompleteTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*go by deadline -> event -> floating
		go by date -> time*/
		ArrayList<Task> complete = new ArrayList<Task>();
		complete.add(task3);  //event
		complete.add(task7);  //deadline
		complete.add(task6);  //floating
		complete.add(task12);  //floating
		assertEquals(complete, Search.obtainAllCompleteTasks(storage));
	}
	
	@Test
	public void testObtainDeadlineTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*complete -> incomplete
		go by date -> time*/
		ArrayList<Task> deadline = new ArrayList<Task>();
		deadline.add(task7);  //deadline
		deadline.add(task1);  //deadline
		deadline.add(task2);  //deadline
		deadline.add(task8);  //deadline
		assertEquals(deadline, Search.obtainDeadlineTasks(storage));
	}
	
	@Test
	public void testObtainEventTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*complete -> incomplete
		go by date -> time*/
		ArrayList<Task> event = new ArrayList<Task>();
		event.add(task3);  //event
		event.add(task9);  //event
		event.add(task4);  //event
		event.add(task10);  //event
		assertEquals(event, Search.obtainEventTasks(storage));
	}
	
	@Test
	public void testObtainFloatingTasks() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*complete -> incomplete*/
		ArrayList<Task> floating = new ArrayList<Task>();
		floating.add(task6);  //floating
		floating.add(task12);  //floating
		floating.add(task5);  //floating
		floating.add(task11);  //floating
		assertEquals(floating, Search.obtainFloatingTasks(storage));
	}
	
	@Test
	public void testObtainSearchResults() throws IOException {
		Controller.initializeProgram();
		command1.execute();
		command2.execute();
		command3.execute();
		command4.execute();
		command5.execute();
		command6.execute();
		command7.execute();
		command8.execute();
		command9.execute();
		command10.execute();
		command11.execute();
		command12.execute();
		
		/*complete -> incomplete*/
		ArrayList<Task> results = new ArrayList<Task>();
		results.add(task6);  //floating
		results.add(task12);  //floating
		results.add(task5);  //floating
		assertEquals(results, Search.obtainSearchResults("wit", storage));
	}

}
