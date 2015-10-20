package main.java.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;

import main.java.resources.Task;
import main.java.storage.Storage;

/**
 * This class handles all search operations and executes the 
 * appropriate action as requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */

public class Search {
    /*
     * Instantiate LocalDateTime to user's computer time
     */
    LocalDateTime ldt = LocalDateTime.now();
    
    /*
     * Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's day as a summary
     * 
     * TODO: Verify method is working
     * PREREQUISITE: Storage.taskList must be sorted by date and time
     */
    public static ArrayList<Task> obtainTodaySummary() {
        ArrayList<Task> todaySummary = new ArrayList<Task>();
        ArrayList<Task> taskList = Storage.taskList;
        
        while(todaySummary.size() == 25 || taskList.isEmpty()) {
            for(int i = 0; i<25; i++) {
                if((taskList.get(i).getTaskType().equals("deadline"))) { 
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }

                if(taskList.get(i).getTaskType().equals("event")) {
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }

                if(taskList.get(i).getTaskType().equals("floating")) {
                    todaySummary.add(taskList.get(i));
                    taskList.remove(i);
                }
            }
        }
        Storage.sortTaskList(todaySummary);
        return todaySummary;
    }
    
    /*
     * Return the corrected month format to compare with date stored in taskList
     * Example: 01 (January) is returned instead of 1
     */
    private String toMonthValue() {
        if((ldt.getMonthValue() == 1) || (ldt.getMonthValue() == 2) || (ldt.getMonthValue() == 3) || (ldt.getMonthValue() == 4) || (ldt.getMonthValue() == 5) ||
                (ldt.getMonthValue() == 6) || (ldt.getMonthValue() == 7) || (ldt.getMonthValue() == 8) || (ldt.getMonthValue() == 9)) {
            return "0" + ldt.getMonthValue();
        }
        return "" + ldt.getMonthValue();
    }
    
    /*
     * Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's next day as a summary
     * 
     * TODO: Method is currently incomplete
     * PREREQUISITE: Storage.taskList must be sorted by date and time
     */
    public static ArrayList<Task> obtainTommorrowSummary() {
        return null;
    }
    
    /*
     * Obtains all tasks in the taskList
     */
    public static ArrayList<Task> obtainAllTasks() {
        if(!Storage.taskList.isEmpty()) {
            return Storage.taskList;
        }
        return null;
    }
    
    /*
     * Obtains all incomplete tasks in the taskList
     */
    public static ArrayList<Task> obtainAllIncompleteTasks() {
        ArrayList<Task> incomplete = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getIsCompleted() == false) {
                incomplete.add(Storage.taskList.get(i));
            }
        }
        
        return incomplete;
    }
    
    /*
     * Obtains all complete tasks in the taskList
     */
    public static ArrayList<Task> obtainAllCompleteTasks() {
        ArrayList<Task> complete = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getIsCompleted() == true) {
                complete.add(Storage.taskList.get(i));
            }
        }
        
        return complete;
    }
    
    /*
     * Obtains all deadline tasks in the taskList
     */
    public static ArrayList<Task> obtainDeadlineTasks() {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getTaskType().equals("deadline")) {
                deadlines.add(Storage.taskList.get(i));
            }
        }
        
        return deadlines;
    }
    
    /*
     * Obtains all tasks approaching deadlines (3 days) in the taskList
     */
    public ArrayList<Task> obtainApproachingDeadlineTasks() {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getTaskType().equals("deadline")
                    && (Storage.taskList.get(i).getEndDate().equals(ldt.getDayOfMonth()  + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.taskList.get(i).getEndDate().equals((ldt.getDayOfMonth() + 1) + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.taskList.get(i).getEndDate().equals((ldt.getDayOfMonth() + 2) + "/" + toMonthValue() + "/" + ldt.getYear()))) {
                deadlines.add(Storage.taskList.get(i));
            }
        }
        
        return deadlines;
    }
    
    /*
     * Obtains all tasks tagged with events as task type in the taskList
     */
    public static ArrayList<Task> obtainEventTasks() {
        ArrayList<Task> events = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getTaskType().equals("event")) {
                events.add(Storage.taskList.get(i));
            }
        }
        
        return events;
    }
    
    /*
     * Obtains all tasks tagged with floating as task type in the taskList
     */
    public static ArrayList<Task> obtainFloatingTasks() {
        ArrayList<Task> floating = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getTaskType().equals("floating")) {
                floating.add(Storage.taskList.get(i));
            }
        }
        
        return floating;
    }
    
    /*
     * Searches and retrieves all tasks with the keyword
     * TODO: Method needs to search for date and time
     */
    public ArrayList<Task> SearchKeyword(String keyword) {
        ArrayList<Task> results = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getEndDate().contains(keyword)
                    || Storage.taskList.get(i).getEndTime().contains(keyword)
                    || Storage.taskList.get(i).getStartDate().contains(keyword)
                    || Storage.taskList.get(i).getStartTime().contains(keyword)
                    || Storage.taskList.get(i).getTaskDescription().contains(keyword)) {
                results.add(Storage.taskList.get(i));
            }
        }
        
        if(results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }
}