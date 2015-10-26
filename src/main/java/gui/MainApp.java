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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.logic.Command;
import main.java.logic.Controller;
import main.java.resources.ItemForUserScreen;
import main.java.resources.OutputToUI;

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
    //private static final String TODAY_LAYOUT_FXML = "/main/resources/layouts/Today.fxml";
    //private static final String TOMORROW_LAYOUT_FXML = "/main/resources/layouts/Tomorrow.fxml";
    private static final String COMPLETE_LAYOUT_FXML = "/main/resources/layouts/Complete.fxml";
    private static final String INCOMPLETE_LAYOUT_FXML = "/main/resources/layouts/Incomplete.fxml";
    
    private static final String FEEDBACK_TODAY_SUMMARY = "Today's Summary";
    private static final String FEEDBACK_TMR_SUMMARY = "Tomorrow's Summary";
    private static final String FEEDBACK_DISPLAY = "All Events";
    private static final String FEEDBACK_COMPLETE = "Completed Events";
    private static final String FEEDBACK_INCOMPLETE = "Incomplete Events";
    //private static final String FEEDBACK_TODAY = "Today's Tasks";
    //private static final String FEEDBACK_TOMORROW = "Tomorrow's Tasks";
    private static final String FEEDBACK_DEADLINE = "Deadline Tasks";
    private static final String FEEDBACK_EVENT = "Events";
    private static final String FEEDBACK_FLOATING = "Floating Tasks";
    
    private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    private static final String FEEDBACK_ADDED = "Successfully Added: ";
    private static final String FEEDBACK_DELETED = "Successfully Deleted: ";
    private static final String FEEDBACK_UPDATED = "Successfully Updated ";
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
    //private ObservableList<String> today = FXCollections.observableArrayList();
    //private ObservableList<String> tomorrow = FXCollections.observableArrayList();
    
    private String[] arr;
    private String[] array;
    private String typeDisplay;  //types of display in command
    private String command;
    private String description;
    private String type;
    private String newDescription;
    private int listNum;
    private PauseTransition delay;
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private MainApp mainApp;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        itemList = outputToUI.getItemList();

        // Add components to RootLayout
        addCommandBar(this);

        /*if(itemList == null) {
        	addSummaryView();
        }
        else {
        	createDeadlineList(itemList);
        	createEventList(itemList);
        	createFloatingList(itemList);
        	addSummaryView();
        }*/
        createDeadlineList(itemList);
    	createEventList(itemList);
    	createFloatingList(itemList);
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

    private void initPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(WINDOW_TITLE);
        Scene scene = new Scene(rootLayout);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent key) {
              if(key.getCode()==KeyCode.ESCAPE)
              {
            	  primaryStage.close();
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

    public ObservableList<String> getEvent() {
    	//return event;
    	//event = createEventList(itemList); //JH
    	return event;
    }
    
    public ObservableList<String> getDeadline() {
    	//return deadline;
    	//deadline = createDeadlineList(itemList);
    	return deadline;
    }
    
    public ObservableList<String> getFloating() {
    	//return floating;
    	//floating = createFloatingList(itemList); //JH
    	return floating;
    }
    
    public ObservableList<Text> getComplete() {
    	//return complete;
    	//complete = createCompleteList(itemList);
    	return complete;
    }
    
    public ObservableList<Text> getIncomplete() {
    	//return incomplete;
    	//incomplete = createIncompleteList(itemList);
    	return incomplete;
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
    	String feedbackMsg = outputToUI.getFeedbackMsg();
    	itemList = outputToUI.getItemList();

    	if(_userInput.equals("today") || _userInput.equals("tomorrow")) {
    		createDeadlineList(itemList);
    		createEventList(itemList);
    		createFloatingList(itemList);
    		addSummaryView();
    		if(_userInput.equals("today")) {
    			commandBarController.setFeedback(FEEDBACK_TODAY_SUMMARY);
    		}
    		else {
    			commandBarController.setFeedback(FEEDBACK_TMR_SUMMARY);
    		}
    		commandBarController.clear();
    	}
    	
    	
    	else if(_userInput.equals("help")) {
    		
    		commandBarController.clear();
    	}
    	
    	else if(_userInput.equals("exit") || _userInput.equals("quit")) {
    		commandBarController.setFeedback(FEEDBACK_EXIT);
    		commandBarController.clear();
    		delay = new PauseTransition(Duration.seconds(1));  //delay closing of GUI window by 1s
    		delay.setOnFinished(new EventHandler<ActionEvent> () {
    			@Override
    			public void handle(ActionEvent event) {
    				primaryStage.hide();
    			}
    		});
    		delay.play();
    	}
    	
    	/*
    	 * Display today and tomorrow's tasks individually
    	 * @@author A0131300-unused due to change in plans
    	 * 
    	else if(userInput.equals("today")) {
    		addToday();
    		commandBarController.setFeedback(FEEDBACK_TODAY);
    		commandBarController.clear();
    	}
    	
    	else if(userInput.equals("tomorrow") || userInput.equals("tmr")) {
    		addTmr();
    		commandBarController.setFeedback(FEEDBACK_TOMORROW);
    		commandBarController.clear();
    	}*/
    	
    	else if(_userInput.equals("deadline")) {
			createDeadlineList(itemList);
			addDeadline();
			commandBarController.setFeedback(FEEDBACK_DEADLINE);
			commandBarController.clear();
		}
    	
    	else if(_userInput.equals("event")) {
			createEventList(itemList);
			addEvent();
			commandBarController.setFeedback(FEEDBACK_EVENT);
			commandBarController.clear();
		}
    	
    	else if(_userInput.equals("floating")) {
			createFloatingList(itemList);
			addFloating();
			commandBarController.setFeedback(FEEDBACK_FLOATING);
			commandBarController.clear();
		}
    	
    	/*else if(_userInput.equals("display all")) {
    		//itemList = outputToUI.getItemList();
    		createCompleteList(itemList);
    		createIncompleteList(itemList);
    		addDisplayAll();
    		commandBarController.setFeedback(FEEDBACK_DISPLAY);
    		commandBarController.clear();
    	}*/
    	
    	else if(_userInput.equals("display complete")) {
    		createCompleteList(itemList);
    		addComplete();
			commandBarController.setFeedback(FEEDBACK_COMPLETE);
			commandBarController.clear();
    	}
    	
    	else if(_userInput.equals("display incomplete")) {
    		createIncompleteList(itemList);
    		addIncomplete();
    		commandBarController.setFeedback(FEEDBACK_INCOMPLETE);
    		commandBarController.clear();
    	}
		
    	else if(_userInput.equals("display all")) {
    		/*if(itemList.get(0).getIfComplete()) {
			
    			if(itemList.get(0).getTaskType().equals("floating")) {
    				createFloatingList(itemList);
    				//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			else if(itemList.get(0).getTaskType().equals("event")) {
    				createEventList(itemList);
    				//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			else if(itemList.get(0).getTaskType().equals("deadline")) {
    				createDeadlineList(itemList);
    				//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			createCompleteList(itemList);
    			//addSummaryView();
    			commandBarController.setFeedback(feedbackMsg);
    			commandBarController.clear();
    		}
    		
    		else if(!(itemList.get(0).getIfComplete())) {
			
    			if(itemList.get(0).getTaskType().equals("floating")) {
    				createFloatingList(itemList);
    				//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			else if(itemList.get(0).getTaskType().equals("event")) {
    				createEventList(itemList);
					//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			else if(itemList.get(0).getTaskType().equals("deadline")) {
    				createDeadlineList(itemList);
    				//commandBarController.setFeedback(feedbackMsg);
    				//commandBarController.clear();
    			}
			
    			createIncompleteList(itemList);
    			//addSummaryView();
    			commandBarController.setFeedback(feedbackMsg);
    			commandBarController.clear();
    		}*/
		
    		/*else if(itemList.get(0).getTaskType().equals("update")) {
    			description = itemList.get(0).getPrintOnScreenMsg();
    			//complete.add(description);
    			commandBarController.setFeedback(feedbackMsg);
    			commandBarController.setText(description);
    		}*/
    		
    		createFloatingList(itemList);
    		createEventList(itemList);
    		createDeadlineList(itemList);
    		createCompleteList(itemList);
    		createIncompleteList(itemList);
			commandBarController.setFeedback(feedbackMsg);
			commandBarController.clear();
    		
    		//addDisplayAll();
    		addSummaryView();
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
    	    		if(type.equals(TYPE_DEADLINE)) {
    					deadline.add(description);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				else {
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
    				if(type.equals(TYPE_DEADLINE)) {
    					deadline.add(description);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				else {
    					description = type + " " + description;
    					floating.add(description);
    				}
    				
    				commandBarController.setFeedback(FEEDBACK_ADDED + description);
    				break;
    				
    			case "a":   //add command
    				type = arr[1];
    	    		description = (arr[2]).trim();
    				//Logic.takeAction(userInput);
    				if(type.equals(TYPE_DEADLINE)) {
    					deadline.add(description);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				else {
    					description = type + " " + description;
    					floating.add(description);
    				}
    				
    				commandBarController.setFeedback(FEEDBACK_ADDED + description);
    				break;
    				
    			case "c":  //add command
    				type = arr[1];
    	    		description = (arr[2]).trim();
    				//Logic.takeAction(userInput);
    				if(type.equals(TYPE_DEADLINE)) {
    					deadline.add(description);
    				}
    				else if(type.equals(TYPE_EVENT)) {
    					event.add(description);
    				}
    				else if(type.equals(TYPE_FLOATING)) {
    					floating.add(description);
    				}
    				else {
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
    				
    			case "del":  //delete command
    				description = (arr[1]).trim();
    				listNum = Integer.parseInt(arr[1]);
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
    				commandBarController.setFeedback(FEEDBACK_DELETED + description);
    				break;
    				
    			case "d":  //delete command
    				description = (arr[1]).trim();
    				listNum = Integer.parseInt(arr[1]);
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
    		commandBarController.clear();*/
    	//}
    	
    }
    
    /*
     * Below are created by Jiahuan
     * @author Jiahuan
     * 
     */
    private ObservableList<String> createEventList(ArrayList<ItemForUserScreen> itemList) {
		event.clear();
    	for (int i = 0; i < itemList.size(); i ++){
			if (itemList.get(i).getTaskType().equals("event")){
				event.add(itemList.get(i).getPrintOnScreenMsg());
			}
		}
    	return event;
    }
    
    private ObservableList<String> createDeadlineList(ArrayList<ItemForUserScreen> itemList) {
    	deadline.clear();
    	for (int i = 0; i < itemList.size(); i ++){
			if (itemList.get(i).getTaskType().equals("deadline")){
				deadline.add(itemList.get(i).getPrintOnScreenMsg());
			}
		}
    	return deadline;
    }
    
    private ObservableList<String> createFloatingList(ArrayList<ItemForUserScreen> itemList) {
    	floating.clear();
    	for (int i = 0; i < itemList.size(); i ++){
			if (itemList.get(i).getTaskType().equals("floating")){
				floating.add(itemList.get(i).getPrintOnScreenMsg());
			}
		}
    	return floating;
    }
    
    private ObservableList<Text> createIncompleteList(ArrayList<ItemForUserScreen> itemList){
    	incomplete.clear();
    	for (int i = 0; i < itemList.size(); i ++){
			if (itemList.get(i).getTaskType().equals("incomplete")){
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFill(Color.GREEN);
				incomplete.add(text);
			}
		}
    	return incomplete;
    }
    
    private ObservableList<Text> createCompleteList(ArrayList<ItemForUserScreen> itemList){
    	complete.clear();
    	for (int i = 0; i < itemList.size(); i ++){
			if (itemList.get(i).getTaskType().equals("complete")){
				Text text = new Text(itemList.get(i).getPrintOnScreenMsg());
				text.setFill(Color.RED);
				complete.add(text);
			}
		}
    	return complete;
    }

}
