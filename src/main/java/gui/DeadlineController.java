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

public class DeadlineController extends StackPane {

	//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> deadlineTable;
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

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Deadline.fxml";

    public DeadlineController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> deadlineData = FXCollections.observableArrayList();
        /*for (Event event : inputSummaryData) {
            summaryData.add(new AuthorBean(author));
        }*/
        
        deadlineData.add(new TaskEvent("Meeting with Bob", "16/10/2015", "16/10/2015",
                "1130", "1400"));
        deadlineData.add(new TaskEvent("Dinner at Orchard with Mom", "18/10/2015", "18/10/2015",
                "1700", "-"));
        deadlineData.add(new TaskEvent("Training", "23/10/2015", "23/10/2015",
                "1300", "1400"));
        deadlineData.add(new TaskEvent("Lunch with business partners", "24/10/2015", "24/10/2015",
                "1100", "1400"));
        deadlineData.add(new TaskEvent("Holiday", "01/11/2015", "15/11/2015",
                "-", "-"));

        deadlineTable.setItems(deadlineData);
        eventNameCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	startDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	endDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	startTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	endTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
