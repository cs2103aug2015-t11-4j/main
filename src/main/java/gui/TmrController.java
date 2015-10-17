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

public class TmrController extends StackPane {

	//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> tmrTable;
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

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Tomorrow.fxml";

    public TmrController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> tmrData = FXCollections.observableArrayList();
        
        tmrData.add(new TaskEvent("Meeting1", "17/10/2015", "17/10/2015",
                "1100", "1400"));
        tmrData.add(new TaskEvent("Meeting2", "17/10/2015", "17/10/2015",
        		"1100", "1400"));
        tmrData.add(new TaskEvent("Meeting3", "17/10/2015", "17/10/2015",
        		"1100", "1400"));
        tmrData.add(new TaskEvent("Meeting4", "17/10/2015", "17/10/2015",
        		"1100", "1400"));
        tmrData.add(new TaskEvent("Meeting5", "17/10/2015", "17/10/2015",
        		"1100", "1400"));

        tmrTable.setItems(tmrData);
        eventNameCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	startDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	endDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	startTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	endTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
