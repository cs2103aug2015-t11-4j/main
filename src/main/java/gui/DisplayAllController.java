package main.java.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * 
 * @author Yu Ju
 *
 */

public class DisplayAllController {

	@FXML
    private ListView<Text> completeList;
	@FXML
    private ListView<Text> incompleteList;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public DisplayAllController() {
        
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		completeList.setItems(mainApp.getComplete());
		incompleteList.setItems(mainApp.getIncomplete());
	}
}
