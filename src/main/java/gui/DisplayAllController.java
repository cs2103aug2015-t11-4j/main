package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the display all scene which shows ListViews of both complete and incomplete
 * tasks by adding the tasks into the respective lists that are to be displayed to the user.
 *
 * @@author yuju
 * 
 * Followed tutorial from:
 * http://code.makery.ch/library/javafx-2-tutorial/
 */

public class DisplayAllController {

	@FXML
    private ListView<Text> completeList;
	@FXML
    private ListView<Text> incompleteList;
	
	@FXML
    private Label clock;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public DisplayAllController() {
        
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		completeList.setItems(mainApp.getComplete());
		incompleteList.setItems(mainApp.getIncomplete());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    clock.setText(" " + dateFormat.format(date));
	}
}
