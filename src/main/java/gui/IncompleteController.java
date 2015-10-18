package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * 
 * @author Yu Ju
 *
 */

public class IncompleteController {
	
	@FXML
    private ListView<Text> incompleteList;

	private MainApp mainApp;
	
	public IncompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		incompleteList.setItems(mainApp.getIncomplete());
	}
}
