package main.java.gui;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.java.data.Event;
import main.java.resources.DataDisplay;
import main.java.resources.Task;

/**
 * 
 * @author Yu Ju
 *
 */

public class TaskEvent2 {

	private Event event;
    private StringProperty eventName;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty startTime;
    private StringProperty endTime;

    public TaskEvent2(Event event) {
        this.event = event;
        this.eventName = new SimpleStringProperty(event.getEventName());
        this.startDate = new SimpleStringProperty(event.getStartDate());
        this.endDate = new SimpleStringProperty(event.getEndDate());
        this.startTime = new SimpleStringProperty(event.getStartTime());
        this.endTime = new SimpleStringProperty(event.getEndTime());
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }
    
    public StringProperty endTimeProperty() {
        return endTime;
    }
    
    public ArrayList<Task> getData() {
        return event.getData();
    }
}
