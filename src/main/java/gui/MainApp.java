package main.java.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.logic.Logic;

/**
 * MainApp is the entry point for JavaFX applications.
 * 
 * @author Yu Ju
 *
 */
public class MainApp extends Application {

    private static final String ROOT_LAYOUT_FXML = "/main/resources/layouts/RootLayouts.fxml";
    private static final String WINDOW_TITLE = "ALT4";
    
    //private static final String FEEDBACK_EMPTY = "";
    private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    private static final String FEEDBACK_ADDED = "Added: ";
    private static final String FEEDBACK_DELETED = "Deleted: ";
    private static final String FEEDBACK_UPDATED = "Updated: ";
    private static final String FEEDBACK_SUMMARY = "Summary";
    private static final String FEEDBACK_EXIT = "Exiting Alt4";
    private static final String FEEDBACK_UNDONE = "Undone: ";

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	initRootLayout();
        initPrimaryStage(primaryStage);

        // Add components to RootLayout
        addCommandBar(this);
        addDeadline(this);
        addEvent(this);
        addFloating(this);
    }
    
    private void initRootLayout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_LAYOUT_FXML));
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the main JavaFX Stage with RootLayout being the main Scene.
     * 
     * @param primaryStage
     */
    private void initPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(WINDOW_TITLE);
        this.primaryStage.setScene(new Scene(rootLayout));
        this.primaryStage.show();
    }

    private void addCommandBar(MainApp mainApp) {
        rootLayout.setBottom(new CommandBarController(mainApp));
    }

    private void addDeadline(MainApp mainApp) {
    	rootLayout.setTop(new DeadlineController(mainApp));
    }
    
    private void addEvent(MainApp mainApp) {
    	rootLayout.setCenter(new EventController(mainApp));
    	//rootLayout.setLeft(new EventController(mainApp));
    }
    
    private void addFloating(MainApp mainApp) {
    	//rootLayout.setBottom(new EventController(mainApp));
    	rootLayout.setRight(new FloatingController(mainApp));
    }
    
    private void addComplete(MainApp mainApp) {
    	rootLayout.setTop(new CompleteController(mainApp));
    }
    
    private void addIncomplete(MainApp mainApp) {
    	rootLayout.setCenter(new IncompleteController(mainApp));
    }
    
    private void addToday(MainApp mainApp) {
    	rootLayout.setTop(new TodayController(mainApp));
    }
    
    private void addTmr(MainApp mainApp) {
    	rootLayout.setTop(new TmrController(mainApp));
    }

    public void handleKeyPress(CommandBarController commandBarController,
                               KeyCode key,
                               String userInput) {
        if (key == KeyCode.ENTER) {
            handleEnterPress(commandBarController, userInput);
        }
    }

    private void handleEnterPress(CommandBarController commandBarController,
                                  String userInput) {
    	
    	userInput.toLowerCase();
    	
    	if(userInput.equals("display")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addComplete(this);
    		addIncomplete(this);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("summary")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addDeadline(this);
    		addEvent(this);
    		addFloating(this);
    		commandBarController.setFeedback(FEEDBACK_SUMMARY);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("help")) {
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("exit") || userInput.equals("quit")) {
    		commandBarController.setFeedback(FEEDBACK_EXIT);
    		primaryStage.hide();
    	}
    	
    	else if(userInput.equals("today")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addToday(this);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("tomorrow") || userInput.equals("tmr")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addTmr(this);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("deadline")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addDeadline(this);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("event") || userInput.equals("events")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addEvent(this);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("floating")) {
    		initRootLayout();
            initPrimaryStage(primaryStage);
            addCommandBar(this);
    		addFloating(this);
    		commandBarController.clear();
    	}
    	
    	else {
    		String[] arr = userInput.split(" ", 2);
    		String command = arr[0];
    		String description = (arr[1]).trim();
    	
    		switch (command) {
            
    			case "add" :
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_ADDED + description);
    				break;
                
    			case "update" :
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_UPDATED + description);
    				break;
            	
    			case "delete" :
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_DELETED + description);
    				break;
    				
    			case "undo" :
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_UNDONE + description);
    				break;
    			
    			case "search" :
    				Logic.takeAction(userInput);
    				addDeadline(this);
    				addEvent(this);
    				addFloating(this);
    				break;
                
    			default :
    				commandBarController.setFeedback(FEEDBACK_INVALID_COMMAND);
    				break;
    		}
    		commandBarController.clear();
    	}
    }
    
}