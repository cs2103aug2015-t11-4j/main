/*
 * @@author A0131300 
 */

package test.logic;

import static org.junit.Assert.*;
import org.junit.Test;

import main.java.parser.CreateTask;
import main.java.resources.Task;


public class CreateTaskTest {
	
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";

	//to test whether the method createDeadline creates a deadline task in the right format
	@Test
	public void testCreateDeadline() {
		Task task = new Task(TYPE_DEADLINE, "meeting tonight", "-" , "21/10/2015", "-" , "2200", false, true, 0);
		Task test = CreateTask.createDeadline(TYPE_DEADLINE, "meeting tonight by 21/10;2200");
		assertEquals(task.getTaskType(), test.getTaskType());
		assertEquals(task.getTaskDescription(), test.getTaskDescription());
		assertEquals(task.getStartDate(), test.getStartDate());
		assertEquals(task.getEndDate(), test.getEndDate());
		assertEquals(task.getStartTime(), test.getStartTime());
		assertEquals(task.getEndTime(), test.getEndTime());
		assertEquals(task.getIsCompleted(), test.getIsCompleted());
	}
	
	//to test whether the method createEvent creates a event task in the right format
	@Test
	public void testCreateEvent() {
		Task task = new Task(TYPE_EVENT, "holiday with family", "29/11/2015", "11/12/2015", "-", "-", false, true, 0);
		Task test = new Task();
		test = CreateTask.createEvent(TYPE_EVENT, "holiday with family from 29/11 to 11/12");
		assertEquals(task.getTaskType(), test.getTaskType());
		assertEquals(task.getTaskDescription(), test.getTaskDescription());
		assertEquals(task.getStartDate(), test.getStartDate());
		assertEquals(task.getEndDate(), test.getEndDate());
		assertEquals(task.getStartTime(), test.getStartTime());
		assertEquals(task.getEndTime(), test.getEndTime());
		assertEquals(task.getIsCompleted(), test.getIsCompleted());
	}
	
	//to test whether the method createFloating creates a floating task in the right format
	@Test
	public void testCreateFloating() {
		Task task = new Task(TYPE_FLOATING, "go themepark", "-", "-", "-", "-", false, true, 0);
		Task test = CreateTask.createFloating(TYPE_FLOATING, "go themepark");
		assertEquals(task.getTaskType(), test.getTaskType());
		assertEquals(task.getTaskDescription(), test.getTaskDescription());
		assertEquals(task.getStartDate(), test.getStartDate());
		assertEquals(task.getEndDate(), test.getEndDate());
		assertEquals(task.getStartTime(), test.getStartTime());
		assertEquals(task.getEndTime(), test.getEndTime());
		assertEquals(task.getIsCompleted(), test.getIsCompleted());
	}

}
