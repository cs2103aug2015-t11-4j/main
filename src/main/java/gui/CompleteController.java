package main.java.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * This class handles the ListView of complete tasks by adding them into the list that is
 * to be displayed to the user.
 *
 * @@author yuju
 */

public class CompleteController {

	@FXML
    private ListView<Text> completeList;
	
	@FXML
    private Label clock;

	@SuppressWarnings("unused")
    private MainApp mainApp;
	
	public CompleteController() {
	
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		completeList.setItems(mainApp.getComplete());
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //get current date time with Date()
	    Date date = new Date();
	    //System.out.println(dateFormat.format(date));
	 
	    //get current date time with Calendar()
	    //Calendar cal = Calendar.getInstance();
	    //System.out.println(dateFormat.format(cal.getTime()));

	    clock.setText(" " + dateFormat.format(date));
	}

}
