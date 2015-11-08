/*
 * @@author A0104278 
 */

package test.logic;

import java.util.ArrayList;

import main.java.resources.Task;

public class SortTest {

	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", "null", "01/01/2015", "null", "0900", false, true, 0);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", "null", "02/02/2015", "null", "1100", true, true, 0);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, true, 0);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true, true, 0);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", "null", "null", "null", "null", false, true, 0);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", "null", "null", "null", "null", true, true, 0);
	Task task7 = new Task(TYPE_DEADLINE, "wake up", "null", "01/01/2015", "null", "0900", false, true, 0);	
	
	ArrayList<Task> sortList = new ArrayList<Task>();
	ArrayList<Task> sortResult = new ArrayList<Task>();
	ArrayList<Task> expected = new ArrayList<Task>();
	
/*	@Test
	public void test() {
		expected.add(task3);
		expected.add(task1);
		expected.add(task2);
		expected.add(task4);
		sortResult = Sort.sortByTime(sortList);
		
		assertEquals(expected, sortResult);
	}*/

}
