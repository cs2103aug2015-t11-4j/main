package main.java.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.logic.Logic;

/**
 * 
 * @author Yu Ju
 *
 */

public class MainApp extends Application {
	
    private static final String WINDOW_TITLE = "ALT4";
    private static final String ROOT_LAYOUT_FXML = "/main/resources/layouts/RootLayout.fxml";
    private static final String SUMMARY_LAYOUT_FXML = "/main/resources/layouts/Summary.fxml";
    private static final String ALL_LAYOUT_FXML = "/main/resources/layouts/DisplayAll.fxml";
    private static final String FLOATING_LAYOUT_FXML = "/main/resources/layouts/Floating.fxml";
    private static final String DEADLINE_LAYOUT_FXML = "/main/resources/layouts/Deadline.fxml";
    private static final String EVENT_LAYOUT_FXML = "/main/resources/layouts/Event.fxml";
    private static final String TODAY_LAYOUT_FXML = "/main/resources/layouts/Today.fxml";
    private static final String TOMORROW_LAYOUT_FXML = "/main/resources/layouts/Tomorrow.fxml";
    
    private static final String FEEDBACK_SUMMARY = "Summary";
    private static final String FEEDBACK_DISPLAY = "All Events";
    private static final String FEEDBACK_TODAY = "Today's Tasks";
    private static final String FEEDBACK_TOMORROW = "Tomorrow's Tasks";
    private static final String FEEDBACK_DEADLINE = "Deadline Tasks";
    private static final String FEEDBACK_EVENT = "Events";
    private static final String FEEDBACK_FLOATING = "Floating Tasks";
    
    private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    private static final String FEEDBACK_ADDED = "Added: ";
    private static final String FEEDBACK_DELETED = "Deleted: ";
    private static final String FEEDBACK_UPDATED = "Updated ";
    private static final String FEEDBACK_EXIT = "Exiting Alt4";
    private static final String FEEDBACK_UNDONE = "Undone: ";
    
    private static final String TYPE_DEADLINE = "deadline";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_FLOATING = "floating";
    
    private ObservableList<String> event = FXCollections.observableArrayList();
    private ObservableList<String> deadline = FXCollections.observableArrayList();
    private ObservableList<String> floating = FXCollections.observableArrayList();
    private ObservableList<Text> complete = FXCollections.observableArrayList();
    private ObservableList<Text> incomplete = FXCollections.observableArrayList();
    private ObservableList<String> today = FXCollections.observableArrayList();
    private ObservableList<String> tomorrow = FXCollections.observableArrayList();
    
    private String[] arr;
    private String command;
    private String description;
    private String type;
    private String newDescription;
    private int listNum;
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    //private MainApp mainApp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	initRootLayout();
        initPrimaryStage(primaryStage);

        // Add components to RootLayout
        addCommandBar(this);
        addSummaryView();
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

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        //event.add(new LocalEvent("Hans"));
        //event.add(new LocalEvent("Ruth"));
    	
    	/*event.add("Hans");
        event.add("Ruth");
        event.add("Heinz");
        event.add("Cornelia");
        event.add("Werner");
        event.add("Lydia");
        event.add("Anna");
        event.add("Stefan");
        event.add("Martin");
        
        deadline.add("Hans");
        deadline.add("Ruth");
        deadline.add("Heinz");
        deadline.add("Cornelia");
        deadline.add("Werner");
        deadline.add("Lydia");
        deadline.add("Anna");
        deadline.add("Stefan");
        deadline.add("Martin");
        
        floating.add("Hans");
        floating.add("Ruth");
        floating.add("Heinz");
        floating.add("Cornelia");
        floating.add("Werner");
        floating.add("Lydia");
        floating.add("Anna");
        floating.add("Stefan");
        floating.add("Martin");*/
        
        Text text = new Text("hello");
        text.setFill(Color.GREEN);
        
        //complete.add("hello");
        //complete.add("world");
        complete.add(text);
        
        Text text2 = new Text("not yet");
        text2.setFill(Color.RED);
        
