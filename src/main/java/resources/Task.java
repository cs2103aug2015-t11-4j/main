package main.java.resources;
//@author: A0124524N; wenbin 

public class Task {
	
	private String taskType;
	private String taskDescription;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private boolean isCompleted;
	private boolean validDTF;
	
	/********************** Constructor ************************/
	//creates an empty task
	public Task() {
		
		setTaskType("-");
		setTaskDescription("-");
		setStartDate("-");
		setEndDate("-");
		setStartTime("-");
		setEndTime("-");
		setCompleted(false);
		setValidDT(false);
	}
	
	public Task (String newTaskType, String newTaskDescription, String newStartDate, String newEndDate,
									String newStartTime, String newEndTime, boolean taskStatus, boolean dateTimeFormat) {
			
		setTaskType(newTaskType);
		setTaskDescription(newTaskDescription);
		setStartDate(newStartDate);
		setEndDate(newEndDate);
		setStartTime(newStartTime);
		setEndTime(newEndTime);
		setCompleted(taskStatus);
		setValidDT(dateTimeFormat);
	}

	/********************* Accessors *************************/
	public String getTaskType() {
		return taskType;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public boolean getIsCompleted() {
		return isCompleted;
	}
	
	public boolean getIsDateTimeValid() {
		return validDTF;
	}
	/*********************** Mutators ************************/
	public void setTaskType(String newTaskType) {
		taskType = newTaskType;
	}
	
	public void setTaskDescription(String newTaskDescription) {
		taskDescription = newTaskDescription;
	}
	
	public void setStartDate(String newStartDate) {
		startDate = newStartDate;
	}
	
	public void setEndDate(String newEndDate) {
		endDate = newEndDate;
	}
	
	public void setStartTime(String newStartTime) {
		startTime = newStartTime;
	}
	
	public void setEndTime(String newEndTime) {
		endTime = newEndTime;
	}
	
	public void setCompleted(boolean newTaskStatus) {
		isCompleted = newTaskStatus;
	}
	
	public void setValidDT(boolean newDTF) {
		validDTF = newDTF;
	}
	
	//Overriding equals() method
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Task) {
			Task task = (Task) obj;
			return this.getTaskType().equals(task.getTaskType()) && 
					this.getTaskDescription().equals(task.getTaskDescription()) &&
						this.getStartDate().equals(task.getStartDate()) &&
							this.getEndDate().equals(task.getEndDate()) &&
								this.getStartTime().equals(task.getStartTime()) && 
									this.getEndTime().equals(task.getEndTime()) &&
										this.getIsCompleted() == task.getIsCompleted() &&
											this.getIsDateTimeValid() == task.getIsDateTimeValid();
		}
	return result;
	}
}
