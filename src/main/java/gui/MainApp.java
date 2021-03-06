/**
 * @@author A0131300
 * 
 * Followed tutorial from:
 * http://code.makery.ch/library/javafx-2-tutorial/
 * 
 * Followed Collate
 */

package main.java.gui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.PauseTransition;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;

public class MainApp extends Application {
	
    private static final String WINDOW_TITLE = "ALT4";
    private static final String ROOT_LAYOUT_FXML = "/main/resources/layouts/RootLayout.fxml";
    private static final String ROOT_LAYOUT2_FXML = "/main/resources/layouts/RootLayout2.fxml";
    private static final String TODAY_SUMMARY_LAYOUT_FXML = "/main/resources/layouts/TodaySummary.fxml";
    private static final String TOMORROW_SUMMARY_LAYOUT_FXML = "/main/resources/layouts/TomorrowSummary.fxml";
    private static final String ALL_LAYOUT_FXML = "/main/resources/layouts/DisplayAll.fxml";
    private static final String FLOATING_LAYOUT_FXML = "/main/resources/layouts/Floating.fxml";
    private static final String DEADLINE_LAYOUT_FXML = "/main/resources/layouts/Deadline.fxml";
    private static final String EVENT_LAYOUT_FXML = "/main/resources/layouts/Event.fxml";
    private static final String COMPLETE_LAYOUT_FXML = "/main/resources/layouts/Complete.fxml";
    private static final String INCOMPLETE_LAYOUT_FXML = "/main/resources/layouts/Incomplete.fxml";
    private static final String HELP_LAYOUT_FXML = "/main/resources/layouts/HelpTable.fxml";
    private static final String SEARCH_LAYOUT_FXML = "/main/resources/layouts/Search.fxml";
    
    private static final String TODAY_SCENE = "today";
    private static final String TOMORROW_SCENE = "tomorrow";
    private static final String FLOATING_SCENE = "floating";
    private static final String EVENT_SCENE = "event";
    private static final String DEADLINE_SCENE = "deadline";
    private static final String COMPLETE_SCENE = "complete";
    private static final String INCOMPLETE_SCENE = "incomplete";
    private static final String DISPLAY_ALL_SCENE = "all";
    private static final String HELP_SCENE = "help";
    private static final String EXIT_SCENE = "exit";
    private static final String SEARCH_SCENE = "search";

    private static final String FEEDBACK_EXIT = "Exiting Alt4";
    
    private static final String TYPE_DEADLINE = "deadline";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_FLOATING = "floating";
    
    //for switching scenes
    private static final String DISPLAY_ALL = "display all";
    private static final String DISPLAY_TODAY = "display today";
    private static final String DISPLAY_TMR = "display tomorrow";
    
    //For logging
    private static final String FEEDBACK_TODAY_SUMMARY = "Today's Summary";
    private static final String FEEDBACK_TMR_SUMMARY = "Tomorrow's Summary";
    private static final String FEEDBACK_DISPLAY = "All Events";
    private static final String FEEDBACK_COMPLETE = "Completed Events";
    private static final String FEEDBACK_INCOMPLETE = "Incomplete Events";
    private static final String FEEDBACK_DEADLINE = "Deadline Tasks";
    private static final String FEEDBACK_EVENT = "Events";
    private static final String FEEDBACK_FLOATING = "Floating Tasks";
    private static final String FEEDBACK_HELP = "Help Table";
    private static final String FEEDBACK_SEARCH = "Search Results";
    
    private ObservableList<Text> event = FXCollections.observableArrayList();
    private ObservableList<Text> deadline = FXCollections.observableArrayList();
    private ObservableList<Text> floating = FXCollections.observableArrayList();
    private ObservableList<Text> complete = FXCollections.observableArrayList();
    private ObservableList<Text> incomplete = FXCollections.observableArrayList();
    private ObservableList<Text> search = FXCollections.observableArrayList();

    private PauseTransition delay;
    private int pressCount = 1;
	
