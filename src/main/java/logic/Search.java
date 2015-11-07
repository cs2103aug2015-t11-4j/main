package main.java.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.java.resources.Task;
import main.java.storage.Storage;

/**
 * This class handles all search operations and executes the 
 * appropriate action as requested by the Logic component
 * 
 * @@author Lim Yong Zhi; A0124524N wenbin
 */

public class Search {
    
	private static final LocalDateTime TODAY = LocalDateTime.now();
	private static final LocalDateTime TMR = TODAY.plusDays(1);
	
    /* Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's day as a summary
     * PREREQUISITE: Storage.taskList must be sorted by date and time
    */
    public static ArrayList<Task> obtainTodaySummary(Storage storage) {
        ArrayList<Task> todaySummary = new ArrayList<Task>();
        ArrayList<Task> taskList = storage.getTaskList();
        
        /* 
         *@@Author: Lim Yong Zhi
        while(todaySummary.size() == 25 || taskList.isEmpty()) {
            for(int i = 0; i<taskList.size(); i++) {
                if((taskList.get(i).getTaskType().equals("deadline"))) { 
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }
            }

            for(int i = 0; i<10; i++) {
                if(taskList.get(i).getTaskType().equals("event")) {
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }
            }

            for(int i = 0; i<5; i++) { 
                if(taskList.get(i).getTaskType().equals("floating")) {
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }
            }
        }
        */
        
        //@@Author: A0124524N wenbin
        //add only top 10 urgent deadlines into list first
        ArrayList<Task> urgentDeadlines = new ArrayList<Task>();
        urgentDeadlines = obtainApproachingDeadlineTasks(TODAY, taskList);
        int counterDeadlines = 0;
        for(int i=0; i<urgentDeadlines.size(); i++) {
        	todaySummary.add(urgentDeadlines.get(i));
        	counterDeadlines++;
        	if(counterDeadlines == 10)
        		break;
        }
        //add only top 10 events into list
        ArrayList<Task> todayEvents = new ArrayList<Task>();
        todayEvents = obtainTodayEvents(TODAY, taskList);
        int counterEvents = 0;
        for(int i=0; i<todayEvents.size(); i++) {
        	todaySummary.add(todayEvents.get(i));
        	counterEvents++;
        	if(counterEvents == 10)
        		break;
        }
        //add only top 5 events into list
        int counterFloating = 0;
        for(int i=0; i<taskList.size(); i++) {
        	if(taskList.get(i).getTaskType().equals("floating") && taskList.get(i).getIsCompleted() == false) {
        		todaySummary.add(taskList.get(i));
            	counterFloating++;
        	}
        	if(counterFloating == 5)
        		break;
        }        
        return todaySummary;
    }
    
    /* Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's next day as a summary
     * PREREQUISITE: Storage.taskList must be sorted by date and time
     */
    //@@Author: A0124524N wenbin
    public static ArrayList<Task> obtainTommorrowSummary(Storage storage) { 
        ArrayList<Task> tmrSummary = new ArrayList<Task>();
        ArrayList<Task> taskList = storage.getTaskList();
        
        //add only top 10 urgent deadlines into list first
        ArrayList<Task> urgentDeadlines = new ArrayList<Task>();
        urgentDeadlines = obtainApproachingDeadlineTasks(TMR, taskList);
        int counterDeadlines = 0;
        for(int i=0; i<urgentDeadlines.size(); i++) {
        	tmrSummary.add(urgentDeadlines.get(i));
        	counterDeadlines++;
        	if(counterDeadlines == 10)
        		break;
        }
        //add only top 10 events into list
        ArrayList<Task> todayEvents = new ArrayList<Task>();
        todayEvents = obtainTodayEvents(TMR, taskList);
        int counterEvents = 0;
        for(int i=0; i<todayEvents.size(); i++) {
        	tmrSummary.add(todayEvents.get(i));
        	counterEvents++;
        	if(counterEvents == 10)
        		break;
        }
        //add only top 5 events into list
        int counterFloating = 0;
        for(int i=0; i<taskList.size(); i++) {
        	if(taskList.get(i).getTaskType().equals("floating") && taskList.get(i).getIsCompleted() == false) {
        		tmrSummary.add(taskList.get(i));
            	counterFloating++;
        	}
        	if(counterFloating == 5)
        		break;
        }        
        return tmrSummary;
    }
    
    
    //Obtains all tasks in the taskList
    public static ArrayList<Task> obtainAllTasks(Storage storage) {
            return storage.getTaskList();
    }
    
