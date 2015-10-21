package main.java.gui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Yu Ju
 *
 */

public class CommandBarController extends BorderPane {

    @FXML
    private Label feedback;

    @FXML
    private TextField commandBar;

    private static final String COMMAND_BAR_LAYOUT_FXML = "/main/resources/layouts/CommandBar.fxml";

    private MainApp mainApp;

    public CommandBarController(MainApp mainApp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(COMMAND_BAR_LAYOUT_FXML));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainApp = mainApp;
    }

    @FXML
    public void onKeyPress(KeyEvent event) {
        mainApp.handleKeyPress(this, event.getCode(), commandBar.getText());
    }

    public void clear() {
        commandBar.clear();
    }

    public void setFeedback(String feedbackText) {
        feedback.setText(feedbackText);
    }
    
    public void setText(String listviewName) {
    	/*
    	 * set text in command bar
    	 * @@author A0131300-unused as this section is used to test prompt text
    	 * 
    	commandBar.setOnKeyTyped(new EventHandler<KeyEvent>() {
    		public void handle(KeyEvent event) {
    			commandBar.setText(listviewName);
    		}
    	});*/
    	commandBar.setText(listviewName);
    }
}
