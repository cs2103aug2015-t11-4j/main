package main.java.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.logic.Sort;
import main.java.resources.Task;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @author Lim Yong Zhi
 * @@author A0126058
 */

public class Storage {
    private static ArrayList<Task> taskList; // a global variable for task list (jh)
    Logger logger = Logger.getLogger("Storage");
    
    private static Storage storage;
    
    private Storage() {
        taskList = new ArrayList<Task>();
    }
    
    /**
     * Creates an access to the Storage object and creates one if there is none.
     * @return a newly created storage object for manipulation
     */
    public static Storage getInstance(){
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }
    
    /**
     * Creates an access to the internal taskList.
     * @return the internal taskList containing an ArrayList of tasks
     */
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    
    private String directory = "";
    private String filename = "Alt4.txt";
    private final int SUCCESS = 0;
    private final int FAILURE = -1;
    
	/** 
     * Allows user to change the destination of the external file and then writes to the
     * new location.
     * 
     * Returns an integer if the process is successful (0) or unsuccessful (-1).
     * 
     * @param  setDirectory a string containing a path in the file system
     * @return an integer containing a success (0) or failure (-1) code
     */
	public int changeDirectory(String setDirectory) {
	    int code = 0;  
	    
	    if (setDirectory.equals("default") || setDirectory.equals("\\") || setDirectory.equals("/")) {
	        code = performDirectoryDefaults();
	    } else {
	        code = performDirectoryChange(setDirectory);    
	    }
	    
	    return code;
	}
	
	/**
	 * Returns the external file location to defaults. Default location is relative to Alt4.java.
	 * 
	 * @return an integer containing a success (0) or failure (-1) code
	 */
    private int performDirectoryDefaults() {
        try {
            File file = new File(directory + "\\" + filename);
            file.delete();

            directory = "";
            filename = "Alt4.txt";

            // Blanks off the file
            FileWriter fw2 = new FileWriter(filename, false);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            bw2.write("");

            // Copies all existing tasks in the taskList onto the default location
            FileWriter fw1 = new FileWriter(filename, true);
            BufferedWriter bw1 = new BufferedWriter(fw1);

            for (int i = 0; i < taskList.size(); i++) {
                bw1.write(taskList.get(i).getTaskType() + ";" + taskList.get(i).getTaskDescription() + ";" + taskList.get(i).getStartDate()
                        + ";" + taskList.get(i).getEndDate() + ";" + taskList.get(i).getStartTime() + ";" + taskList.get(i).getEndTime() + ";"
                        + taskList.get(i).getIsCompleted() + ";" + taskList.get(i).getIsDateTimeValid() + ";" + taskList.get(i).getRecurringID() + ";" );

                bw1.newLine();
            }

            bw1.close();
            bw2.close();
            return SUCCESS;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to create external file in {0}!", directory);
            return FAILURE;
        }
    }
    
    /**
     * Performs the change of the destination of the external file by providing a directory path to setDirectory.
     * 
     * @param setDirectory  a string containing a path in the file system
     * @return an integer containing a success (0) or failure (-1) code
     */
    private int performDirectoryChange(String setDirectory) {
        try {
            directory = setDirectory;

            File file = new File(directory, filename);

            if (file.exists()) {
                file.createNewFile(); 
            }

            file.getParentFile().mkdirs();

            // Copies all existing tasks in the taskList onto the new location
            FileWriter fw1 = new FileWriter(directory + "\\" + filename, true);
            BufferedWriter bw1 = new BufferedWriter(fw1);

            for (int i = 0; i < taskList.size(); i++) {
                bw1.write(taskList.get(i).getTaskType() + ";" + taskList.get(i).getTaskDescription() + ";" + taskList.get(i).getStartDate()
                        + ";" + taskList.get(i).getEndDate() + ";" + taskList.get(i).getStartTime() + ";" + taskList.get(i).getEndTime() + ";"
                        + taskList.get(i).getIsCompleted() + ";" + taskList.get(i).getIsDateTimeValid() + ";" + taskList.get(i).getRecurringID() + ";" );

                bw1.newLine();
            }

            // Writes the user's set directory onto the first line
            FileWriter fw2 = new FileWriter(filename, false);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            bw2.write(directory + ";");

            bw1.close();
            bw2.close();
            return SUCCESS;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to create external file in {0}!", directory);
            return FAILURE;
        }
    }
    
