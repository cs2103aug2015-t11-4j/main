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

public class IncompleteController extends StackPane {
	
	//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> incompleteTable;
    @FXML
    private TableColumn<TaskEvent, String> eventNameCol;
    @FXML
    private TableColumn<TaskEvent, String> startTimeCol;
    @FXML
    private TableColumn<TaskEvent, String> endTimeCol;
    @FXML
    private TableColumn<TaskEvent, String> startDateCol;
    @FXML
    private TableColumn<TaskEvent, String> endDateCol;

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Incomplete.fxml";

    public IncompleteController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> incompleteData = FXCollections.observableArrayList();
        
        incompleteData.add(new TaskEvent("Dinner1", "16/12/2015", "16/12/2015",
        		"1700", "1900"));
        incompleteData.add(new TaskEvent("Dinner12", "18/12/2015", "18/12/2015",
        		"1700", "1900"));
        incompleteData.add(new TaskEvent("Dinner13", "23/12/2015", "23/12/2015",
        		"1700", "1900"));
        incompleteData.add(new TaskEvent("Dinner14", "24/12/2015", "24/12/2015",
        		"1700", "1900"));
        incompleteData.add(new TaskEvent("Dinner15", "01/11/2015", "01/12/2015",
        		"1700", "1900"));

        incompleteTable.setItems(incompleteData);
        eventNameCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	startDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	endDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	startTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	endTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
