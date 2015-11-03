package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * This class handles the ListView of events by adding them into the list that is to be displayed
 * to the user.
 * 
 * @author Yu Ju
 *
 */

public class EventController {

	@FXML
    private ListView<String> eventList;
	
	@FXML
    private Label clock;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public EventController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		eventList.setItems(mainApp.getEvent());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    clock.setText(dateFormat.format(date));
	}
}
