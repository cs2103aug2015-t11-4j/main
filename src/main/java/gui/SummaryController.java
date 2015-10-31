package main.java.gui;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;

/**
 * This class handles the summary scenes that include today and tomorrow's tasks.
 * The three ListViews are the lists that would be shown in a summary view.
 * This class adds tasks into the lists that are to be displayed to the user.
 * 
 * @author Yu Ju
 */

public class SummaryController {

	@FXML
    private ListView<String> deadlineList;
	@FXML
    private ListView<String> eventList;
	@FXML
    private ListView<String> floatingList;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public SummaryController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		eventList.setItems(mainApp.getEvent());
		deadlineList.setItems(mainApp.getDeadline());
		floatingList.setItems(mainApp.getFloating());
	}
}
