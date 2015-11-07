package main.java.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.resources.Task;

/**
 * @@author yuju 
 */

public class CreateTaskTest {

	//to test whether the method createDeadline creates a deadline task in the right format
	@Test
	public void testCreateDeadline() {
		Task task = new Task("deadline", "meeting tonight", null, "21/10/2015", null, "2200", false, true, 0);
		Task test = CreateTask.createDeadline("deadline", "meeting tonight by 21/10;2200");
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
		Task task = new Task("event", "holiday with family", "29/11/2015", "11/12/2015", null, null, false, true, 0);
		Task test = new Task();
		test = CreateTask.createEvent("event", "holiday with family from 29/11 to 11/12");
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
		Task task = new Task("floating", "go themepark", null, null, null, null, false, true, 0);
		Task test = CreateTask.createFloating("floating", "go themepark");
		assertEquals(task.getTaskType(), test.getTaskType());
		assertEquals(task.getTaskDescription(), test.getTaskDescription());
		assertEquals(task.getStartDate(), test.getStartDate());
		assertEquals(task.getEndDate(), test.getEndDate());
		assertEquals(task.getStartTime(), test.getStartTime());
		assertEquals(task.getEndTime(), test.getEndTime());
		assertEquals(task.getIsCompleted(), test.getIsCompleted());
	}

}
