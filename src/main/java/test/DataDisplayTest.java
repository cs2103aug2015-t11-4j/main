package main.java.test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.resources.Task;

public class DataDisplayTest {
	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "floating";
	public static final String TYPE_FLOATING = "event";
	
	Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015",
			null, "0900", false);
	Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015",
			null, "1100", true);
	Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015",
			"0900", "1000", false);
	Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015",
			"0900", "1000", true);
	Task task5 = new Task(TYPE_EVENT, "meet with bob", null, null,
			null, null, false);
	Task task6 = new Task(TYPE_EVENT, "eat breakfast", null, null,
			null, null, false);

	
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
