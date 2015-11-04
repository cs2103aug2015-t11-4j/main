package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the summary scenes that include today and tomorrow's tasks.
 * The three ListViews are the lists that would be shown in a summary view.
 * This class adds tasks into the lists that are to be displayed to the user.
 * 
 * @author Yu Ju
 */

public class SummaryController {

	@FXML
    private ListView<Text> deadlineList;
	@FXML
    private ListView<Text> eventList;
	@FXML
    private ListView<Text> floatingList;
	
	@FXML
    private Label clock;
	
	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public SummaryController() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		eventList.setItems(mainApp.getEvent());
		deadlineList.setItems(mainApp.getDeadline());
		floatingList.setItems(mainApp.getFloating());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    clock.setText(dateFormat.format(date));
	}
}
