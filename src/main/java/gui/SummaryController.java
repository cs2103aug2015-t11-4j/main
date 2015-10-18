package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * 
 * @author Yu Ju
 *
 */

public class SummaryController {

	@FXML
    private ListView<String> deadlineList;
	@FXML
    private ListView<String> eventList;
	@FXML
    private ListView<String> floatingList;
	
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
