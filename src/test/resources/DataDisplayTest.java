//@Author: Jiahuan
package test.resources;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.java.resources.DataDisplay;
import main.java.resources.Task;

public class DataDisplayTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";

	// initialize different task object that fits different requirement
	Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true);
	Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false);
	Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);

	// expected print format for each task
	String task1Print = "By 0900, 01/01/2015: wake up";
	String task2Print = "By 1100, 02/02/2015: wash face with cool water";
	String task3Print = "From 0900, 01/01/2015 to 1000, 01/02/2015: go toilet";
	String task4Print = "From 0915, 03/02/2015 to 1100, 03/02/2015: wash hand with soap";
	String task5Print = "meet with bob";
	String task6Print = "eat breakfast";

	// initialize different list for print
	ArrayList<Task> summaryList = new ArrayList<Task>();
	ArrayList<Task> incompleteList = new ArrayList<Task>();
	ArrayList<Task> completeList = new ArrayList<Task>();
	ArrayList<Task> floatingList = new ArrayList<Task>();
	ArrayList<Task> deadlineList = new ArrayList<Task>();
	ArrayList<Task> eventList = new ArrayList<Task>();

	@Test
	// Task Type count
	public void testTaskTypeCountFunction() {
		summaryList.add(task1);
		summaryList.add(task3);
		summaryList.add(task5);
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(1);
		expected.add(1);

		testTaskTypeCount(expected, summaryList);
	}

	private void testTaskTypeCount(ArrayList<Integer> expected, ArrayList<Task> summaryList2) {
		assertEquals(expected, DataDisplay.countTaskTypeNum(summaryList));

	}

	@Test
	// Display summary
	public void testDisplaySummaryFunction() {
		summaryList.add(task1);
		summaryList.add(task3);
		summaryList.add(task5);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Deadline");
		expected.add("1: " + task1Print);
		expected.add("Event");
		expected.add("2: " + task3Print);
		expected.add("Floating");
		expected.add("3: " + task5Print);

		testDisplaySummary(expected, summaryList);

	}

	private void testDisplaySummary(ArrayList<String> expected, ArrayList<Task> summaryList) {
		assertEquals(expected, DataDisplay.displaySummary(summaryList));

	}

	@Test
	// Display complete list
	public void testDisplayCompleteFunction() {
		completeList.add(task2);
		completeList.add(task4);
		completeList.add(task6);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Complete");
		expected.add("1: " + task2Print);
		expected.add("2: " + task4Print);
		expected.add("3: " + task6Print);

		testDisplayComplete(expected, completeList);
	}

	private void testDisplayComplete(ArrayList<String> expected, ArrayList<Task> completeList) {
		assertEquals(expected, DataDisplay.displayComplete(completeList));

	}

	@Test
	// Display incomplete list
	public void testDisplayIncompleteFunction() {
		incompleteList.add(task1);
		incompleteList.add(task3);
		incompleteList.add(task5);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Incomplete");
		expected.add("1: " + task1Print);
		expected.add("2: " + task3Print);
		expected.add("3: " + task5Print);

		testDisplayIncomplete(expected, incompleteList);
	}

	private void testDisplayIncomplete(ArrayList<String> expected, ArrayList<Task> incompleteList) {
		assertEquals(expected, DataDisplay.displayIncomplete(incompleteList));

	}

	@Test
	// Display floating list
	public void testDisplayFloatingFunction() {
		floatingList.add(task5);
		floatingList.add(task6);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Floating");
		expected.add("1: " + task5Print);
		expected.add("2: " + task6Print);

		testDisplayFloating(expected, floatingList);
	}

	private void testDisplayFloating(ArrayList<String> expected, ArrayList<Task> floatingList) {
		assertEquals(expected, DataDisplay.displayFloating(floatingList));

	}

	@Test
	// Display event list
	public void testDisplayEventFunction() {
		eventList.add(task4);
		eventList.add(task3);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Event");
		expected.add("1: " + task4Print);
		expected.add("2: " + task3Print);

		testDisplayEvent(expected, eventList);
	}

	private void testDisplayEvent(ArrayList<String> expected, ArrayList<Task> eventList) {
		assertEquals(expected, DataDisplay.displayEvent(eventList));

	}

	@Test
	// Display deadline list
	public void testDisplayDeadlineFunction() {
		deadlineList.add(task2);
		deadlineList.add(task1);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Deadline");
		expected.add("1: " + task2Print);
		expected.add("2: " + task1Print);

		testDisplayDeadline(expected, deadlineList);
	}

	private void testDisplayDeadline(ArrayList<String> expected, ArrayList<Task> deadlineList) {
		assertEquals(expected, DataDisplay.displayDeadline(deadlineList));

	}

}
