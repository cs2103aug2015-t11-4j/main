package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * 
 * @author Yu Ju
 *
 */

public class CompleteController {

	@FXML
    private ListView<Text> completeList;

	private MainApp mainApp;
	
	public CompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		completeList.setItems(mainApp.getComplete());
	}
}