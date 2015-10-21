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
    public static ArrayList<Task> obtainTodaySummary(Storage storage) {
        /*ArrayList<Task> todaySummary = new ArrayList<Task>();
        ArrayList<Task> taskList = Storage.getTaskList();
        
        while(todaySummary.size() == 25 || taskList.isEmpty()) {
            for(int i = 0; i<10; i++) {
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
        Sort.sortTaskList(todaySummary);*/
        return storage.getTaskList();
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
    public static ArrayList<Task> obtainTommorrowSummary(Storage storage) {
        return storage.getTaskList();
    }
    
    /*
     * Obtains all tasks in the taskList
     */
    public static ArrayList<Task> obtainAllTasks(Storage storage) {
        /*if(!Storage.getTaskList().isEmpty()) {
            return Storage.getTaskList();
        }*/
        return storage.getTaskList();
    }
    
    /*
     * Obtains all incomplete tasks in the taskList
     */
    public static ArrayList<Task> obtainAllIncompleteTasks(Storage storage) {
/*        ArrayList<Task> incomplete = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getIsCompleted() == false) {
                incomplete.add(Storage.getTaskList().get(i));
            }
        }
        */
        return storage.getTaskList();
    }
    
    /*
     * Obtains all complete tasks in the taskList
     */
    public static ArrayList<Task> obtainAllCompleteTasks(Storage storage) {
/*        ArrayList<Task> complete = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getIsCompleted() == true) {
                complete.add(Storage.getTaskList().get(i));
            }
        }
        */
        return storage.getTaskList();
    }
    
    /*
     * Obtains all deadline tasks in the taskList
     */
    public static ArrayList<Task> obtainDeadlineTasks(Storage storage) {
/*        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getTaskType().equals("deadline")) {
                deadlines.add(Storage.getTaskList().get(i));
            }
        }
        */
        return storage.getTaskList();
    }
    
    /*
     * Obtains all tasks approaching deadlines (3 days) in the taskList
     */
    public ArrayList<Task> obtainApproachingDeadlineTasks(Storage storage) {
        ArrayList<Task> deadlines = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getTaskType().equals("deadline")
                    && (Storage.getTaskList().get(i).getEndDate().equals(ldt.getDayOfMonth()  + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.getTaskList().get(i).getEndDate().equals((ldt.getDayOfMonth() + 1) + "/" + toMonthValue() + "/" + ldt.getYear()))
                    && (Storage.getTaskList().get(i).getEndDate().equals((ldt.getDayOfMonth() + 2) + "/" + toMonthValue() + "/" + ldt.getYear()))) {
                deadlines.add(Storage.getTaskList().get(i));
            }
        }
        
        return deadlines;
    }
    
    /*
     * Obtains all tasks tagged with events as task type in the taskList
     */
    public static ArrayList<Task> obtainEventTasks(Storage storage) {
/*        ArrayList<Task> events = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getTaskType().equals("event")) {
                events.add(Storage.getTaskList().get(i));
            }
        }
        */
        return storage.getTaskList();
    }
    
    /*
     * Obtains all tasks tagged with floating as task type in the taskList
     */
    public static ArrayList<Task> obtainFloatingTasks(Storage storage) {
/*        ArrayList<Task> floating = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getTaskType().equals("floating")) {
                floating.add(Storage.getTaskList().get(i));
            }
        }
        */
        return storage.getTaskList();
    }
    
    /*
     * Searches and retrieves all tasks with the keyword
     * TODO: Method needs to search for date and time
     */
    public ArrayList<Task> SearchKeyword(String keyword) {
        ArrayList<Task> results = new ArrayList<Task>();
        
        for(int i = 0; i<Storage.getTaskList().size(); i++) {
            if(Storage.getTaskList().get(i).getEndDate().contains(keyword)
                    || Storage.getTaskList().get(i).getEndTime().contains(keyword)
                    || Storage.getTaskList().get(i).getStartDate().contains(keyword)
                    || Storage.getTaskList().get(i).getStartTime().contains(keyword)
                    || Storage.getTaskList().get(i).getTaskDescription().contains(keyword)) {
                results.add(Storage.getTaskList().get(i));
            }
        }
        
        if(results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    public static Task obtainTaskByItemNum(int itemNumber) {
        return Storage.getTaskList().get(itemNumber-1);
    }
}