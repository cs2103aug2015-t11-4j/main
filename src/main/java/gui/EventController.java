package main.java.gui;

import javafx.fxml.FXML;

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
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public EventController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		eventList.setItems(mainApp.getEvent());
	}
}