        //incomplete.add("not yet");
        //incomplete.add("undone");
        incomplete.add(text2);
        
        today.add("sunday");
        
        tomorrow.add("monday");
    }

    public ObservableList<String> getEvent() {
        return event;
    }
    
    public ObservableList<String> getDeadline() {
    	return deadline;
    }
    
    public ObservableList<String> getFloating() {
    	return floating;
    }
    
    public ObservableList<Text> getComplete() {
    	return complete;
    }
    
    public ObservableList<Text> getIncomplete() {
    	return incomplete;
    }
    
    public ObservableList<String> getToday() {
    	return today;
    }

    public ObservableList<String> getTmr() {
    	return tomorrow;
    }
    
    private void addCommandBar(MainApp mainApp) {
        rootLayout.setBottom(new CommandBarController(mainApp));
    }
    
    private void addSummaryView() {
        try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(SUMMARY_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	SummaryController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addDisplayAll() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(ALL_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	DisplayAllController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addToday() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(TODAY_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	TodayController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addTmr() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(TOMORROW_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	TomorrowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addDeadline() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(DEADLINE_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	DeadlineController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addEvent() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(EVENT_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	EventController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addFloating() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(FLOATING_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	FloatingController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleKeyPress(CommandBarController commandBarController,
            KeyCode key,
            String userInput) {
    	if (key == KeyCode.ENTER) {
    		handleEnterPress(commandBarController, userInput);
    	}
    }
    
    public void handleEnterPress(CommandBarController commandBarController, String userInput) {
    	
    	userInput = userInput.toLowerCase();
    	
    	if(userInput.equals("summary")) {
    		addSummaryView();
    		commandBarController.setFeedback(FEEDBACK_SUMMARY);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("display")) {
    		addDisplayAll();
    		commandBarController.setFeedback(FEEDBACK_DISPLAY);
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
    		addToday();
    		commandBarController.setFeedback(FEEDBACK_TODAY);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("tomorrow") || userInput.equals("tmr")) {
    		addTmr();
    		commandBarController.setFeedback(FEEDBACK_TOMORROW);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("deadline")) {
    		addDeadline();
    		commandBarController.setFeedback(FEEDBACK_DEADLINE);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("event") || userInput.equals("events")) {
    		addEvent();
    		commandBarController.setFeedback(FEEDBACK_EVENT);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("floating")) {
    		addFloating();
    		commandBarController.setFeedback(FEEDBACK_FLOATING);
    		commandBarController.clear();
    	}
    	
    	else {
    		arr = userInput.split(" ", 3);
    		command = arr[0];
    	
    		switch (command) {
            
    			case "add" :
    				type = arr[1];
    	    		description = (arr[2]).trim();
    				//Logic.takeAction(userInput);  //doesnt work yet
    				if(type.equals(TYPE_DEADLINE)) {
    					deadline.add(description);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				commandBarController.setFeedback(FEEDBACK_ADDED + description);
    				break;
                
    			case "update" :
    				description = (arr[2]).trim();
    				listNum = Integer.parseInt(arr[1]);  //get data from Logic
    				
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_UPDATED + "\"" + description +
    						"\" to " + newDescription);
    				break;
            	
    			case "delete" :  //need Logic to pass me the event with task type that is to be deleted
    				//or get task type from Task
    				description = (arr[1]).trim();
    				listNum = Integer.parseInt(arr[1]);
    				Logic.takeAction(userInput);
    				if(type.equals(TYPE_DEADLINE)) {
    					deadline.remove(listNum);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				commandBarController.setFeedback(FEEDBACK_DELETED + description);
    				break;
    				
    			case "undo" :
    				description = (arr[1]).trim();
    				Logic.takeAction(userInput);
    				commandBarController.setFeedback(FEEDBACK_UNDONE + description);
    				break;
    			
    			case "search" :
    				Logic.takeAction(userInput);
    				break;
                
    			default :
    				commandBarController.setFeedback(FEEDBACK_INVALID_COMMAND);
    				break;
    		}
    		commandBarController.clear();
    	}
    	
    }
}
