package main.java.gui;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;

/**
 * This class handles the ListView of floating tasks by adding the them into the list
 * that is to be displayed to the user.
 * 
 * @author Yu Ju
 *
 */

public class FloatingController {

	@FXML
    private ListView<String> floatingList;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public FloatingController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		floatingList.setItems(mainApp.getFloating());
	}
}
