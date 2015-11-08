package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * @@author: A0124524N; wenbin 
 * 
 * Followed tutorial from:
 * http://code.makery.ch/library/javafx-2-tutorial/
 */

public class SearchController {
	
	@FXML
    private ListView<Text> searchList;
	
	@FXML
    private Label clock;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public SearchController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		searchList.setItems(mainApp.getSearch());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    clock.setText(" " + dateFormat.format(date));
	}
}
