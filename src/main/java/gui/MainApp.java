package main.java.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * MainApp is the entry point for JavaFX applications.
 * 
 * @author Yu Ju
 *
 */
public class MainApp extends Application {

    private static final String ROOT_LAYOUT_FXML = "/main/resources/layouts/RootLayouts.fxml";
    private static final String WINDOW_TITLE = "Alt4";
    
    private static final String FEEDBACK_INVALID_COMMAND = "Invalid command.";
    private static final String FEEDBACK_TASK_ADDED = "Task added!";
    private static final String FEEDBACK_TASK_DELETED = "Task deleted!";
    private static final String FEEDBACK_TASK_UPDATED = "Task updated!";

    private Stage primaryStage;
    private BorderPane rootLayout;

    private Logic logic;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initRootLayout();
        initPrimaryStage(primaryStage);

        initLogic();

        // Add components to RootLayout
        addCommandBar(this);
    }

    /**
     * Initialises the RootLayout that will contain all other JavaFX components.
     */
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

    private void initLogic() {
        logic = new Logic();
    }

    private void addCommandBar(MainApp mainApp) {
        rootLayout.setBottom(new CommandBarController(mainApp));
    }


    // ================================================================
    // Methods which refer to Logic directly
    // ================================================================

    public void handleKeyPress(CommandBarController commandBarController,
                               KeyCode key,
                               String userInput) {
        if (key == KeyCode.ENTER) {
            handleEnterPress(commandBarController, userInput);
        }
    }

    private void handleEnterPress(CommandBarController commandBarController,
                                  String userInput) {
    	
    	switch(logic.executeCommand(userInput)) {
    		case ADD :
    			commandBarController.setFeedback(FEEDBACK_TASK_ADDED);
    			break;
    		case DELETE:
    			commandBarController.setFeedback(FEEDBACK_TASK_DELETED);
    			break;
    		case UPDATE:
    			commandBarController.setFeedback(FEEDBACK_TASK_UPDATED);
    			break;
    		case INVALID :
    		default :
    			commandBarController.setFeedback(FEEDBACK_INVALID_COMMAND);
    			break;
    	}
    	commandBarController.setFeedback("data added");  //stub
        commandBarController.clear();
    }
}