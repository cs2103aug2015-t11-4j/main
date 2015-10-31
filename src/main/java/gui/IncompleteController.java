package main.java.gui;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the ListView of incomplete tasks by adding the them into the list
 * that is to be displayed to the user.
 * 
 * @author Yu Ju
 *
 */

public class IncompleteController {
	
	@FXML
    private ListView<Text> incompleteList;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public IncompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		incompleteList.setItems(mainApp.getIncomplete());
	}
}