	private Stage primaryStage;
	private Stage secondaryStage;
    private BorderPane rootLayout;
    private BorderPane rootLayout2;

    private static ArrayList<ItemForUserScreen> itemList;
    
    private KeyCombination switchDisplay;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	initRootLayout();
        initPrimaryStage(primaryStage);
        
        OutputToUI outputToUI = new OutputToUI();
        
        try {
			outputToUI = Controller.initializeProgram();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        itemList = outputToUI.getItemList();

        // Add components to RootLayout
        addCommandBar(this);
        callToday();
    }

	private void initRootLayout() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_LAYOUT_FXML));
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void initHelpRootLayout() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_LAYOUT2_FXML));
            rootLayout2 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(WINDOW_TITLE);
        Scene scene = new Scene(rootLayout);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();	
        
        //switch screens when Ctrl+S are pressed
        switchDisplay = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
              if (key.getCode() == KeyCode.ESCAPE) {  //close window
            	  primaryStage.close();
              } else if (key.getCode() == KeyCode.F5) {  //minimise the window
            	  primaryStage.setIconified(true);
              } else if (switchDisplay.match(key) && (pressCount == 1)) {
            	  processItemList(DISPLAY_TODAY);
            	  callToday();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 2)) {
            	  processItemList(DISPLAY_TMR);
            	  callTomorrow();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 3)) {
            	  processItemList(DISPLAY_ALL);
            	  callDisplayAll();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 4)) {
            	  callComplete();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 5)) {
            	  callIncomplete();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 6)) {
            	  callDeadline();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 7)) {
            	  callEvent();
            	  processPressCount();
              } else if (switchDisplay.match(key) && (pressCount == 8)) {
            	  callFloating();
            	  processPressCount();
              }
            }

			private void processItemList(String loadScene) {
				Command command = Controller.createCommand(loadScene);
            	  OutputToUI outputToUI = new OutputToUI();
            	  outputToUI = command.execute();
            	  itemList = outputToUI.getItemList();
			}

			private void processPressCount() {
				pressCount++;
            	  if(isLimit()) {
            		  resetPressCount();
            	  }
			}
			
        });
    }
    
    private boolean isLimit() {
    	return (pressCount == 9);
    }
    
    private void resetPressCount() {
    	pressCount = 1;
    }
    
    private void initSecondaryStage(Stage secondaryStage) {
    	this.secondaryStage = secondaryStage;
        Scene scene = new Scene(rootLayout2);
        scene.setFill(Color.TRANSPARENT);
        rootLayout2.setBackground(Background.EMPTY);
        this.secondaryStage.initStyle(StageStyle.TRANSPARENT);
        this.secondaryStage.setScene(scene);
        this.secondaryStage.show();
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
            	if ((key.getCode() == KeyCode.ESCAPE) || (key.getCode() == KeyCode.SPACE) ||
            	   (isLetterCode(key)) || (isDigitCode(key)) || (isFunctionCode(key))) {
            		secondaryStage.close();
            	}
            }
        });
    }
    
    public boolean isLetterCode(KeyEvent key) {
    	return ((key.getCode() == KeyCode.A) || (key.getCode() == KeyCode.B) || 
               (key.getCode() == KeyCode.C) || (key.getCode() == KeyCode.D) || 
               (key.getCode() == KeyCode.E) || (key.getCode() == KeyCode.F) ||
               (key.getCode() == KeyCode.G) || (key.getCode() == KeyCode.H) || 
               (key.getCode() == KeyCode.I) || (key.getCode() == KeyCode.J) || 
               (key.getCode() == KeyCode.K) || (key.getCode() == KeyCode.L) || 
               (key.getCode() == KeyCode.M) || (key.getCode() == KeyCode.N) || 
               (key.getCode() == KeyCode.O) || (key.getCode() == KeyCode.P) || 
               (key.getCode() == KeyCode.Q) || (key.getCode() == KeyCode.R) || 
               (key.getCode() == KeyCode.S) || (key.getCode() == KeyCode.T) || 
               (key.getCode() == KeyCode.U) || (key.getCode() == KeyCode.V) || 
               (key.getCode() == KeyCode.W) || (key.getCode() == KeyCode.X) || 
               (key.getCode() == KeyCode.Y) || (key.getCode() == KeyCode.Z));
    }
    
    public boolean isDigitCode(KeyEvent key) {
    	return ((key.getCode() == KeyCode.DIGIT0) || (key.getCode() == KeyCode.DIGIT1) || 
           	   (key.getCode() == KeyCode.DIGIT2) || (key.getCode() == KeyCode.DIGIT3) || 
           	   (key.getCode() == KeyCode.DIGIT4) || (key.getCode() == KeyCode.DIGIT5) ||
           	   (key.getCode() == KeyCode.DIGIT6) || (key.getCode() == KeyCode.DIGIT7) || 
           	   (key.getCode() == KeyCode.DIGIT8) || (key.getCode() == KeyCode.DIGIT9));
    }
    
    public boolean isFunctionCode(KeyEvent key) {
    	return ((key.getCode() == KeyCode.F1) || (key.getCode() == KeyCode.F2) || 
               (key.getCode() == KeyCode.F3) || (key.getCode() == KeyCode.F4) || 
               (key.getCode() == KeyCode.F5) || (key.getCode() == KeyCode.F6) ||
               (key.getCode() == KeyCode.F7) || (key.getCode() == KeyCode.F8) || 
               (key.getCode() == KeyCode.F9) || (key.getCode() == KeyCode.F10) ||
               (key.getCode() == KeyCode.F11) || (key.getCode() == KeyCode.F12));
    }

    /**
     * Constructor
     */
    public MainApp() {
    	
    }

    public ObservableList<Text> getEvent() {
    	return event;
    }
    
    public ObservableList<Text> getDeadline() {
    	return deadline;
    }
    
    public ObservableList<Text> getFloating() {

    	return floating;
    }
    
    public ObservableList<Text> getComplete() {
    	return complete;
    }
    
    public ObservableList<Text> getIncomplete() {
    	return incomplete;
    }
    
	//@@author A0124524
    public ObservableList<Text> getSearch() {
    	return search;
    }
    
    /**
     * @@author A0131300
     */
    private void addCommandBar(MainApp mainApp) {
        rootLayout.setBottom(new CommandBarController(mainApp));
    }
    
    private void addHelpTable() {
        initHelpRootLayout();
        secondaryStage = new Stage();
        initSecondaryStage(secondaryStage);
    	
    	try {
        	FXMLLoader loader2 = new FXMLLoader(MainApp.class.getResource(HELP_LAYOUT_FXML));
        	StackPane page = (StackPane) loader2.load();
        	rootLayout2.setCenter(page);
        	
        	HelpTableController controller = loader2.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
        	System.out.println("Help Table");
        }
    }
    
    private void addTodaySummaryView() {
        try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(TODAY_SUMMARY_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	SummaryController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addTomorrowSummaryView() {
        try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(TOMORROW_SUMMARY_LAYOUT_FXML));
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
    
    private void addComplete() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(COMPLETE_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	CompleteController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void addIncomplete() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(INCOMPLETE_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	IncompleteController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	//@@author: A0124524
    private void addSearch() {
    	try {
        	FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(SEARCH_LAYOUT_FXML));
        	AnchorPane page = (AnchorPane) loader.load();
        	rootLayout.setTop(page);
        	
        	SearchController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @@author A0131300
     */ 
    public void callToday() {
    	createDeadlineList(itemList);
		createEventList(itemList);
		createFloatingList(itemList);
		addTodaySummaryView();
    }
    
    public void callTomorrow() {
    	createDeadlineList(itemList);
		createEventList(itemList);
		createFloatingList(itemList);
		addTomorrowSummaryView();
    }
    
    public void callFloating() {
    	createFloatingList(itemList);
		addFloating();
    }
    
    public void callEvent() {
    	createEventList(itemList);
		addEvent();
    }
    
    public void callDeadline() {
    	createDeadlineList(itemList);
		addDeadline();
    }
    
    public void callComplete() {
    	createCompleteList(itemList);
		addComplete();
    }
    
    public void callIncomplete() {
    	createIncompleteList(itemList);
		addIncomplete();
    }
    
    public void callSearch() {
    	createSearchList(itemList);
		addSearch();
    }
    
    public void callDisplayAll() {
    	createCompleteList(itemList);
		createIncompleteList(itemList);
		addDisplayAll();
    }
    
    public void callHelp() {
		addHelpTable();
    }
    
    public void handleKeyPress(CommandBarController commandBarController,
            				   KeyCode key,
            				   String userInput) {
    	if (key == KeyCode.ENTER) {
    		handleEnterPress(commandBarController, userInput);
    	}
    }
    
    public void handleEnterPress(CommandBarController commandBarController, String _userInput ) {
    	
    		_userInput = _userInput.toLowerCase();
    		Command command = Controller.createCommand(_userInput);
    		OutputToUI outputToUI = new OutputToUI();
    		outputToUI = command.execute();
    		commandBarController.clear();

    		String userInput = outputToUI.getTypeOfScreen();
    		itemList = outputToUI.getItemList();
    		String feedbackMsg = outputToUI.getFeedbackMsg();
    		String taskToUpdate = outputToUI.getInputBoxMsg();

    		commandBarController.setFeedback(feedbackMsg);

    		if (userInput != null) {
    			if (userInput.equals(TODAY_SCENE)) {
    				callToday();
    				Logger.log(FEEDBACK_TODAY_SUMMARY);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(TOMORROW_SCENE)) {
    				callTomorrow();
    				Logger.log(FEEDBACK_TMR_SUMMARY);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(FLOATING_SCENE)) {
    				callFloating();
    				Logger.log(FEEDBACK_FLOATING);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(EVENT_SCENE)) {
    				callEvent();
    				Logger.log(FEEDBACK_EVENT);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(DEADLINE_SCENE)) {
    				callDeadline();
    				Logger.log(FEEDBACK_DEADLINE);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(COMPLETE_SCENE)) {
    				callComplete();
    				Logger.log(FEEDBACK_COMPLETE);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(INCOMPLETE_SCENE)) {
    				callIncomplete();
    				Logger.log(FEEDBACK_INCOMPLETE);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(SEARCH_SCENE)) {
    				callSearch();
    				Logger.log(FEEDBACK_SEARCH);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(DISPLAY_ALL_SCENE)) {
    				callDisplayAll();
    				Logger.log(FEEDBACK_DISPLAY);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(HELP_SCENE)) {
    				callHelp();
    				Logger.log(FEEDBACK_HELP);
    				handleCommandBar(commandBarController, feedbackMsg);
    			} else if (userInput.equals(EXIT_SCENE)) {
    				handleCommandBar(commandBarController, FEEDBACK_EXIT);
    				//delay closing of GUI window by 1s
    				delay = new PauseTransition(Duration.seconds(1));
    				delay.setOnFinished(new EventHandler<ActionEvent> () {
    					@Override
    					public void handle(ActionEvent event) {
    						primaryStage.hide();
    					}
    				});
    				delay.play();
    			}
    		}

    		if (taskToUpdate != null) {
    			commandBarController.setText(taskToUpdate);
    			commandBarController.setFeedback(feedbackMsg);
    		}
    }

	private void handleCommandBar(CommandBarController commandBarController, String feedbackMsg) {
		commandBarController.setFeedback(feedbackMsg);
		commandBarController.clear();
	}
    
    /**
     * Return list of tasks to be added into the respective listviews
     * 
     * @@author A0104278
     * 
     * @param itemList list of tasks from storage
     * @return list of tasks
     */
    private ObservableList<Text> createEventList(ArrayList<ItemForUserScreen> itemList) {
		event.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_EVENT)){
				/**
			     * @@author A0131300
			     */ 
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				event.add(text);
				/** 
			     * @@author A0104278
			     */
			}
		}
    	return event;
    }
    
    private ObservableList<Text> createDeadlineList(ArrayList<ItemForUserScreen> itemList) {
    	deadline.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_DEADLINE)){
				/**
			     * @@author A0131300
			     */ 
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				deadline.add(text);
				/** 
			     * @@author A0104278
			     */
			}
		}
    	return deadline;
    }
    
    private ObservableList<Text> createFloatingList(ArrayList<ItemForUserScreen> itemList) {
    	floating.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_FLOATING)){
				/**
			     * @@author A0131300
			     */ 
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				floating.add(text);
				/** 
			     * @@author A0104278
			     */
			}
		}
    	return floating;
    }
    
    private ObservableList<Text> createIncompleteList(ArrayList<ItemForUserScreen> itemList) {
    	incomplete.clear();
    	for (int i = 0; i < itemList.size(); i++) {
    		if (!(itemList.get(i).getIfComplete())) {
    			/**
    		     * @@author A0131300
    		     */ 
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				text.setFill(Color.RED);
				incomplete.add(text);
				/** 
			     * @@author A0104278
			     */
			}
		}
    	return incomplete;
    }
    
    private ObservableList<Text> createCompleteList(ArrayList<ItemForUserScreen> itemList) {
    	complete.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getIfComplete()) {
				/**
			     * @@author A0131300
			     */ 
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				text.setFill(Color.GREEN);
				complete.add(text);
				/** 
			     * @@author A0104278
			     */
			}
		}
    	return complete;
    }    
    
	//@@author A0124524
    private ObservableList<Text> createSearchList(ArrayList<ItemForUserScreen> itemList) {
    	search.clear();
    	for (int i = 0; i < itemList.size(); i++) {
    		if (!(itemList.get(i).getIfComplete())) {
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				search.add(text);
			}
		}
    	return search;
    }
    
    /*
	 * testing GUI
	 * stub feedback
	 * @@author A0131300-unused 
	 * Reason: Change of program requirements
	 *
	private static final String TODAY_LAYOUT_FXML = "/main/resources/layouts/Today.fxml";
    private static final String TOMORROW_LAYOUT_FXML = "/main/resources/layouts/Tomorrow.fxml";

    private static final String FEEDBACK_TODAY = "Today's Tasks";
    private static final String FEEDBACK_TOMORROW = "Tomorrow's Tasks";
    
    private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    private static final String FEEDBACK_ADDED = "Successfully Added: ";
    private static final String FEEDBACK_DELETED = "Successfully Deleted: ";
    private static final String FEEDBACK_UPDATED = "Successfully Updated ";
    private static final String FEEDBACK_UNDONE = "Undone: ";
    
    private static final String TYPE_COMPLETE = "complete";
    private static final String TYPE_INCOMPLETE = "incomplete";
    
    private ObservableList<String> today = FXCollections.observableArrayList();
    private ObservableList<String> tomorrow = FXCollections.observableArrayList();

    private String[] arr;
    private String[] array;
    private String typeDisplay;  //types of display in command
    private String command;
    private String description;
    private String type;
    private String newDescription;
    private int listNum;
    private MainApp mainApp;*/
    
    /*
	 * testing GUI
	 * stub data
	 * @@author A0131300-unused 
	 * Reason: because these are used to test GUI
	 *
    public MainApp() {    	
    	event.add("school activites");
        event.add("community service");
        event.add("cca");
        event.add("school activities");
        event.add("welfare packs packing");
        event.add("church visit with friend");
        event.add("family outing");
        event.add("family dinner");
        event.add("banquet");
        
        deadline.add("V0.2 Project Manual Submission");
        deadline.add("Review on Reflections");
        deadline.add("Post Lecture Quiz");
        deadline.add("Tutorial Homework");
        deadline.add("V0.2 Features");
        deadline.add("Link All Components Together");
        deadline.add("Essay Submission");
        deadline.add("Demo");
        deadline.add("V0.5 Poject Manual Submission");
        
        floating.add("badminton with friends");
        floating.add("project meeting");
        floating.add("meal with family");
        floating.add("replace broken cup");
        floating.add("study for test");
        floating.add("movie");
        floating.add("buy birthday present");
        floating.add("shopping");
        floating.add("dinner with friends");
        
        Text text = new Text("V0.1 Project Manual");
        text.setFill(Color.GREEN);
        Text t2 = new Text("V0.1 Live Demo");
        t2.setFill(Color.GREEN);
        Text t3 = new Text("Study for mid-terms");
        t3.setFill(Color.GREEN);
        
        complete.add("hello");
        complete.add("world");
        complete.add(text);
        complete.add(t2);
        complete.add(t3);
        
        Text text2 = new Text("Adde All V0.2 Features");
        text2.setFill(Color.RED);
        
        incomplete.add("not yet");
        incomplete.add("undone");
        incomplete.add(text2);
        
        today.add("Change GUI");
        
        tomorrow.add("Edit Developer Guide");
    	
    }*/
    
    /*
	 * Display today and tomorrow's tasks individually
	 * @@author A0131300-unused
	 * Reason: due to change in plans
	 *	
    public ObservableList<String> getToday() {
    	return today;
    }

    public ObservableList<String> getTmr() {
    	return tomorrow;
    }*/
    
    /*
	 * Display today and tomorrow's tasks individually
	 * @@author A0131300-unused 
	 * Reason: due to change in plans
	 * 
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
    }*/
    
    /*
	 * Display today and tomorrow's tasks individually
	 * @@author A0131300-unused 
	 * Reason: due to change in plans
	 * 
    public void handleEnterPress(CommandBarController commandBarController, String _userInput ) {
    
		else if (userInput.equals(TODAY_SCENE)) {
			addToday();
			commandBarController.setFeedback(FEEDBACK_TODAY);
			commandBarController.clear();
		} else if (userInput.equals(TOMORROW_SCENE)) {
			addTmr();
			commandBarController.setFeedback(FEEDBACK_TOMORROW);
			commandBarController.clear();
		}
	}*/
	
	/* 
     * Determines user input
     * @@author A0131300-unused 
     * Reason: as this section is for testing the GUI separately
     *
	public void handleEnterPress(CommandBarController commandBarController, String _userInput ) {
		
		else if(userInput.substring(0, 6).equals("delete") || 
			userInput.substring(0, 3).equals("del") ||
			userInput.substring(0, 1).equals("d")) {
		listNum = Integer.parseInt(userInput.substring(7));
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
	
		else {
		
		Command command = createCommand(userInput);
		command.execute();
		
		update (num)
		commandBarController.setText(string event returned by logic);
		
		ArrayList<String> list = new ArrayList<String>();
		Command command = createCommand(userInput);
		list = command.execute();
		
		handleEnterPress(commandBarController, list.get(0));
		
		listView.getItems().remove(item);  // Here I remove the item form my list.
		remove item from incomplete list when user is done with it and add it into complete list
		
		arr = userInput.split(" ", 3);
		command = arr[0];
	
		switch (command) {
        
			case "add":
				type = arr[1];
	    		description = (arr[2]).trim();
				//Logic.takeAction(userInput);
	    		if (type.equals(TYPE_DEADLINE)) {
					deadline.add(description);
				} else if(type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if(type.equals(TYPE_FLOATING)) {
					floating.add(description);
				} else {
					description = type + " " + description;
					floating.add(description);
				}
	    		//show added list
	    		//commandBarController.setText("floating");

				commandBarController.setFeedback(FEEDBACK_ADDED + description);
				commandBarController.setText("floating");
				//handleEnterPress(commandBarController, "floating");  //update screen once an action is done
				break;	
			case "create":   //add command
				type = arr[1];
	    		description = (arr[2]).trim();
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.add(description);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				} else {
					description = type + " " + description;
					floating.add(description);
				}
				
				commandBarController.setFeedback(FEEDBACK_ADDED + description);
				break;
			case "a":   //add command
				type = arr[1];
	    		description = (arr[2]).trim();
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.add(description);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				} else {
					description = type + " " + description;
					floating.add(description);
				}
				
				commandBarController.setFeedback(FEEDBACK_ADDED + description);
				break;
			case "c":  //add command
				type = arr[1];
	    		description = (arr[2]).trim();
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.add(description);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				} else {
					description = type + " " + description;
					floating.add(description);
				}
				
				commandBarController.setFeedback(FEEDBACK_ADDED + description);
				break;
			case "update" :
				description = (arr[2]).trim();
				listNum = Integer.parseInt(arr[1]);  //get data from Logic
				
				//Logic.takeAction(userInput);
				commandBarController.setFeedback(FEEDBACK_UPDATED + "\"" + description +
						"\" to " + newDescription);
				break;
			case "delete" :  
				description = (arr[1]).trim();
				listNum = Integer.parseInt(arr[1]);
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.remove(listNum);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				}
				commandBarController.setFeedback(FEEDBACK_DELETED + description);
				break;	
			case "del":  //delete command
				description = (arr[1]).trim();
				listNum = Integer.parseInt(arr[1]);
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.remove(listNum);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				}
				commandBarController.setFeedback(FEEDBACK_DELETED + description);
				break;	
			case "d":  //delete command
				description = (arr[1]).trim();
				listNum = Integer.parseInt(arr[1]);
				//Logic.takeAction(userInput);
				if (type.equals(TYPE_DEADLINE)) {
					deadline.remove(listNum);
				} else if (type.equals(TYPE_EVENT)) {
					event.add(description);
				} else if (type.equals(TYPE_FLOATING)) {
					floating.add(description);
				}
				commandBarController.setFeedback(FEEDBACK_DELETED + description);
				break;	
			case "undo" :
				description = (arr[1]).trim();
				//Logic.takeAction(userInput);
				commandBarController.setFeedback(FEEDBACK_UNDONE + description);
				break;
			case "search" :
				//Logic.takeAction(userInput);
				break;
			default :
				commandBarController.setFeedback(FEEDBACK_INVALID_COMMAND);
				break;
			}
			commandBarController.clear();
		}
	}*/
    
    /*
     * @@author A0131300-unused 
     * Reason: Change of plans (change from Swing to JavaFX)
     *
    package main.java.ui;

    import java.io.IOException;
    import java.io.OutputStream;

    import javax.swing.JTextArea;


    public class CustomOutputStream extends OutputStream {

        private JTextArea textArea;
        
        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }
        
        //b is the byte to be written as character to the JTextArea
        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
            // scrolls the text area to the end of data
            //textArea.setCaretPosition(textArea.getDocument().getLength());
        }

    }*/

    /*
     * @@author A0131300-unused 
     * Reason: Change of plans (change from Swing to JavaFX)
     *
    package main.java.ui;

    import java.io.IOException;
    import java.io.OutputStream;

    import javax.swing.JTextArea;


    public class CustomOutputStream extends OutputStream {

        private JTextArea textArea;
        
        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }
        
        //b is the byte to be written as character to the JTextArea
        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
            // scrolls the text area to the end of data
            //textArea.setCaretPosition(textArea.getDocument().getLength());
        }

    }*/
    
    /*
     * @@author A0104278-unused 
     * Reason: Change of plans (change from Swing to JavaFX)
     *
    package main.java.ui;

    import java.util.Scanner; 

    public class UI { 
        public static void welcome(){ 
            System.out.println("Welcome to ALT4, your personlized agenda manager"); 
        } 
         
        public static String promoteCommand(){ 
            Scanner sc = new Scanner(System.in); 
            System.out.print("Command: "); 
            String command = sc.nextLine(); 
            sc.close(); 
            return command; 
        } 
     
     
        public static void feedback(String action, int code) { 
            switch (action){ 
            case "Add": 
                if (code==0){ 
                    System.out.println("Added!"); 
                }else{ 
                    System.out.println("Failed!"); 
                } 
            } 
             
        }

        public static void feedbackWrongCommand() {
            // TODO Auto-generated method stub
        } 
     } */
}
