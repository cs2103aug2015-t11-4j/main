//@author : wenbin

public class Task {
	
	private String taskType;
	private String taskDescription;
	private String date;
	private String startTime;
	private String endTime;
	
	/********************** Constructor ************************/
	//creates an empty task
	public Task() {
		
		setTaskType(null);
		setTaskDescription(null);
		setDate(null);
		setStartTime(null);
		setEndTime(null);
	}
	
	public Task (String newTaskType, String newTaskDescription, String newDate,
									String newStartTime, String newEndTime) {
			
		setTaskType(newTaskType);
		setTaskDescription(newTaskDescription);
		setDate(newDate);
		setStartTime(newStartTime);
		setEndTime(newEndTime);
	}

	/********************* Accessors *************************/
	public String getTaskType() {
		return taskType;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	/*********************** Mutators ************************/
	public void setTaskType(String newTaskType) {
		taskType = newTaskType;
	}
	
	public void setTaskDescription(String newTaskDescription) {
		taskDescription = newTaskDescription;
	}
	
	public void setDate(String newDate) {
		date = newDate;
	}
	
	public void setStartTime(String newStartTime) {
		startTime = newStartTime;
	}
	
	public void setEndTime(String newEndTime) {
		endTime = newEndTime;
	}
}
