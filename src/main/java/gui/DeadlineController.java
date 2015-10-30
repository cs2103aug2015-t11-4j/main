package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * 
 * @author Yu Ju
 *
 */

public class DeadlineController {

	@FXML
    private ListView<String> deadlineList;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public DeadlineController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		deadlineList.setItems(mainApp.getDeadline());
	}
}
