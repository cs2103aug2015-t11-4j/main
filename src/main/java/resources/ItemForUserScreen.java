/*
 * @@author A0104278 
 */

package main.java.resources;

/*
 * This class is for create a list for the user screen display
 * So the screen information can be refreshed with a standard format, which is the one in this class
 * By using information on the tasks from the tasklist to be displayed
 */
public class ItemForUserScreen {
	private boolean ifComplete;
	private String taskType;
	private String printOnScreenMsg;

	public ItemForUserScreen(boolean ifComplete, String taskType, String printOnScreenMsg) {
		this.ifComplete = ifComplete;
		this.taskType = taskType;
		this.printOnScreenMsg = printOnScreenMsg;
	}

	// Accessor
	public boolean getIfComplete() {
		return ifComplete;
	}

	public String getTaskType() {
		return taskType;
	}
	
	public String getPrintOnScreenMsg(){
		return printOnScreenMsg;
	}
	
}
