package main.java.data;

import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.Task;

public class Event {

	private String eventName;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	
	private ArrayList<DataDisplay> data;
	private ArrayList<Task> task;
	
	public Event(String eventName, String startDate, String endDate, String startTime, String endTime) {
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.data = new ArrayList<DataDisplay> ();
		
		/*this.eventName = "meeting";
		this.startDate = "16/10/2015";
		this.endDate = "17/10/2015";
		this.startTime = "0900";
		this.endTime = "1000";*/
	}
	
	public void setEventName(String name) {
		this.eventName = name;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
	public ArrayList<Task> getData() {
        return task;
    }

    public void addData(Task dataDetails) {
        getData().add(dataDetails);
    }
}
