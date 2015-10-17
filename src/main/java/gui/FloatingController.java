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

public class FloatingController extends StackPane {

	//private MainApp mainApp;
    
    @FXML
    private TableView<TaskEvent> floatingTable;
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

    private static final String OVERVIEW_LAYOUT_FXML = "/main/resources/layouts/Floating.fxml";

    public FloatingController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(OVERVIEW_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<TaskEvent> floatingData = FXCollections.observableArrayList();
        
        floatingData.add(new TaskEvent("Lunch with MM", "-", "-", "-", "-"));
        floatingData.add(new TaskEvent("Dinner at YCK", "-", "-", "-", "-"));
        floatingData.add(new TaskEvent("Recreational", "-", "-", "-", "-"));
        floatingData.add(new TaskEvent("Shopping", "-", "-", "-", "-"));
        floatingData.add(new TaskEvent("Break", "-", "-", "-", "-"));

        floatingTable.setItems(floatingData);
        eventNameCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("eventName"));
    	startDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startDate"));
    	endDateCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endDate"));
    	startTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("startTime"));
    	endTimeCol.setCellValueFactory(new PropertyValueFactory<TaskEvent, String>("endTime"));
    	
    	//this.mainApp = mainApp;
    }
}
