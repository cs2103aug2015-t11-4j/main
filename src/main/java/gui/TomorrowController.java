package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * 
 * @author Yu Ju
 *
 */

public class TomorrowController {

	@FXML
    private ListView<String> tmrList;
	
	private MainApp mainApp;
	
	public TomorrowController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		tmrList.setItems(mainApp.getTmr());
	}
}
