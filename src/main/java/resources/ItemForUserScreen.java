package main.java.resources;
//@Author: Jiahuan

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