    //Obtains all incomplete tasks in the taskList
    public static ArrayList<Task> obtainAllIncompleteTasks(Storage storage) {
        ArrayList<Task> incomplete = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getIsCompleted() == false) {
                incomplete.add(storage.getTaskList().get(i));
            }
        }
        return incomplete;
    }
    
    //Obtains all complete tasks in the taskList
    public static ArrayList<Task> obtainAllCompleteTasks(Storage storage) {
       ArrayList<Task> complete = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getIsCompleted() == true) {
                complete.add(storage.getTaskList().get(i));
            }
        }
        return complete;
    }
    

    //Obtains all deadline tasks in the taskList
    public static ArrayList<Task> obtainDeadlineTasks(Storage storage) {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getTaskType().equals("deadline")) {
                deadlines.add(storage.getTaskList().get(i));
            }
        }
        return deadlines;
    }
    

    //Obtains all tasks approaching deadlines (3 days) in the taskList 
    private static ArrayList<Task> obtainApproachingDeadlineTasks(LocalDateTime date, ArrayList<Task> fullStorageTaskList) {
        ArrayList<Task> urgentDeadlines = new ArrayList<Task>();
        
        //@@author: Lim Yong Zhi
        /*for(int i = 0; i<task.size(); i++) {
            if(Storage.getTaskList().get(i).getTaskType().equals("deadline")
                    && (Storage.getTaskList().get(i).getEndDate().equals(ldt.getDayOfMonth()  + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.getTaskList().get(i).getEndDate().equals((ldt.getDayOfMonth() + 1) + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.getTaskList().get(i).getEndDate().equals((ldt.getDayOfMonth() + 2) + "/" + toMonthValue() + "/" + ldt.getYear()))) {
                deadlines.add(Storage.getTaskList().get(i));
            }
        }*/
        
        //@@Author: A0124524N wenbin
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String today = date.format(formatter);
    	String tmr = date.plusDays(1).format(formatter);
    	String dayAfter = date.plusDays(2).format(formatter);
    	//assumes that fullStorageTaskList is already sorted according to increasing date
    	for(int i=0; i<fullStorageTaskList.size(); i++) {
    		if(fullStorageTaskList.get(i).getTaskType().equals("deadline") && fullStorageTaskList.get(i).getIsCompleted() == false) {
        		//find all deadlines due today and add to list
        		if(fullStorageTaskList.get(i).getEndDate().equals(today))
        			urgentDeadlines.add(fullStorageTaskList.get(i));
        		//find all deadlines due tmr and add to list
        		if(fullStorageTaskList.get(i).getEndDate().equals(tmr))
        			urgentDeadlines.add(fullStorageTaskList.get(i));
        		//find all deadlines due day after and add to list
        		if(fullStorageTaskList.get(i).getEndDate().equals(dayAfter))
        			urgentDeadlines.add(fullStorageTaskList.get(i));
    		}
    	}
        return urgentDeadlines;
    }
    
    //Obtains all tasks happening on the date
    //@@Author: A0124524N wenbin
    private static ArrayList<Task> obtainTodayEvents(LocalDateTime date, ArrayList<Task> fullStorageTaskList) {
        ArrayList<Task> eventsToday = new ArrayList<Task>();
        
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String today = date.format(formatter);
    	for(int i=0; i<fullStorageTaskList.size(); i++) {
    		if(fullStorageTaskList.get(i).getTaskType().equals("event") && fullStorageTaskList.get(i).getIsCompleted() == false) {
    			if(fullStorageTaskList.get(i).getStartDate().equals(today))
    				eventsToday.add(fullStorageTaskList.get(i));
    		}
    	}
    	return eventsToday;
    }
  

    //Obtains all tasks tagged with events as task type in the taskList
    public static ArrayList<Task> obtainEventTasks(Storage storage) {
    	ArrayList<Task> events = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getTaskType().equals("event")) {
                events.add(storage.getTaskList().get(i));
            }
        }
        return events;
    }
    
    
    //Obtains all tasks tagged with floating as task type in the taskList
    public static ArrayList<Task> obtainFloatingTasks(Storage storage) {
        ArrayList<Task> floating = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getTaskType().equals("floating")) {
                floating.add(storage.getTaskList().get(i));
            }
        }
        return floating;
    }
    
    //Searches and retrieves all tasks with the keyword
    public ArrayList<Task> SearchKeyword(String keyword, Storage storage) {
        ArrayList<Task> results = new ArrayList<Task>();
        
        for(int i = 0; i<storage.getTaskList().size(); i++) {
            if(storage.getTaskList().get(i).getEndDate().contains(keyword)
                    || storage.getTaskList().get(i).getEndTime().contains(keyword)
                    || storage.getTaskList().get(i).getStartDate().contains(keyword)
                    || storage.getTaskList().get(i).getStartTime().contains(keyword)
                    || storage.getTaskList().get(i).getTaskDescription().contains(keyword)) {
                results.add(storage.getTaskList().get(i));
            }
        }
        
        if(results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    public static Task obtainTaskByItemNum(int itemNumber, ArrayList<Task> list) {
        return list.get(itemNumber-1);
    }
    
    public static int obtainItemNumByTask(Task task, ArrayList<Task> list){
		int size = list.size();
		int itemNum = 0;
		for (int i = 0; i < size; i ++){
			if (list.get(i).equals(task))
				itemNum = i+1;
		}
    	return itemNum;
    }
    
    //@@author: wenbin
    public static ArrayList<Task> obtainRecurTaskListByItemNum(int itemNumber, ArrayList<Task> list) {
    	Task target = list.get(itemNumber-1);
    	int recurGroupID = target.getRecurringID();
    	Storage storage = Storage.getInstance();
    	ArrayList<Task> recurTaskGroup = new ArrayList<Task>();
    	
    	for(int i=0; i<storage.getTaskList().size(); i++) {
    		if(storage.getTaskList().get(i).getRecurringID() == recurGroupID) {
    			recurTaskGroup.add(storage.getTaskList().get(i));
    		}
    	}
    	return recurTaskGroup;
    }
    
    //@@Author: Jiahuan
	public static int obtainLargestRecurID() {
		Storage storage = Storage.getInstance();
		int temp, largestRecurID = 0;
		for (int i = 0; 1 < storage.getTaskList().size(); i++){
			System.out.println("RecurID is" + storage.getTaskList().get(i).getRecurringID());
			if (storage.getTaskList().get(i).getRecurringID() > largestRecurID){
				
				temp = storage.getTaskList().get(i).getRecurringID();
				largestRecurID = temp;
			}
		}
		return largestRecurID;
	}
    
    
    /* @@Author: Lim Yong Zhi
     * Return the corrected month format to compare with date stored in taskList
     * Example: 01 (January) is returned instead of 1
    private static String toMonthValue() {
        if((ldt.getMonthValue() == 1) || (ldt.getMonthValue() == 2) || (ldt.getMonthValue() == 3) || (ldt.getMonthValue() == 4) || (ldt.getMonthValue() == 5) ||
                (ldt.getMonthValue() == 6) || (ldt.getMonthValue() == 7) || (ldt.getMonthValue() == 8) || (ldt.getMonthValue() == 9)) {
            return "0" + ldt.getMonthValue();
        }
        return "" + ldt.getMonthValue();
    }
    */
    
}