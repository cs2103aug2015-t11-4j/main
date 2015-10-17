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

public class TodayController extends StackPane {

	//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> todayTable;
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

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Today.fxml";

    public TodayController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> todayData = FXCollections.observableArrayList();
        
        todayData.add(new TaskEvent("Meeting1", "16/10/2015", "16/10/2015",
                "1100", "1400"));
        todayData.add(new TaskEvent("Meeting2", "16/10/2015", "16/10/2015",
        		"1100", "1400"));
        todayData.add(new TaskEvent("Meeting3", "16/10/2015", "16/10/2015",
        		"1100", "1400"));
        todayData.add(new TaskEvent("Meeting4", "16/10/2015", "16/10/2015",
        		"1100", "1400"));
        todayData.add(new TaskEvent("Meeting5", "16/10/2015", "16/10/2015",
        		"1100", "1400"));

        todayTable.setItems(todayData);
        eventNameCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	startDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	endDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	startTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	endTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
