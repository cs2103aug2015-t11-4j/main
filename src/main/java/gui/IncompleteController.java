//@author: yuju 
package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the ListView of incomplete tasks by adding the them into the list
 * that is to be displayed to the user.
 *
 */

public class IncompleteController {
	
	@FXML
    private ListView<Text> incompleteList;
	
	@FXML
    private Label clock;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public IncompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		incompleteList.setItems(mainApp.getIncomplete());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    clock.setText(" " + dateFormat.format(date));
	}
}
