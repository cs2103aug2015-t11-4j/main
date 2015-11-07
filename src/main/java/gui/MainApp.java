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

/**
 * @author Yu Ju
 */

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
    //private static final String TODAY_LAYOUT_FXML = "/main/resources/layouts/Today.fxml";
    //private static final String TOMORROW_LAYOUT_FXML = "/main/resources/layouts/Tomorrow.fxml";
    private static final String COMPLETE_LAYOUT_FXML = "/main/resources/layouts/Complete.fxml";
    private static final String INCOMPLETE_LAYOUT_FXML = "/main/resources/layouts/Incomplete.fxml";
    private static final String HELP_LAYOUT_FXML = "/main/resources/layouts/Help.fxml";
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
    
    //private static final String FEEDBACK_TODAY_SUMMARY = "Today's Summary";
    //private static final String FEEDBACK_TMR_SUMMARY = "Tomorrow's Summary";
    //private static final String FEEDBACK_DISPLAY = "All Events";
    //private static final String FEEDBACK_COMPLETE = "Completed Events";
    //private static final String FEEDBACK_INCOMPLETE = "Incomplete Events";
    //private static final String FEEDBACK_TODAY = "Today's Tasks";
    //private static final String FEEDBACK_TOMORROW = "Tomorrow's Tasks";
    //private static final String FEEDBACK_DEADLINE = "Deadline Tasks";
    //private static final String FEEDBACK_EVENT = "Events";
    //private static final String FEEDBACK_FLOATING = "Floating Tasks";
    
    //private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    //private static final String FEEDBACK_ADDED = "Successfully Added: ";
    //private static final String FEEDBACK_DELETED = "Successfully Deleted: ";
    //private static final String FEEDBACK_UPDATED = "Successfully Updated ";
    private static final String FEEDBACK_EXIT = "Exiting Alt4";
    //private static final String FEEDBACK_UNDONE = "Undone: ";
    
    private static final String TYPE_DEADLINE = "deadline";
    private static final String TYPE_EVENT = "event";
    private static final String TYPE_FLOATING = "floating";
    //private static final String TYPE_COMPLETE = "complete";
    //private static final String TYPE_INCOMPLETE = "incomplete";
    
    private ObservableList<Text> event = FXCollections.observableArrayList();
    private ObservableList<Text> deadline = FXCollections.observableArrayList();
    private ObservableList<Text> floating = FXCollections.observableArrayList();
    private ObservableList<Text> complete = FXCollections.observableArrayList();
    private ObservableList<Text> incomplete = FXCollections.observableArrayList();
    private ObservableList<Text> search = FXCollections.observableArrayList();
    //private ObservableList<String> today = FXCollections.observableArrayList();
    //private ObservableList<String> tomorrow = FXCollections.observableArrayList();
    
    //private String[] arr;
    //private String[] array;
    //private String typeDisplay;  //types of display in command
    //private String command;
    //private String description;
    //private String type;
    //private String newDescription;
    //private int listNum;
    private PauseTransition delay;
	
	private Stage primaryStage;
	private Stage secondaryStage;
    private BorderPane rootLayout;
    private BorderPane rootLayout2;
    //private MainApp mainApp;

    private static ArrayList<ItemForUserScreen> itemList;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	initRootLayout();
        initPrimaryStage(primaryStage);
        
        OutputToUI outputToUI = new OutputToUI();
        /*ItemForUserScreen item = new ItemForUserScreen(false, "event", "family outing");
    	ArrayList<ItemForUserScreen> list = new ArrayList<ItemForUserScreen> ();
    	list.add(item);
    	outputToUI.setTypeOfListView("event");
    	outputToUI.setItemList(list);*/
    	
    	//itemList = outputToUI.getItemList();
        try {
			outputToUI = Controller.initializeProgram();
		} catch (IOException e) {
			e.printStackTrace();
		}
        itemList = outputToUI.getItemList();

        // Add components to RootLayout
        addCommandBar(this);
        createDeadlineList(itemList);
    	createEventList(itemList);
    	createFloatingList(itemList);
    	addTodaySummaryView();
    }

	private void initRootLayout() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_LAYOUT_FXML));
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void initHelpRootLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_LAYOUT2_FXML));
        try {
            rootLayout2 = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(WINDOW_TITLE);
        Scene scene = new Scene(rootLayout);
        //scene.setFill(Color.TRANSPARENT);
        //rootLayout.setBackground(Background.EMPTY);
        //this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
              if (key.getCode() == KeyCode.ESCAPE) {  //close window
            	  primaryStage.close();
              } else if (key.getCode() == KeyCode.F5) {  //minimise the window
            	  primaryStage.setIconified(true);
              } else if (key.getCode() == KeyCode.F6) {  //restore window
            	  if (primaryStage.isShowing()) {
            		  //primaryStage.setIconified(false);
            		  primaryStage.show();
            	  }
            	  //primaryStage.setIconified(false);
            	  //primaryStage.setWidth((double)900);
            	  //primaryStage.setHeight((double)700);
              } else if (key.getCode() == KeyCode.F7) {  //maximise the window
            	  primaryStage.setMaximized(true);
              } else if (key.getCode() == KeyCode.F8) {  //restore to original size
            	  primaryStage.setMaximized(false);
              }
            }
        });
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
              if ((key.getCode() == KeyCode.ESCAPE) || (key.getCode() == KeyCode.ENTER) ||
            	 (key.getCode() == KeyCode.ALT) || (key.getCode() == KeyCode.A) || 
            	 (key.getCode() == KeyCode.B) || (key.getCode() == KeyCode.C) || 
            	 (key.getCode() == KeyCode.D) || (key.getCode() == KeyCode.E) ||
            	 (key.getCode() == KeyCode.F) || (key.getCode() == KeyCode.G) ||
            	 (key.getCode() == KeyCode.H) || (key.getCode() == KeyCode.I) ||
            	 (key.getCode() == KeyCode.J) || (key.getCode() == KeyCode.K) ||
            	 (key.getCode() == KeyCode.L) || (key.getCode() == KeyCode.M) ||
            	 (key.getCode() == KeyCode.N) || (key.getCode() == KeyCode.O) ||
            	 (key.getCode() == KeyCode.P) || (key.getCode() == KeyCode.Q) ||
            	 (key.getCode() == KeyCode.R) || (key.getCode() == KeyCode.S) ||
            	 (key.getCode() == KeyCode.T) || (key.getCode() == KeyCode.U) ||
            	 (key.getCode() == KeyCode.V) || (key.getCode() == KeyCode.W) ||
            	 (key.getCode() == KeyCode.X) || (key.getCode() == KeyCode.Y) ||
            	 (key.getCode() == KeyCode.Z)) {
            	  secondaryStage.close();
              }
            }
        });
    }
    
    //private static ArrayList<ItemForUserScreen> itemList = Controller.getItemList();//JH

    /**
     * Constructor
     */
    public MainApp() {
    	/*
    	 * testing GUI
    	 * stub data
    	 * @@author A0131300-unused because these are used to test GUI
    	 * 
        //event.add(new LocalEvent("Hans"));
        //event.add(new LocalEvent("Ruth"));
    	
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
        
        //complete.add("hello");
        //complete.add("world");
        complete.add(text);
        complete.add(t2);
        complete.add(t3);
        
        Text text2 = new Text("Adde All V0.2 Features");
        text2.setFill(Color.RED);
        
        //incomplete.add("not yet");
        //incomplete.add("undone");
        incomplete.add(text2);
        
        today.add("Change GUI");
        
        tomorrow.add("Edit Developer Guide");*/
    	
    }
    
    //private static ArrayList<ItemForUserScreen> itemList = Controller.getItemList();//JH

    public ObservableList<Text> getEvent() {
    	//event = createEventList(itemList); //JH
    	return event;
    }
    
    public ObservableList<Text> getDeadline() {
    	//deadline = createDeadlineList(itemList);
    	return deadline;
    }
    
    public ObservableList<Text> getFloating() {
    	//floating = createFloatingList(itemList); //JH
    	return floating;
    }
    
    public ObservableList<Text> getComplete() {
    	//complete = createCompleteList(itemList);
    	return complete;
    }
    
    public ObservableList<Text> getIncomplete() {
    	//incomplete = createIncompleteList(itemList);
    	return incomplete;
    }
    
    //@@author: wenbin
    public ObservableList<Text> getSearch() {
    	//incomplete = createIncompleteList(itemList);
    	return search;
    }
    
    
    /*
	 * Display today and tomorrow's tasks individually
	 * @@author A0131300-unused due to change in plans
	 * 
    public ObservableList<String> getToday() {
    	return today;
    }

    public ObservableList<String> getTmr() {
    	return tomorrow;
    }*/
    
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
        	//page.setStyle("-fx-background-color: rgba(230, 230, 250, 0.5)"); //lavendar
        	//page.setStyle("-fx-background-color: rgba(205, 197, 191, 0.5)");  //seashell3
        	//page.setStyle("-fx-background-color: rgba(000, 229, 238, 0.5)");  //turquoise2
        	//page.setStyle("-fx-background-color: rgba(150, 150, 150, 0.5)");  //grey59
        	page.setStyle("-fx-background-color: rgba(204, 204, 204)");  //grey80
        	rootLayout2.setCenter(page);
        	//rootLayout.setTop(page);
        	
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
    
    /*
	 * Display today and tomorrow's tasks individually
	 * @@author A0131300-unused due to change in plans
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
    
    //@@author: wenbin
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

    		/*ItemForUserScreen item = new ItemForUserScreen(false, "event", "family outing");
    		ArrayList<ItemForUserScreen> list = new ArrayList<ItemForUserScreen> ();
    		list.add(item);
    		outputToUI.setTypeOfScreen("event");
    		outputToUI.setItemList(list);*/

    		String userInput = outputToUI.getTypeOfScreen();  //null means no change of screen
    		itemList = outputToUI.getItemList();
    		String feedbackMsg = outputToUI.getFeedbackMsg();
    		String taskToUpdate = outputToUI.getInputBoxMsg();

    		commandBarController.setFeedback(feedbackMsg);
    		
    		//test UI with testFX

    		if (userInput != null) {
    			if (userInput.equals(TODAY_SCENE)) {
    				createDeadlineList(itemList);
    				createEventList(itemList);
    				createFloatingList(itemList);
    				addTodaySummaryView();
    				//commandBarController.setFeedback(FEEDBACK_TODAY_SUMMARY);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(TOMORROW_SCENE)) {
    				createDeadlineList(itemList);
    				createEventList(itemList);
    				createFloatingList(itemList);
    				addTomorrowSummaryView();
    				//commandBarController.setFeedback(FEEDBACK_TMR_SUMMARY);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(FLOATING_SCENE)) {
    				createFloatingList(itemList);
    				addFloating();
    				//commandBarController.setFeedback(FEEDBACK_FLOATING);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(EVENT_SCENE)) {
    				createEventList(itemList);
    				addEvent();
    				//commandBarController.setFeedback(FEEDBACK_EVENT);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(DEADLINE_SCENE)) {
    				createDeadlineList(itemList);
    				addDeadline();
    				//commandBarController.setFeedback(FEEDBACK_DEADLINE);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(COMPLETE_SCENE)) {
    				createCompleteList(itemList);
    				addComplete();
    				//commandBarController.setFeedback(FEEDBACK_COMPLETE);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(INCOMPLETE_SCENE)) {
    				createIncompleteList(itemList);
    				addIncomplete();
    				//commandBarController.setFeedback(FEEDBACK_INCOMPLETE);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			//@@author:wenbin
    			} else if (userInput.equals(SEARCH_SCENE)) {
    				createSearchList(itemList);
    				addSearch();
    				//commandBarController.setFeedback(FEEDBACK_INCOMPLETE);
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(DISPLAY_ALL_SCENE)) {
    				createCompleteList(itemList);
    				createIncompleteList(itemList);
    				addDisplayAll();
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(HELP_SCENE)) {
    				addHelpTable();
    				commandBarController.setFeedback(feedbackMsg);
    				commandBarController.clear();
    			} else if (userInput.equals(EXIT_SCENE)) {
    				commandBarController.setFeedback(FEEDBACK_EXIT);
    				commandBarController.clear();
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
    			
    		/*
    		 * Display today and tomorrow's tasks individually
    		 * @@author A0131300-unused due to change in plans
    		 * 
    		else if (userInput.equals(TODAY_SCENE)) {
    			addToday();
    			commandBarController.setFeedback(FEEDBACK_TODAY);
    			commandBarController.clear();
    		} else if (userInput.equals(TOMORROW_SCENE)) {
    			addTmr();
    			commandBarController.setFeedback(FEEDBACK_TOMORROW);
    			commandBarController.clear();
    		}*/

    		if (taskToUpdate != null) {
    			commandBarController.setText(taskToUpdate);
    			commandBarController.setFeedback(feedbackMsg);
    		}
    }
    		
    	
		//handleEnterPress(commandBarController, userInput);
    	
    	/* 
	     * Determines user input
	     * @@author A0131300-unused as this section is for testing the GUI separately
	     *
    	 * need Logic to pass me the event with task type that is to be deleted
		 * or get task type from Task
    	else if(userInput.substring(0, 6).equals("delete") || 
    			userInput.substring(0, 3).equals("del") ||
    			userInput.substring(0, 1).equals("d")) {
    		listNum = Integer.parseInt(userInput.substring(7));
			//Logic.takeAction(userInput);
			if(type.equals(TYPE_DEADLINE)) {
				deadline.remove(listNum);
			}
			else if(type.equals(TYPE_EVENT)) {
				event.add(description);
			}
			else if(type.equals(TYPE_FLOATING)) {
				floating.add(description);
			}
			commandBarController.setFeedback(FEEDBACK_DELETED + description);  //des being task name
    	}*/
    	
    	//list.get(1).getTaskType();
    	
    	//else {
    		
    		//Command command = createCommand(userInput);
    		//command.execute();
    		
    		//update (num)
    		//commandBarController.setText(string event returned by logic);
    		
    		/* 1:
    		 * handleEnterPress(commandBarController, "(get first param)") 
    		 * to update and show list immediately after action
    		 * 
    		 * 2:
    		 * type = list.get(1);
    		 * description = get the rest of the string?
    		 * 
    		 * 3:
    		 * commandBarController.setFeedback(feedback msg);
    		 * 
    		 * 4:
    		 * commandBarController.setText("event to be updated");
    		 */
    		
    		/*4 param:
    		 * String type of ListView (complete, incomplete, all, floating...)
    		 * ArrayList<ThreeString> obj
    		 * Feedback msg
    		 * String for input box (for update)
    		 */
    		
    		/*ArrayList<String> list = new ArrayList<String>();
    		Command command = createCommand(userInput);
    		list = command.execute();
    		
    		handleEnterPress(commandBarController, list.get(0));*/
    		
    		//listView.getItems().remove(item);  // Here I remove the item form my list.
    		//remove item from incomplete list when user is done with it and add it into complete list
    		
//****************************************************************************************//
//***********************below is all used for testing before*****************************//
//****************************************************************************************//
    		
    		/* 
    	     * Determines user input
    	     * @@author A0131300-unused as this section is for testing the GUI separately
    	     *
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
    
    /**
     * Return list of tasks to be added into the respective listviews
     * 
     * @author Jiahuan
     * edit by Yu Ju
     * @param itemList list of tasks from storage
     * @return list of tasks
     */
    private ObservableList<Text> createEventList(ArrayList<ItemForUserScreen> itemList) {
		event.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_EVENT)){
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				event.add(text);
			}
		}
    	return event;
    }
    
    private ObservableList<Text> createDeadlineList(ArrayList<ItemForUserScreen> itemList) {
    	deadline.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_DEADLINE)){
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				deadline.add(text);
			}
		}
    	return deadline;
    }
    
    private ObservableList<Text> createFloatingList(ArrayList<ItemForUserScreen> itemList) {
    	floating.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTaskType().equals(TYPE_FLOATING)){
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				floating.add(text);
			}
		}
    	return floating;
    }
    
    private ObservableList<Text> createIncompleteList(ArrayList<ItemForUserScreen> itemList) {
    	incomplete.clear();
    	for (int i = 0; i < itemList.size(); i++) {
    		if (!(itemList.get(i).getIfComplete())) {
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				text.setFill(Color.RED);
				incomplete.add(text);
			}
		}
    	return incomplete;
    }
    
    //@@author:wenbin
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
    
    private ObservableList<Text> createCompleteList(ArrayList<ItemForUserScreen> itemList) {
    	complete.clear();
    	for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getIfComplete()) {
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFont(Font.font ("System", 20));
				text.setFill(Color.GREEN);
				complete.add(text);
			}
		}
    	return complete;
    }
}
