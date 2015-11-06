//@@Author: Jiahuan
package main.java.resources;


import java.util.Comparator;

import main.java.logic.Sort;

//comparator that compare iscomplete first then time, then task content string
public class TaskComparator implements Comparator<Sort> {

	@Override
	public int compare(Sort sort1, Sort sort2) {

		int dateResult = sort1.getTaskDate().compareTo(sort2.getTaskDate());
		if (dateResult != 0) {
			return dateResult;
		} else {
			int timeResult = sort1.getTaskTime().compareTo(sort2.getTaskTime());
			if (timeResult != 0) {
				return timeResult;
			} else {
				return sort1.getTaskContent().compareTo(sort2.getTaskContent());
			}
		}
	}

	/*
	 * public static final String TYPE_DEADLINE = "deadline"; public static
	 * final String TYPE_EVENT = "event"; public static final String
	 * TYPE_FLOATING = "floating";
	 * 
	 * static Task task1 = new Task(TYPE_DEADLINE, "wake up", null,
	 * "01/01/2015", null, "0900", false); static Task task2 = new
	 * Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015",
	 * null, "1100", true); static Task task3 = new Task(TYPE_EVENT, "go toilet"
	 * , "01/01/2015", "01/02/2015", "0900", "1000", false); static Task task4 =
	 * new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015",
	 * "0915", "1100", true); static Task task5 = new Task(TYPE_FLOATING,
	 * "meet with bob", null, null, null, null, false); static Task task6 = new
	 * Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
	 * 
	 * public static void main(String arg[]){ ArrayList<Task> taskList = new
	 * ArrayList<Task>(); taskList.add(task1); taskList.add(task3);
	 * taskList.add(task6); taskList.add(task4); taskList.add(task5);
	 * taskList.add(task2); Collections.sort(taskList, new TaskComparator());
	 * DataDisplay.displayList(taskList); }
	 */

	/*
	 * @Override public int compare(Sort sort1, Sort sort2) { int completeResult
	 * = sort1.getIsComplete().compareTo(sort2.getIsComplete()); if
	 * (completeResult != 0){ return completeResult; } int timeResult =
	 * sort1.getTaskTime().compareTo(sort2.getTaskTime()); if (timeResult != 0){
	 * return timeResult; } int stringResult =
	 * sort1.getTaskContent().compareTo(sort2.getTaskContent()); return
	 * stringResult; }
	 * 
	 * public static void main(String arg[]){ ArrayList<Task> sortResult = new
	 * ArrayList<Task> (); ArrayList<Sort> sortList =
	 * createSortList(listForSort); Collections.sort(sortList, new
	 * TaskComparator()); for (int i = 0; i <sortList.size(); i++){
	 * sortResult.add(sortList.get(i).getTask()); } }
	 */

}