//@@Author Jiahuan
package main.java.logic;

import java.util.ArrayList;
import java.util.Collections;

import main.java.resources.DataDisplay;
import main.java.resources.Task;
import main.java.resources.TaskComparator;
import main.java.storage.Storage;

public class Sort {

	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";

	public static void main(String arg[]) {

		Task task1 = new Task(TYPE_DEADLINE, "wake up", "null", "01/01/2015", "null", "0900", false, false);
		Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", "null", "02/02/2015", "null", "1100", true, false);
		Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false, false);
		Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true, false);
		Task task5 = new Task(TYPE_FLOATING, "meet with bob", "null", "null", "null", "null", false, false);
		Task task6 = new Task(TYPE_FLOATING, "eat breakfast", "null", "null", "null", "null", true, false);
		Task task7 = new Task(TYPE_DEADLINE, "wake up", "null", "01/01/2015", "null", "0900", false, false);	
		int taskTime = createTaskTime(task1);
		int taskTime2 = createTaskTime(task4);
		int taskDate = createTaskDate(task1);
		int taskDate2 = createTaskDate(task3);
/*		p(taskTime);
		p(taskTime2);
		p(taskDate);
		p(taskDate2);*/
		
		
/*		Sort sort = new Sort(task1, createTaskDate(task1), createTaskTime(task1), task1.getTaskDescription());
		if(sort.getTask().equals(task1)){
			p(sort.getTaskDate());
			p(sort.getTaskTime());
			System.out.println(sort.getTaskContent());
		}*/
		
		ArrayList<Task> list = new ArrayList<Task>();
		ArrayList<Sort> sortList = new ArrayList <Sort>();
		
		list.add(task1);
		list.add(task2);
		list.add(task3);
		list.add(task4);
		list.add(task5);
		list.add(task6);
		sortList = createSortList(list);
		printSortList(sortList, list);
		
		Storage storage = Storage.getInstance();
		storage.addOneItem(task1);
		storage.addOneItem(task2);
		storage.addOneItem(task3);
		storage.addOneItem(task4);
		storage.addOneItem(task5);
		storage.addOneItem(task6);
		DataDisplay.displayList(storage.getTaskList());
		ArrayList<Task> sortResult = sortAll();
		DataDisplay.displayList(sortResult);
		
	}
	
	
	
	private static void printSortList(ArrayList<Sort> sortList, ArrayList<Task> list){
		Sort sort;
		for (int i = 0; i < sortList.size(); i++){
			sort = sortList.get(i);
			if(sort.getTask().equals(list.get(i))){
				p(sort.getTaskDate());
				p(sort.getTaskTime());
				System.out.println(sort.getTaskContent());
			}
		}
	}
	
	private static void p(int i){
		System.out.println(i);
	}
    
	
	private static Storage storage = Storage.getInstance();
	private Task task;
	private Integer taskTime;
	private Integer taskDate;
	private String taskContent;

	// constructor
	public Sort(Task task, int taskDate, int taskTime, String taskContent) {
		this.task = task;
		this.taskDate = taskDate;
		this.taskTime = taskTime;
		this.taskContent = taskContent;
	}

	// accessor
	public Task getTask() {
		return task;
	}

	public Integer getTaskTime() {
		return taskTime;
	}

	public Integer getTaskDate() {
		return taskDate;
	}

	public String getTaskContent() {
		return taskContent;
	}

	// mutator
	public void setTask(Task task) {
		this.task = task;
	}

	public void setTaskTime(Integer taskTime) {
		this.taskTime = taskTime;
	}

	public void setTaskDate(Integer taskDate) {
		this.taskDate = taskDate;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	// sort all by ifcomplete, event/deadline or floating, time, taskcontent
	public static ArrayList<Task> sortAll() {

		ArrayList<Task> sortResult = new ArrayList<Task>();
		//return empty list if no task in storage
		if (storage.getTaskList().size()==0){
			return sortResult;
		}
		
		ArrayList<Task> incompleteList = new ArrayList<Task>();
		ArrayList<Task> completeList = new ArrayList<Task>();
		ArrayList<Task> incompleteFloatingList = new ArrayList<Task>();
		ArrayList<Task> completeFloatingList = new ArrayList<Task>();
		ArrayList<Task> incompleteNonFloatingList = new ArrayList<Task>();
		ArrayList<Task> completeNonFloatingList = new ArrayList<Task>();
		ArrayList<Task> incompleteNonFloatingListSorted = new ArrayList<Task>();
		ArrayList<Task> completeNonFloatingListSorted = new ArrayList<Task>();
		
		incompleteList = Search.obtainAllIncompleteTasks(storage);
		System.out.println("***");
		System.out.println("incompleteList:");
		DataDisplay.displayList(incompleteList);
		System.out.println("***");
		completeList = Search.obtainAllCompleteTasks(storage);
		System.out.println("***");
		System.out.println("completeList:");
		DataDisplay.displayList(completeList);
		System.out.println("***");
		incompleteFloatingList = getFloatingList(incompleteList);
		System.out.println("***");
		System.out.println("incompleteFloatingList:");
		DataDisplay.displayList(incompleteFloatingList);
		System.out.println("***");
		completeFloatingList = getFloatingList(completeList);
		System.out.println("***");
		System.out.println("completeFloatingList:");
		DataDisplay.displayList(completeFloatingList);
		System.out.println("***");
		incompleteNonFloatingList = getNonFloatingList(incompleteList);
		System.out.println("***");
		System.out.println("incompleteNonFloatingList:");
		DataDisplay.displayList(incompleteNonFloatingList);
		System.out.println("***");
		completeNonFloatingList = getNonFloatingList(completeList);
		System.out.println("***");
		System.out.println("completeList:");
		DataDisplay.displayList(completeList);
		System.out.println("***");

		ArrayList<Sort> sortListForIncomplete = createSortList(incompleteNonFloatingList);
		ArrayList<Sort> sortListForComplete = createSortList(completeNonFloatingList);
		
		Collections.sort(sortListForIncomplete, new TaskComparator());
		if (sortListForIncomplete.size()!=0){
			for (int i = 0; i < sortListForIncomplete.size(); i ++){
			incompleteNonFloatingListSorted.add(sortListForIncomplete.get(i).getTask());
			}
		}
		Collections.sort(sortListForComplete, new TaskComparator());
		if (sortListForComplete.size()!=0){
			for (int i = 0; i < sortListForComplete.size(); i ++){
			completeNonFloatingListSorted.add(sortListForComplete.get(i).getTask());
			}
		}
		
		if (completeNonFloatingList.size() != 0) {
			for (int i = 0; i < completeNonFloatingList.size(); i++) {
				sortResult.add(completeNonFloatingListSorted.get(i));
			}
		}

		if (completeFloatingList.size() != 0) {
			for (int i = 0; i < completeFloatingList.size(); i++) {
				sortResult.add(completeFloatingList.get(i));
			}
		}

		if (incompleteNonFloatingList.size() != 0) {
			for (int i = 0; i < incompleteNonFloatingList.size(); i++) {
				sortResult.add(incompleteNonFloatingListSorted.get(i));
			}
		}

		if (incompleteFloatingList.size() != 0) {
			for (int i = 0; i < incompleteFloatingList.size(); i++) {
				sortResult.add(incompleteFloatingList.get(i));
			}
		}

		return sortResult;
	}

	private static ArrayList<Sort> createSortList(ArrayList<Task> list) {

		ArrayList<Sort> sortList = new ArrayList<Sort>();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Task task = list.get(i);
				sortList.add(new Sort(task, createTaskDate(task), createTaskTime(task), task.getTaskDescription()));
			}
		}
		return sortList;
	}

	// get the floatig out of the list of tasks
	private static ArrayList<Task> getFloatingList(ArrayList<Task> list) {
		ArrayList<Task> result = new ArrayList<Task>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTaskType().equals("floating")) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	// get the non-floatig (event, deadline) out of the list of tasks
	private static ArrayList<Task> getNonFloatingList(ArrayList<Task> list) {
		ArrayList<Task> result = new ArrayList<Task>();
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).getTaskType().equals("floating")) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	// sort a task list by time then followed by alphabetic order of content
	/*
	 * public static ArrayList<Task> sortByTime(ArrayList<Task> listForSort) {
	 * ArrayList<Task> sortResult = new ArrayList<Task>(); ArrayList<Sort>
	 * sortList = createSortList(listForSort); Collections.sort(sortList, new
	 * TaskComparator()); for (int i = 0; i < sortList.size(); i++) {
	 * sortResult.add(sortList.get(i).getTask()); } return sortResult; }
	 * 
	 * // create sortlist with sort objects for sort private static
	 * ArrayList<Sort> createSortList(ArrayList<Task> listForSort) {
	 * ArrayList<Sort> sortList = new ArrayList<Sort>(); for (int i = 0; i <
	 * listForSort.size(); i++) { Task task = listForSort.get(i);
	 * sortList.add(new Sort(task, task.getTaskDescription(),
	 * task.getIsCompleted())); }
	 * 
	 * return sortList; }
	 */
	// create calendar from task timing 

	private static int createTaskDate(Task task) {
		int taskDate = 0;
		if (task.getTaskType().equals("event")) {
			String[] date = task.getStartDate().split("/");
			int day = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			// int hour = Integer.parseInt(task.getStartTime().substring(0, 2));
			// int minute = Integer.parseInt(task.getStartTime().substring(2));
			taskDate = day + month * 100 + year * 10000;
			//p(year);
			//p(month);
			//p(day);
			// p(hour);
			// p(minute);
			//System.out.println(taskDate);
		}

		if (task.getTaskType().equals("deadline")) {
			String[] date = task.getEndDate().split("/");
			int day = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			// int hour = Integer.parseInt(task.getEndTime().substring(0, 2));
			// int minute = Integer.parseInt(task.getEndTime().substring(2));
			taskDate = day + month * 100 + year * 10000;
			//p(year);
			//p(month);
			//p(day);
			// p(hour);
			// p(minute);
			//System.out.println(taskDate);
		}

		return new Integer(taskDate);
	}

	private static int createTaskTime(Task task) {
		int taskTime = 0;
		if (task.getTaskType().equals("event")) {
			int hour = Integer.parseInt(task.getStartTime().substring(0, 2));
			int minute = Integer.parseInt(task.getStartTime().substring(2));
			taskTime = minute + hour * 100;
			//p(hour);
			//p(minute);
			//System.out.println(taskTime);
		}

		if (task.getTaskType().equals("deadline")) {
			int hour = Integer.parseInt(task.getEndTime().substring(0, 2));
			int minute = Integer.parseInt(task.getEndTime().substring(2));
			taskTime = minute + hour * 100;
			//p(hour);
			//p(minute);
			//System.out.println(taskTime);
		}

		return new Integer(taskTime);
	}

	/*
	private static void p(int toprint) {
		System.out.println(toprint);
	}
    */

	// Yongzhi's Code
	/*
	 * Sorts task by task type and description in the taskList
	 * 
	 * @@author Lim Yong Zhi
	 * 
	 * public static ArrayList<Task> sortTaskList (ArrayList<Task> taskList) {
	 * Collections.sort(taskList, new TaskComparatorByTaskDescription()); return
	 * taskList; } }
	 * 
	 *//**
		 * Comparator override methods for sorting purposes
		 * 
		 * @@author Lim Yong Zhi
		 *//*
		 * 
		 * 
		 * Sorts taskList by Task Description
		 * 
		 * class TaskComparatorByTaskDescription implements Comparator<Task> {
		 * 
		 * @Override public int compare(Task t1, Task t2) { return
		 * t1.getTaskDescription().compareTo(t2.getTaskDescription()); } }
		 * 
		 * 
		 * Sorts taskList by Date
		 * 
		 * class TaskComparatorByDate implements Comparator<Task> {
		 * 
		 * @Override public int compare(Task t1, Task t2) { if
		 * (t1.getStartDate() == null || t2.getStartDate() == null) { return 0;
		 * } return t1.getStartDate().compareTo(t2.getStartDate()); } }
		 * 
		 * 
		 * Sorts taskList by Time
		 * 
		 * class TaskComparatorByTime implements Comparator<Task> {
		 * 
		 * @Override public int compare(Task t1, Task t2) { if
		 * (t1.getStartTime() == null || t2.getStartTime() == null) { return 0;
		 * } return t1.getStartTime().compareTo(t2.getStartTime()); } }
		 * 
		 * 
		 * 
		 * Sorts taskList by Task Type TODO: May not be required
		 * 
		 * class TaskComparatorByTaskType implements Comparator<Task> {
		 * 
		 * @Override public int compare(Task t1, Task t2) { return
		 * t1.getTaskType().compareTo(t2.getTaskType()); }
		 */
}
