package main.java.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.java.resources.Task;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */


public class Storage {
    private static ArrayList<Task> taskList; // a global variable for task list (jh)
    
    private static Storage storage;
    
    //private constructor
    private Storage() {
        taskList = new ArrayList<Task>();
    }
    
    //access to object, create one if there is none
    public static Storage getInstance(){
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }
    
    //access to task list
    public static ArrayList<Task> getTaskList(){
        return taskList;
    }
    
    /* 
	 * Temporary placeholder for creation of file - To be replaced with user's
	 * directory of choice
	 */
	private static String filename = "MyCalender.txt";
	
	/* 
     * Adds one task to the taskList and writes to external file
     */
	public static int addOneItem(Task task) {
		taskList.add(task); //(jh) update internal list
		
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getStartDate()
					+ ";" + task.getEndDate() + ";" + task.getStartTime() + ";" + task.getEndTime() + ";"
					+ task.getIsCompleted() + ";" );
			
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	/* 
     * Updates one task to the taskList and writes to external file
     */
	public static int updateOneItem(int itemNumber, Task task) {
		taskList.set(itemNumber-1, task); //(jh) update internal list
		
		try {
		    FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
		    
            int lineNumber = 0;
            String input = "";
            String line;
            String replaceLine = "";
            
            while ((line = br.readLine()) != null) {        
                lineNumber += 1;
                input += line + '\n';
                
                if (lineNumber == itemNumber) {
                    replaceLine = line + '\n';
                }
            }
            
            br.close();
            
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(input.replaceAll(replaceLine, task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getStartDate()
            + ";" + task.getEndDate() + ";" + task.getStartTime() + ";" + task.getEndTime() + ";" + task.getIsCompleted() + ";"  + "\n"));
            
            bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	/* 
     * Deletes a task from the taskList and delete entry from external file
     */
	public static int deleteOneItem(int itemNumber) {
		taskList.remove(itemNumber-1); //(jh) update internal list
		
		try {
		    File original = new File(filename);
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            File temp = new File("Alt4.tmp");
            FileWriter fw = new FileWriter("Alt4.tmp", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            int lineNumber = 0;
            String line = "";
            
            while ((line = br.readLine()) != null) {
                lineNumber += 1;
                
                if (lineNumber != itemNumber) {
                    bw.write(line);
                    bw.newLine();
                }
            }
                               
            br.close();
            bw.close();
            original.delete();
            temp.renameTo(original);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	/* 
     * Obtains task type from task saved in external file
     */
    public static String getTaskTypeByItemNum(int itemNumber) {
        try {
            String[] target = readExternalFile(itemNumber);
            return target[0];
        } catch (Exception e) {
            return null;
        }
    }
    
    /* 
     * Obtains task description from task saved in external file
     */
    public static String getTaskDescriptionByItemNum(int itemNumber) {
        try {
            String[] target = readExternalFile(itemNumber);
            return target[1];
        } catch (Exception e) {
            return null;
        }
    }

    /* 
     * Obtains deadline from task saved in external file
     */
    public static String getTaskDeadlineItemNum(int itemNumber) {
        try {
            String[] target = readExternalFile(itemNumber);
            return target[2];
        } catch (Exception e) {
            return null;
        }
    }
    
    /*
     * Reads the external file based on its line number
     */
    private static String[] readExternalFile(int itemNumber) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);        
           
        int lineNumber = 0;
        String line = null;
        String[] target = new String[4];
      
        while ((line = br.readLine()) != null) {
            lineNumber += 1;
        
            if (lineNumber == itemNumber) {
                target = line.split(";");
            }
        }
      
        br.close();
        return target;
    }
    

    /* 
     * Sorts task by task type and description in the taskList
     */
    public static ArrayList<Task> sortTaskList (ArrayList<Task> taskList) {
        Collections.sort(taskList, new TaskComparatorByTaskDescription());
        return taskList;
    }
    
	/* 
     * Displays all tasks to the taskList
     * @@author A0126058-unused due to change of requirements
     
	public static void display() {
        try {
            File file = new File(filename);
            
            if (file.length() == 0) {
                //System.out.println(String.format(MESSAGE_DISPLAY_EMPTY, filename));
            } else {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                
                int lineNumber = 0;
                String line = "";
            
                while ((line = br.readLine()) != null) {
                        lineNumber += 1;
                        System.out.println(lineNumber + ". " + line);
                }
            
                br.close();
            }
       } catch (Exception e) {
            //System.out.println(MESSAGE_READ_ERROR);
       }
	}
	*/
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