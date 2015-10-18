package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * 
 * @author Yu Ju
 *
 */

public class FloatingController {

	@FXML
    private ListView<String> floatingList;
	
	private MainApp mainApp;
	
	public FloatingController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		floatingList.setItems(mainApp.getFloating());
	}
}
