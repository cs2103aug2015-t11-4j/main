package main.java.gui;

/**
 * 
 * @author Yu Ju
 *
 */

public class TaskEvent {

	private String eventName;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	
	public TaskEvent(String newEventName, String newStartDate, String newEndDate, 
			String newStartTime, String newEndTime) {
		/*this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;*/
		
		/*this.eventName = "meeting";
		this.startDate = "16/10/2015";
		this.endDate = "17/10/2015";
		this.startTime = "0900";
		this.endTime = "1000";*/
		
		setEventName(newEventName);
		setStartDate(newStartDate);
		setEndDate(newEndDate);
		setStartTime(newStartTime);
		setEndTime(newEndTime);
	}
	
	public void setEventName(String newEventName) {
		//this.eventName = name;
		eventName = newEventName;
	}
	
	public void setStartDate(String newStartDate) {
		//this.startDate = startDate;
		startDate = newStartDate;
	}
	
	public void setEndDate(String newEndDate) {
		//this.endDate = endDate;
		endDate = newEndDate;
	}
	
	public void setStartTime(String newStartTime) {
		//this.startTime = startTime;
		startTime = newStartTime;
	}
	
	public void setEndTime(String newEndTime) {
		//this.endTime = endTime;
		endTime = newEndTime;
	}
	
	public String getEventName() {
		return eventName;
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
}
