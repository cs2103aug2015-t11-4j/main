/**
 * @@author A0131300
 */

package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public static Date today = new Date();
    
    private static final LocalDateTime TODAY = LocalDateTime.now();
	private static final LocalDateTime TMR = TODAY.plusDays(1);
	private static final LocalDateTime TDL = TODAY.plusDays(3);  //three days later
	private static final LocalDateTime TWENTYDAYS = TODAY.plusDays(20);  //20 days later
	private static final LocalDateTime TWENTYONEDAYS = TODAY.plusDays(21);  //21 days later

	public static Date tmr = Date.from(TMR.atZone(ZoneId.systemDefault()).toInstant());
	public static Date tdl = Date.from(TDL.atZone(ZoneId.systemDefault()).toInstant());
	public static Date twenty = Date.from(TWENTYDAYS.atZone(ZoneId.systemDefault()).toInstant());
	public static Date twentyone = Date.from(TWENTYONEDAYS.atZone(ZoneId.systemDefault()).toInstant());
    
    Task task1 = new Task(TYPE_DEADLINE, "report submission", "-", dateFormat.format(today), "-", "2359", false, true, 0);
	Task task2 = new Task(TYPE_DEADLINE, "essay", "-", dateFormat.format(tmr), "-", "1200", false, true, 0);
	Task task3 = new Task(TYPE_EVENT, "family outing", dateFormat.format(today), dateFormat.format(today), "0900", "1300", true, true, 0);
	Task task4 = new Task(TYPE_EVENT, "welfare pack distribution", dateFormat.format(twenty), dateFormat.format(twentyone), "1200", "1800", false, true, 0);
	Task task5 = new Task(TYPE_FLOATING, "meeting with friends", "-", "-", "-", "-", false, true, 0);
	Task task6 = new Task(TYPE_FLOATING, "dinner with family", "-", "-", "-", "-", true, true, 0);
	
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
	
	//Test only when external file is empty
	@Test
	public void test() throws IOException {
		execute();
		
		assertTrue(storage.getTaskList().contains(task1));
		assertTrue(storage.getTaskList().contains(task2));
		assertTrue(storage.getTaskList().contains(task3));
		assertTrue(storage.getTaskList().contains(task4));
		assertTrue(storage.getTaskList().contains(task5));
		assertTrue(storage.getTaskList().contains(task6));	
	}
	
	//Test every method separately
	//Pre-cond: empty ALT4.txt
	@Test
	public void testObtainTodaySummary() throws IOException {	
		execute();
		
		ArrayList<Task> todaySummary = new ArrayList<Task>();
		todaySummary.add(task1);  //deadline
		todaySummary.add(task2);  //deadline
		todaySummary.add(task5);  //floating
		assertEquals(todaySummary, Search.obtainTodaySummary(storage));
	}
	
	/*@Test
	public void testObtainTomorrowSummary() throws IOException {
		execute();
		
		ArrayList<Task> tmrSummary = new ArrayList<Task>();
		tmrSummary.add(task2);  //deadline
		tmrSummary.add(task5);  //floating
		assertEquals(tmrSummary, Search.obtainTomorrowSummary(storage));
	}*/
	
	/*@Test
	public void testObtainAllTasks() throws IOException {
		execute();
		
		//go by date -> time
		ArrayList<Task> allTasks = new ArrayList<Task>();
		allTasks.add(task3);  //event completed
		allTasks.add(task6);  //floating completed
		allTasks.add(task1);  //deadline
		allTasks.add(task2);  //deadline
		allTasks.add(task4);  //event
		allTasks.add(task5);  //floating	
		assertEquals(allTasks, Search.obtainAllTasks(storage));
	}*/
	
	/*@Test
	public void testObtainAllIncompleteTasks() throws IOException {
		execute();
		
		//go by deadline -> event -> floating
		//go by date -> time
		//go by alphabetical order except for floating
		ArrayList<Task> incomplete = new ArrayList<Task>();
		incomplete.add(task1);  //deadline
		incomplete.add(task2);  //deadline
		incomplete.add(task4);  //event
		incomplete.add(task5);  //floating
		assertEquals(incomplete, Search.obtainAllIncompleteTasks(storage));
	}*/
	
	/*@Test
	public void testObtainAllCompleteTasks() throws IOException {
		execute();

		//go by deadline -> event -> floating
		//go by date -> time
		ArrayList<Task> complete = new ArrayList<Task>();
		complete.add(task3);  //event
		complete.add(task6);  //floating
		assertEquals(complete, Search.obtainAllCompleteTasks(storage));
	}*/
	
	/*@Test
	public void testObtainDeadlineTasks() throws IOException {
		execute();
		
		//complete -> incomplete
		//go by date -> time
		ArrayList<Task> deadline = new ArrayList<Task>();
		deadline.add(task1);  //deadline
		deadline.add(task2);  //deadline
		assertEquals(deadline, Search.obtainDeadlineTasks(storage));
	}*/
	
	/*@Test
	public void testObtainEventTasks() throws IOException {
		execute();
		
		//complete -> incomplete
		//go by date -> time
		ArrayList<Task> event = new ArrayList<Task>();
		event.add(task3);  //event
		event.add(task4);  //event
		assertEquals(event, Search.obtainEventTasks(storage));
	}*/
	
	/*@Test
	public void testObtainFloatingTasks() throws IOException {
		execute();
		
		//complete -> incomplete
		ArrayList<Task> floating = new ArrayList<Task>();
		floating.add(task6);  //floating
		floating.add(task5);  //floating
		assertEquals(floating, Search.obtainFloatingTasks(storage));
	}*/
	
	/*@Test
	public void testObtainSearchResults() throws IOException {
		execute();
		
		//complete -> incomplete
		ArrayList<Task> results = new ArrayList<Task>();
		results.add(task6);  //floating
		results.add(task5);  //floating
		assertEquals(results, Search.obtainSearchResults("wit", storage));
	}*/

}
