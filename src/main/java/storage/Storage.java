package main.java.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.resources.Task;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */


public class Storage {
    private static ArrayList<Task> taskList; // a global variable for task list (jh)
    static Logger logger = Logger.getLogger("Storage");
    
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
     * Allows user to change the destination of the taskList
     */
	public static void changeDirectory(String directory) {
	    filename = directory + filename;
	}
	
	/*
	 * Retrieves the external file to regenerate the taskList
	 * NOTE: External file saves the path of the user's directory of choice 
	 * to the first line of the external file
	 * 
	 * TODO: To allow retrieval of user's directory of choice once set
     
	private static void retrieveDirectory(String directory) throws FileNotFoundException, IOException {
	    String[] getDirectory = readExternalFile(0);
	    changeDirectory(getDirectory[0]);
	}
	*/
	
	/* 
     * Adds one task to the taskList and writes to external file
     */
	public static int addOneItem(Task task) {
	    logger.log(Level.INFO, "Adding {0} to taskList", task.getTaskDescription());
		taskList.add(task); //(jh) update internal list
		
		try {
		    logger.log(Level.INFO, "Writing {0} to external file", task.getTaskDescription());
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getStartDate()
					+ ";" + task.getEndDate() + ";" + task.getStartTime() + ";" + task.getEndTime() + ";"
					+ task.getIsCompleted() + ";" );
			
			bw.newLine();
			bw.close();
			logger.log(Level.INFO, "Completed writing {0} to external file", task.getTaskDescription());
		} catch (Exception e) {
		    logger.log(Level.WARNING, "Unable to add {0}", task.getTaskDescription());
			return -1;
		}
		return 0;
	}
	
	/* 
     * Updates one task to the taskList and writes to external file
     */
	public static int updateOneItem(int itemNumber, Task task) {
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
            logger.log(Level.INFO, "Updated {0} to external file", task.getTaskDescription());
		} catch (Exception e) {
		    logger.log(Level.WARNING, "Unable to update {0}", task.getTaskDescription());
			return -1;
		}
		return 0;
	}
	
	/* 
     * Deletes a task from the taskList and delete entry from external file
     */
	public static int deleteOneItem(int itemNumber) {
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
		    logger.log(Level.WARNING, "Unable to delete task {0}", itemNumber);
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