//@Author:Jiahuan
package main.java.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import main.java.resources.Task;
import main.java.resources.TaskComparator;

public class Sort {
/*	public static final String TYPE_DEADLINE = "deadline";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_FLOATING = "floating";
	public static void main(String arg[]){

		
		Task task1 = new Task(TYPE_DEADLINE, "wake up", null, "01/01/2015", null, "0900", false);
		Task task2 = new Task(TYPE_DEADLINE, "wash face with cool water", null, "02/02/2015", null, "1100", true);
		Task task3 = new Task(TYPE_EVENT, "go toilet", "01/01/2015", "01/02/2015", "0900", "1000", false);
		Task task4 = new Task(TYPE_EVENT, "wash hand with soap", "03/02/2015", "03/02/2015", "0915", "1100", true);
		Task task5 = new Task(TYPE_FLOATING, "meet with bob", null, null, null, null, false);
		Task task6 = new Task(TYPE_FLOATING, "eat breakfast", null, null, null, null, true);
		System.out.println();
	}*/
	
	
	private Task task;
	private Long taskTime; 
	private String taskContent;
	private Boolean isComplete;
	//constructor
	public Sort(Task task,  String taskContent, boolean isComplete){
		this.task = task;
		//this.taskTime = taskTime;
		this.taskContent = taskContent;
		this.isComplete = isComplete;
	}
	//accessor
	public Task getTask(){
		return task;
	}
	
	/*public Long getTaskTime(){
		return taskTime;
	}*/
	
	public String getTaskContent(){
		return taskContent;
	}
	
	public Boolean getIsComplete(){
		return isComplete;
	}
	//mutator
	public void setTask(Task task){
		this.task = task;
	}
	
	/*public void setTaskTime(Long taskTime){
		this.taskTime = taskTime;
	}*/
	
	public void setTaskContent(String taskContent){
		this.taskContent = taskContent;
	}
	
	public void setIsComplete(Boolean isComplete){
		this.isComplete = isComplete;
	}
	
	//sort a task list by time then followed by alphabetic order of content
	public static ArrayList<Task> sortByTime(ArrayList<Task> listForSort){
		ArrayList<Task> sortResult = new ArrayList<Task> ();
		ArrayList<Sort> sortList = createSortList(listForSort);
		Collections.sort(sortList, new TaskComparator());
		for (int i = 0; i <sortList.size(); i++){
			sortResult.add(sortList.get(i).getTask());
		}
		return sortResult;
	}
	

	
	
	//create sortlist with sort objects for sort
	private static ArrayList<Sort> createSortList(ArrayList<Task> listForSort) {
		ArrayList<Sort> sortList = new ArrayList<Sort> ();
		for (int i = 0; i<listForSort.size();i++){
			Task task = listForSort.get(i);
			sortList.add(new Sort(task, task.getTaskDescription(),task.getIsCompleted()));
		}
		
		return sortList;
	}
	
	//create calendar from task timing TODO
	/*private static Long createTaskTime(Task task) {
		Long taskTime = Long.valueOf(0);
		if (task.getTaskType().equals("event")){
			String [] date = task.getStartDate().split("/");
			int day = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			int hour = Integer.parseInt(task.getStartTime().substring(0, 2));
			int minute = Integer.parseInt(task.getStartTime().substring(2));
			taskTime = Long.valueOf(minute+hour*100+day*10000+month*1000000+year*100000000);
			p(year);
			p(month);
			p(day);
			p(hour);
			p(minute);
			System.out.println(taskTime);
		}
		
		if (task.getTaskType().equals("deadline")){
		String [] date = task.getEndDate().split("/");
		int day = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);
		int hour = Integer.parseInt(task.getEndTime().substring(0, 2));
		int minute = Integer.parseInt(task.getEndTime().substring(2));
		taskTime = Long.valueOf(minute+hour*100+day*10000+month*1000000+year*100000000);
		p(year);
		p(month);
		p(day);
		p(hour);
		p(minute);
		System.out.println(taskTime);
		}
		
		return taskTime;
	}*/
	private static void p(int toprint){
		System.out.println(toprint);
	}
	

	
	//Yongzhi's Code
/*     
     * Sorts task by task type and description in the taskList
     * @@author Lim Yong Zhi
     
    public static ArrayList<Task> sortTaskList (ArrayList<Task> taskList) {
        Collections.sort(taskList, new TaskComparatorByTaskDescription());
        return taskList;
    }
}

*//**
 * Comparator override methods for sorting purposes
 * @@author Lim Yong Zhi
 *//*


 * Sorts taskList by Task Description
 
class TaskComparatorByTaskDescription implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getTaskDescription().compareTo(t2.getTaskDescription());
    }
}


 * Sorts taskList by Date
 
class TaskComparatorByDate implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getStartDate() == null || t2.getStartDate() == null) {
            return 0;
        }
        return t1.getStartDate().compareTo(t2.getStartDate());
    }
}


 * Sorts taskList by Time
 
class TaskComparatorByTime implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1.getStartTime() == null || t2.getStartTime() == null) {
            return 0;
        }
        return t1.getStartTime().compareTo(t2.getStartTime());
    }
}



 * Sorts taskList by Task Type
 * TODO: May not be required
 
class TaskComparatorByTaskType implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getTaskType().compareTo(t2.getTaskType());
    }*/
}
