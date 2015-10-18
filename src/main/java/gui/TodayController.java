package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * 
 * @author Yu Ju
 *
 */

public class TodayController {

	@FXML
    private ListView<String> todayList;
	
	private MainApp mainApp;
	
	public TodayController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		todayList.setItems(mainApp.getToday());
	}
}
