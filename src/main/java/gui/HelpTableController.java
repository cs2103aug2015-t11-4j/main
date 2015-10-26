package main.java.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

/**
 * 
 * @author Yu Ju
 *
 */

public class HelpTableController extends StackPane {

//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> helpTable;
    @FXML
    private TableColumn<TaskEvent, String> commandCol;
    @FXML
    private TableColumn<TaskEvent, String> flexiCol;
    @FXML
    private TableColumn<TaskEvent, String> formatCol;
    @FXML
    private TableColumn<TaskEvent, String> commentCol;
    @FXML
    private TableColumn<TaskEvent, String> resultCol;

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Help.fxml";

    public HelpTableController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> helpData = FXCollections.observableArrayList();
        
        helpData.add(new TaskEvent("add", "create\na\nc", "Deadline\nadd (content) by date;time\nadd (content) by date\nadd (content) by time",
                "1. Keyword \"by\" must be present\n2. If only time is specified, date will be automatically assumed as the daye the task is added.", 
                "Adds a deadline to ALT4"));
        helpData.add(new TaskEvent("Meeting2", "18/10/2015", "18/10/2015",
        		"1100", "1400"));
        helpData.add(new TaskEvent("Meeting3", "23/10/2015", "23/10/2015",
        		"1100", "1400"));
        helpData.add(new TaskEvent("Meeting4", "24/10/2015", "24/10/2015",
        		"1100", "1400"));
        helpData.add(new TaskEvent("Meeting5", "01/11/2015", "01/11/2015",
        		"1100", "1400"));

        helpTable.setItems(helpData);
        commandCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	flexiCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	formatCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	commentCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	resultCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