    /** 
     * Reads the external file to regenerate the taskList.
     * 
     * If the external file contains a path, the external file shall be read at that 
     * location. If not, an empty taskList will be created.
     */
    public void regenerateTaskList() {
        try {
            logger.log(Level.INFO, "Regenerating internal taskList from external file!");

            // Ensure internal taskList is emptied before regeneration 
            if (!taskList.isEmpty()) {
                wipeTaskList();
            }
            
            // Checks if the first line of the external file is a directory path
            if (retrieveDirectory()) {
                filename = directory + "\\" + filename;
                System.out.println("inside retrievedirectory");
            }
            System.out.println("inside regeneratetasklist");
            System.out.println(filename);
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            @SuppressWarnings("unused")
            String line;
            int count = 0;
            
            while ((line = br.readLine()) != null) {
                taskList.add(new Task(getTaskTypeByItemNum(count),
                        getTaskDescriptionByItemNum(count),
                        getStartDateByItemNum(count),
                        getEndDateByItemNum(count),
                        getStartTimeByItemNum(count),
                        getEndTimeByItemNum(count),
                        getIsCompletedByItemNum(count),
                        getIsDateTimeValidByItemNum(count),
                        getRecurringIDByItemNum(count)));
                count += 1;
            }
            br.close();
            
            sortTaskList(taskList);
            logger.log(Level.INFO, "Completed regeneration of internal taskList from external file");
        } catch (Exception e) {
            File file = new File(filename);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ioe) {
                    logger.log(Level.SEVERE, "Unable to create {0}. Check if you have read/write permissions.", filename);
                }
            }
            logger.log(Level.INFO, "No tasks to generate from external file. Creating {0}.", filename);
        }
    }

    /** 
     * Wipes the current taskList.
     * <p>
     * This method is to be used with caution as it clears all tasks in the internal taskList.
     */
    private void wipeTaskList() {
        logger.log(Level.WARNING, "Wiping taskList!");
        taskList.clear();
    }
    
    /** 
     * Checks the first line external file if it contains a directory
     * and if so, retrieves it.
     * <p>
     * External file saves the path of the user's directory of choice 
     * to the first line of the external file
     * 
     * @return  boolean returns true if a path is found in the external file and false
     *                  if a task is found instead.    
     */
    private boolean retrieveDirectory() {
        String[] getDirectory;
        
        if (getTaskTypeByItemNum(0).equals("deadline") 
           || getTaskTypeByItemNum(0).equals("event") 
           || getTaskTypeByItemNum(0).equals("floating")) { 
            return false;
        }
        logger.log(Level.INFO, "Retrieving directory from external file!");
        getDirectory = readExternalFile(0);
        directory = getDirectory[0];
        return true;
    }
    
	
	/** 
     * Adds a task to the internal taskList, writes to external file then sorts the
     * internal taskList.
     * 
     * @param  task a task object containing details to a user's task
     * @return an integer containing a success (0) or failure (-1) code
     */
	public int addOneItem(Task task) {
	    logger.log(Level.INFO, "Adding {0} to taskList", task.getTaskDescription());
		taskList.add(task); //(jh) update internal list
		
		try {
		    logger.log(Level.INFO, "Writing {0} to external file", task.getTaskDescription());
		    
		    FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getStartDate()
					+ ";" + task.getEndDate() + ";" + task.getStartTime() + ";" + task.getEndTime() + ";"
					+ task.getIsCompleted() + ";" + task.getIsDateTimeValid() + ";" + task.getRecurringID() + ";" );
			
			bw.newLine();
			bw.close();			

            sortTaskList(taskList);
			logger.log(Level.INFO, "Completed writing {0} to external file", task.getTaskDescription());
	        return SUCCESS;
		} catch (Exception e) {
		    logger.log(Level.WARNING, "Unable to add {0}", task.getTaskDescription());
			return FAILURE;
		}
	}
	
	/** 
     * Deletes a task from the internal taskList, deletes an entry from external file 
     * then sorts the internal taskList.
     * 
     * @param  task a task object containing details to a user's task
     * @return an integer containing a success (0) or failure (-1) code
     */
    public int deleteOneItem(Task task) {
        logger.log(Level.INFO, "Deleting task {0} from external file", task.getTaskDescription());
        taskList.remove(task); //(jh) update internal list
        
        try {         
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            String input = "";
            String line;
            
            while ((line = br.readLine()) != null) {        
                input += line + '\n';
            }
            
            br.close();
            
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(input.replaceAll(task.getTaskType() + ";" + task.getTaskDescription() 
            + ";" + task.getStartDate() + ";" + task.getEndDate() + ";" + task.getStartTime()
            + ";" + task.getEndTime() + ";" + task.getIsCompleted() + ";" + task.getIsDateTimeValid() 
            + ";" + task.getRecurringID() + ";" + "\n", ""));
            
            bw.close();

            sortTaskList(taskList);
            logger.log(Level.INFO, "Deleted {0} from external file", task.getTaskDescription());
            return SUCCESS;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to delete task {0} from external file", task.getTaskDescription());
            return FAILURE;
        }
    }
    
    /** 
     * Sets a task to complete saved in external file
     * <p>
     * Checks to see if the task is complete before setting the task to complete in 
     * the internal taskList. After that, it replaces the task stored in the external
     * file before returning a sorted taskList.
     * 
     * @param  task a task object containing details to a user's task
     * @return an integer containing a success (0) or failure (-1) code
     */
    public int completeOneItem(Task task) {
        try {
            if (task.getIsCompleted() != true) {
                task.setCompleted(true);

                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);

                String input = "";
                String line;
                String replaceLine = task.getTaskType() + ";" + task.getTaskDescription() 
                + ";" + task.getStartDate() + ";" + task.getEndDate() + ";" + task.getStartTime()
                + ";" + task.getEndTime() + ";" + false + ";" + task.getIsDateTimeValid() + ";";

                while ((line = br.readLine()) != null) {        
                    input += line + '\n';
                }

                br.close();

                FileWriter fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(input.replaceAll(replaceLine, task.getTaskType() + ";" + task.getTaskDescription() 
                + ";" + task.getStartDate() + ";" + task.getEndDate() + ";" + task.getStartTime()
                + ";" + task.getEndTime() + ";" + task.getIsCompleted() + ";" + task.getIsDateTimeValid() 
                + ";" + task.getRecurringID() + ";"));

                bw.close();

                sortTaskList(taskList);
                logger.log(Level.INFO, "Completed task {0} from external file", task.getTaskDescription());
                return SUCCESS;
            } else {
                logger.log(Level.WARNING, "Task {0} is already completed!", task.getTaskDescription());
                return FAILURE;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to complete task {0} from external file", task.getTaskDescription());
            return FAILURE;
        }
    }
    
    /** 
     * Sets a task to incomplete saved in external file
     * <p>
     * Checks to see if the task is incomplete before setting the task to incomplete in 
     * the internal taskList. After that, it replaces the task stored in the external
     * file before returning a sorted taskList.
     * 
     * @param  task a task object containing details to a user's task
     * @return an integer containing a success (0) or failure (-1) code
     */
    public int incompleteOneItem(Task task) {
        try {
            if (task.getIsCompleted() != false) {
                task.setCompleted(false);

                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);

                String input = "";
                String line;
                String replaceLine = task.getTaskType() + ";" + task.getTaskDescription() 
                + ";" + task.getStartDate() + ";" + task.getEndDate() + ";" + task.getStartTime()
                + ";" + task.getEndTime() + ";" + true + ";" + task.getIsDateTimeValid() 
                + ";" + task.getRecurringID() + ";";

                while ((line = br.readLine()) != null) {        
                    input += line + '\n';
                }

                br.close();

                FileWriter fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(input.replaceAll(replaceLine, task.getTaskType() + ";" + task.getTaskDescription() 
                + ";" + task.getStartDate() + ";" + task.getEndDate() + ";" + task.getStartTime()
                + ";" + task.getEndTime() + ";" + task.getIsCompleted() + ";" + task.getIsDateTimeValid() 
                + ";" + task.getRecurringID() + ";"));

                bw.close();

                sortTaskList(taskList);
                logger.log(Level.INFO, "Reverted completion of task {0} from external file", task.getTaskDescription());
                return SUCCESS;
            } else {
                logger.log(Level.WARNING, "Task {0} is never completed!", task.getTaskDescription());
                return FAILURE;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to incomplete task {0} from external file", task.getTaskDescription());
            return FAILURE;
        }
    }
    
    /**
     * Sorts an arraylist containing objects of Task. Reserved for use for the sorting
     * of the internal taskList.
     * <p>
     * Accepts an arrayList<Task>, duplicates it, before replacing it with a sorted 
     * internal taskList.
     * 
     * @param   tasks   accepts an ArrayList<Task> for sorting
     */
    private void sortTaskList(ArrayList<Task> tasks) {
        ArrayList<Task> sort = tasks;
        sort = Sort.sortAll();     
        wipeTaskList();
        taskList = sort;
    }
    
	/** 
     * Obtains the task type from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the task type from the task object
     */
    private String getTaskTypeByItemNum(int itemNumber) {
       String[] target = readExternalFile(itemNumber);
       return target[0];
    }
    
    /** 
     * Obtains task description from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the task description from the task object
     */
    private String getTaskDescriptionByItemNum(int itemNumber) {
       String[] target = readExternalFile(itemNumber);
       return target[1];
    }

    /** 
     * Obtains start date from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the start date from the task object
     */
    private String getStartDateByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[2];
    }
    
    /** 
     * Obtains end date from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the end date from the task object
     */
    private String getEndDateByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[3];
    }
    
    /** 
     * Obtains start time from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the start time from the task object
     */
    private String getStartTimeByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[4];
    }
    
    /** 
     * Obtains end time from task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  a string containing the end time from the task object
     */
    private String getEndTimeByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[5];
    }
    
    /** 
     * Obtains completion of task saved in external file.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  returns true if the task is complete or false if the task is incomplete
     */
    private Boolean getIsCompletedByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Boolean.parseBoolean(target[6]);
    }
    
    /** 
     * Obtains date/time validity of task saved in external file
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  returns true if the task date/time is valid or false if the task date/time is invalid
     */
    private Boolean getIsDateTimeValidByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Boolean.parseBoolean(target[7]);
    }
    
    /** 
     * Obtains recurringID of task saved in external file
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  the recur ID of the task
     */
    private int getRecurringIDByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Integer.parseInt(target[8]);
    }
    
    /**
     * Reads the external file based on its line number.
     * 
     * @param   itemNumber  represents the line number in the external file
     * @return  the entire string read off from its line including its delimiters (;)
     */
    private String[] readExternalFile(int itemNumber) {
        try {  
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);       

            int lineNumber = 0;
            String line = null;
            String[] target = new String[9];
            
            while ((line = br.readLine()) != null) {
                if (lineNumber == itemNumber) {
                    target = line.split(";");
                }
                lineNumber += 1;
            }

            br.close();
            return target;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to read line number {0} from external file", itemNumber);
            return null;
        }
    }
    
    /*
     * Deletes a task from the taskList and deletes the entry from external file
     * by an item number
     * @@author A0126058-unused 
     * Reason: due to change of requirements
    */
    /*
    public static int deleteOneItemByItemNum(int itemNumber) {
        logger.log(Level.INFO, "Deleting task {0}", itemNumber);
        taskList.remove(itemNumber-1); //(jh) update internal list
        
        try {
            File original = new File(filename);
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            File temp = new File("Alt4.tmp");
            FileWriter fw = new FileWriter("Alt4.tmp", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            int lineNumber = 0;
            String line;
            
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
            logger.log(Level.WARNING, "Unable to delete task {0}", itemNumber);
            return -1;
        }
        return 0;
    }
    */
    
    /* 
     * Updates one task to the taskList and writes to external file
     * @@author A0126058-unused
     * Reason: due to change of requirements
     */
    /*
    public int updateOneItem(int itemNumber, Task task) {
        logger.log(Level.INFO, "Updating {0} to taskList", task.getTaskDescription());
        taskList.set(itemNumber-1, task); //(jh) update internal list
        
        try {
            logger.log(Level.INFO, "Updating {0} to external file", task.getTaskDescription());
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            int lineNumber = 0;
            String input = "";
            String line;
            String replaceLine = "";
            
            while ((line = br.readLine()) != null) {        
                input += line + '\n';
                
                if (lineNumber == itemNumber) {
                    replaceLine = line + '\n';
                }
                
                lineNumber += 1;
            }
            
            br.close();
            
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(input.replaceAll(replaceLine, task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getStartDate()
            + ";" + task.getEndDate() + ";" + task.getStartTime() + ";" + task.getEndTime() + ";" + task.getIsCompleted() + ";"  + "\n"));
            
            bw.close();
            logger.log(Level.INFO, "Updated {0} to external file", task.getTaskDescription());
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to update {0}", task.getTaskDescription());
            return -1;
        }
        return 0;
    }
    */
    
	/* 
     * Displays all tasks to the taskList
     * @@author A0126058-unused 
     * Reason: due to change of requirements
     
	public void display() {
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
    
    /*
    // For testing purposes
    for(int i = 0; i<taskList.size(); i++) {
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getTaskType());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getTaskDescription());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getStartDate());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getEndDate());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getStartTime());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getEndTime());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getIsCompleted());
        logger.log(Level.INFO, "Reading task from taskList: {0}", taskList.get(i).getIsDateTimeValid());
    }
    */
}