//@Author:Jiahuan
package main.java.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import main.java.resources.Task;

public class Sort {
	private Task task;
	private Calendar taskCalendar; 
	private String taskContent;
	//constructor
	public Sort(Task task, Calendar taskCalendar, String taskContent){
		this.task = task;
		this.taskCalendar = taskCalendar;
		this.taskContent = taskContent;
	}
	//accessor
	public Task getTask(){
		return task;
	}
	
	public Calendar getTaskCalendar(){
		return taskCalendar;
	}
	
	public String getTaskContent(){
		return taskContent;
	}
	//mutator
	public void setTask(Task task){
		this.task = task;
	}
	
	public void setTaskCalendar(Calendar taskCalendar){
		this.taskCalendar = taskCalendar;
	}
	
	public void setTaskContent(String content){
		taskContent = content;
	}
	
	//sort a task list by time then followed by alphabetic order of content
	public static ArrayList<Task> sortByTime(ArrayList<Task> listForSort){
		ArrayList<Task> sortResult = new ArrayList<Task> ();
		ArrayList<Sort> sortList = createSortList(listForSort);
		
		return sortResult;
	}
	
	//create sortlist with sort objects for sort
	private static ArrayList<Sort> createSortList(ArrayList<Task> listForSort) {
		ArrayList<Sort> sortList = new ArrayList<Sort> ();
		for (int i = 0; i<listForSort.size();i++){
			Task task = listForSort.get(i);
			sortList.add(new Sort(task, createTaskCalendar(task), task.getTaskDescription()));
		}
		return sortList;
	}
	
	//create calendar from task timing
	private static Calendar createTaskCalendar(Task task) {
		Calendar calendar = new GregorianCalendar();
		String [] date = task.getStartDate().split("/");
		int day = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		int hour = Integer.parseInt(task.getStartTime().substring(0, 2));
		int minute = Integer.parseInt(task.getStartTime().substring(2));
		//set those attributes with gregoriancalendar and sort 
		//TODO consider deadline and floating and event
		return null;
	}
	

    /* 
     * Sorts task by task type and description in the taskList
     * @@author Lim Yong Zhi
     */
    public static ArrayList<Task> sortTaskList (ArrayList<Task> taskList) {
        Collections.sort(taskList, new TaskComparatorByTaskDescription());
        return taskList;
    }
}

/**
 * Comparator override methods for sorting purposes
 * @@author Lim Yong Zhi
 */

/*
 * Sorts taskList by Task Description
 */
class TaskComparatorByTaskDescription implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getTaskDescription().compareTo(t2.getTaskDescription());
    }
}

/*
 * Sorts taskList by Date
 */
class TaskComparatorByDate implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getStartDate() == null || t2.getStartDate() == null) {
            return 0;
        }
        return t1.getStartDate().compareTo(t2.getStartDate());
    }
}

/*
 * Sorts taskList by Time
 */
class TaskComparatorByTime implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getStartTime() == null || t2.getStartTime() == null) {
            return 0;
        }
        return t1.getStartTime().compareTo(t2.getStartTime());
    }
}


/*
 * Sorts taskList by Task Type
 * TODO: May not be required
 */
class TaskComparatorByTaskType implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getTaskType().compareTo(t2.getTaskType());
    }
}