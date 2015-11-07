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
 * @@author Lim Yong Zhi
 */

public class Storage {
    private static ArrayList<Task> taskList; // a global variable for task list (jh)
    Logger logger = Logger.getLogger("Storage");
    
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
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    
    /* 
	 * Generates an alt4 file containing taskList
	 */
	private String filename = "Alt4.txt";	
	
	/* 
     * Allows user to change the destination of the taskList and write actual
     * taskList location
     * 
     * TODO: To allow duplicates of the taskList? Rely on Alt4.txt for location storage?
     */
	public int changeDirectory(String directory) {
	    try {
	        File file = new File(filename = directory + filename);

	        if(!file.exists()) {
	            file.createNewFile(); 
	        }

	        file.getParentFile().mkdirs();
	        return 0;
	    } catch (Exception e) {
	        logger.log(Level.WARNING, "Unable to create external file in {0}!", directory);
	        return -1;
	    }
	}
	
    /* 
     * Reads the external file to regenerate the taskList
     */
    public void regenerateTaskList() throws IOException {
        try {
            logger.log(Level.INFO, "Regenerating internal taskList from external file!");

            // Ensure internal taskList is emptied before regeneration 
            if(!taskList.isEmpty()) {
                wipeTaskList();
            }

            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            @SuppressWarnings("unused")
            String line;
            int count = 0;
            /*
            if(retrieveDirectory()){
                count = 1;
            }
            */
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
            
            sortTaskList(taskList);
            logger.log(Level.INFO, "Completed regeneration of internal taskList from external file");
        } catch (Exception e) {
            File file = new File(filename);

            if(!file.exists()) {
                file.createNewFile(); 
            }
            logger.log(Level.INFO, "No tasks to generate from external file. Creating {0}.", filename);
        }
    }

    /* 
     * Wipes the current taskList
     */
    private void wipeTaskList() {
        logger.log(Level.WARNING, "Wiping taskList!");
        taskList.clear();
    }
    
    /* 
     * Checks the first line external file if it contains a directory
     * and if so, retrieves it.
     * 
     * NOTE: External file saves the path of the user's directory of choice 
     * to the first line of the external file
     */
    /*
    private boolean retrieveDirectory() {
        String[] getDirectory;
        
        if(getTaskTypeByItemNum(0).equals("deadlines") 
                || getTaskTypeByItemNum(0).equals("event") 
                || getTaskTypeByItemNum(0).equals("floating")) { 
            logger.log(Level.INFO, "Retrieving directory from external file!");
            getDirectory = readExternalFile(0);
            filename = getDirectory[0] + filename;
            return true;
        }
        
        return false;
    }
    */
	
	/* 
     * Adds one task to the taskList and writes to external file
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
			
			logger.log(Level.INFO, "Completed writing {0} to external file", task.getTaskDescription());
		} catch (Exception e) {
		    logger.log(Level.WARNING, "Unable to add {0}", task.getTaskDescription());
			return -1;
		}
		
		sortTaskList(taskList);
		return 0;
	}
	
	/* 
     * Deletes a task from the taskList and delete entry from external file
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
            return 0;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to delete task {0} from external file", task.getTaskDescription());
            return -1;
        }
    }
    
    /* 
     * Set task to complete saved in external file
     */
    public int completeOneItem(Task task) {
        try {
            if(task.getIsCompleted() != true) {
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
                return 0;
            } else {
                logger.log(Level.WARNING, "Task {0} is already completed!", task.getTaskDescription());
                return -1;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to complete task {0} from external file", task.getTaskDescription());
            return -1;
        }
    }
    
    /* 
     * Set task to incomplete saved in external file
     */
    public int incompleteOneItem(Task task) {
        try {
            if(task.getIsCompleted() != false) {
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
                return 0;
            } else {
                logger.log(Level.WARNING, "Task {0} is never completed!", task.getTaskDescription());
                return -1;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to incomplete task {0} from external file", task.getTaskDescription());
            return -1;
        }
    }
    
    /*
     * Sorts the taskList
     */
    private void sortTaskList(ArrayList<Task> tasks) {
        ArrayList<Task> sort = tasks;
        sort = Sort.sortAll();     
        wipeTaskList();
        taskList = sort;
    }
    
	/* 
     * Obtains task type from task saved in external file
     */
    private String getTaskTypeByItemNum(int itemNumber) {
       String[] target = readExternalFile(itemNumber);
       return target[0];
    }
    
    /* 
     * Obtains task description from task saved in external file
     */
    private String getTaskDescriptionByItemNum(int itemNumber) {
       String[] target = readExternalFile(itemNumber);
       return target[1];
    }

    /* 
     * Obtains start date from task saved in external file
     */
    private String getStartDateByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[2];
    }
    
    /* 
     * Obtains end date from task saved in external file
     */
    private String getEndDateByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[3];
    }
    
    /* 
     * Obtains start time from task saved in external file
     */
    private String getStartTimeByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[4];
    }
    
    /* 
     * Obtains end time from task saved in external file
     */
    private String getEndTimeByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return target[5];
    }
    
    /* 
     * Obtains completion of task saved in external file
     */
    private Boolean getIsCompletedByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Boolean.parseBoolean(target[6]);
    }
    
    /* 
     * Obtains date/time validity of task saved in external file
     */
    private Boolean getIsDateTimeValidByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Boolean.parseBoolean(target[7]);
    }
    
    /* 
     * Obtains date/time validity of task saved in external file
     */
    private int getRecurringIDByItemNum(int itemNumber) {
        String[] target = readExternalFile(itemNumber);
        return Integer.parseInt(target[8]);
    }
    
    /*
     * Reads the external file based on its line number
     */
    private String[] readExternalFile(int itemNumber) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);        

            int lineNumber = 0;
            String line = null;
            String[] target = new String[9];
            
            /*
            //Checks if the first line is a user specified directory for the taskList
                if(retrieveDirectory()){
                lineNumber = 1;
            }
            */
            
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
     * @@author A0126058-unused due to change of requirements
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
     * @@author A0126058-unused due to change of requirements
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
     * @@author A0126058-unused due to change of requirements
     
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
}