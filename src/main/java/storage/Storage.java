package main.java.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import main.java.resources.Task;

/**
 * This class handles the Storage component and executes the 
 * appropriate action requested by the Logic component
 * 
 * @@author Lim Yong Zhi
 */


public class Storage {
	/* 
	 * Temporary placeholder for creation of file - To be replaced with user's
	 * directory of choice
	 */
	public static ArrayList<Task> taskList =new ArrayList<Task>();// a global variable for task list (jh)
	private static String filename = "MyCalender.txt";
	
	/* 
     * Adds one task to the agenda
     */
	public static int addOneItem(Task task) {
		taskList.add(task); //(jh) update internal list
		try {
			FileWriter fw = new FileWriter(filename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getDate()
					+ ";" + task.getStartTime() + ";" + task.getEndTime() + ";");
			
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	/* 
     * Updates one task to the agenda
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
            
            bw.write(input.replaceAll(replaceLine, task.getTaskType() + ";" + task.getTaskDescription() + ";" + task.getDate()
            + ";" + task.getStartTime() + ";" + task.getEndTime() + ";" + "\n"));
            
            bw.close();
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}
	
	public static int deleteOneItem(int itemNumber) {
		taskList.remove(itemNumber); //(jh) update internal list
		try {
		    File original = new File(filename);
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            File temp = new File("Alt4.tmp");
            FileWriter fw = new FileWriter("Alt4.tmp", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            int lineNumber = 0;
            String line = "";
            String deletedLine = "";
            
            while ((line = br.readLine()) != null) {
                lineNumber += 1;
                
                if (lineNumber != itemNumber) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    deletedLine = line;
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

/*	/* 
     * Displays all tasks to the agenda
     
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
	
	/* 
     * Obtains task type from task saved in Storage
     */
	public static String getTaskTypeByItemNum(int itemNumber) {
	    try {
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
            return target[0];
	    } catch (Exception e) {
            return null;
        }
	}

}