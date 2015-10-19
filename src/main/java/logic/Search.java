package main.java.logic;

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
     * Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's day as a summary
     * 
     * TODO: Verfiy method is working
     * PREREQUISITE: Storage.taskList must be sorted by date and time
     */
    public ArrayList<Task> obtainTodaySummary() {
        ArrayList<Task> todaySummary = new ArrayList<Task>();
        
        if(Storage.taskList.size() >= 25) {
            for(int i = 0; i<10; i++) {
                if(Storage.taskList.get(i).getTaskType().equals("deadline")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        
            for(int i = 0; i<10; i++) {
                if(Storage.taskList.get(i).getTaskType().equals("event")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        
            for(int i = 0; i<5; i++) {
                if(Storage.taskList.get(i).getTaskType().equals("floating")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        } else {
            
            for(int i = 0; i<Storage.taskList.size(); i++) {
                if(Storage.taskList.get(i).getTaskType().equals("deadline")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        
            for(int i = 0; i<Storage.taskList.size(); i++) {
                if(Storage.taskList.get(i).getTaskType().equals("event")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        
            for(int i = 0; i<Storage.taskList.size(); i++) {
                if(Storage.taskList.get(i).getTaskType().equals("floating")) {
                    todaySummary.add(Storage.taskList.get(i));
                }
            }
        }
        return todaySummary;
    }
    
    /*
     * Obtains 10 deadlines, 10 events and 5 floating tasks to be seen in
     * the user's next day as a summary
     * 
     * TODO: Method is currently incomplete
     * PREREQUISITE: Storage.taskList must be sorted by date and time
     */
    public ArrayList<Task> obtainTommorrowSummary(ArrayList<Task> Tasks) {
        return null;
    }
    
    /*
     * Obtains all tasks in the taskList
     */
    public ArrayList<Task> obtainAllTasks() {
        try {
            return Storage.taskList;
        } catch (Exception e) {
            return null;
        }
    }
    
    /*
     * Obtains all incomplete tasks in the taskList
     */
    public ArrayList<Task> obtainAllIncompleteTasks() {
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
    public ArrayList<Task> obtainAllCompleteTasks() {
        ArrayList<Task> complete = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getIsCompleted() == true) {
                complete.add(Storage.taskList.get(i));
            }
        }
        
        return complete;
    }
    
    /*
     * Obtains all tasks approaching deadlines (3 days) in the taskList
     */
    public ArrayList<Task> obtainDeadlineTasks() {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.taskList.size(); i++) {
            if(Storage.taskList.get(i).getTaskType().equals("deadline")) {
                deadlines.add(Storage.taskList.get(i));
            }
        }
        
        return deadlines;
    }
    
    /*
     * Obtains all tasks tagged with events as task type in the taskList
     */
    public ArrayList<Task> obtainEventTasks(ArrayList<Task> Tasks) {
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
    public ArrayList<Task> obtainFloatingTasks(ArrayList<Task> Tasks) {
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
            if(Storage.taskList.get(i).getTaskDescription().contains(keyword)) {
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