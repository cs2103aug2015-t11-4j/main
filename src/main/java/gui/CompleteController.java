package main.java.gui;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the ListView of complete tasks by adding them into the list that is
 * to be displayed to the user.
 * 
 * @author Yu Ju
 *
 */

public class CompleteController {

	@FXML
    private ListView<Text> completeList;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public CompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		completeList.setItems(mainApp.getComplete());
	}
}
