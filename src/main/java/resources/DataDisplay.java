/*
 * @@author A0104278 
 */

package main.java.resources;

import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.logic.Command;
import main.java.logic.History;
/*
 * This class is for display various informations
 * By creating the result by various different input
 */
public class DataDisplay {

	private static final Logger log = Logger.getLogger( DataDisplay.class.getName() );
	private static final String TASK_TYPE_FLOATING_LOWERCASE = "floating";
	private static final String TASK_TYPE_EVENT_LOWERCASE = "event";
	private static final String TASK_TYPE_DEADLINE_LOWERCASE = "deadline";
	private static final String KEYWORD_ADD = "add";
	private static final String KEYWORD_FROM =" from ";
	private static final String KEYWORD_UPPER_FROM ="From ";
	private static final String KEYWORD_TO =" to ";
	private static final String KEYWORD_BY = " by ";
	private static final String REGEX_COLON = ": ";
	private static final String REGEX_SEMICOLON = ";";
	private static final String REGEX_COMMA =", ";
	

	// To display a given list of task in order
	public static ArrayList<String> displayList(ArrayList<Task> listForPrint) {
		log.log(Level.INFO, "start to display task list");
		ArrayList<String> outputForTesting = new ArrayList<String>();
		if (listForPrint.isEmpty()) {
			log.log(Level.WARNING, "No task. Please enter one to start");
		} else {
			for (int i = 0; i < listForPrint.size(); i++) {
				assert listForPrint.size() > 0; //Make sure there are sth to print
				int itemNum = i + 1;
				switch (listForPrint.get(i).getTaskType().toLowerCase()) {
				case TASK_TYPE_DEADLINE_LOWERCASE:
					log.log(Level.INFO, itemNum + REGEX_COLON + createContentForDeadline(listForPrint.get(i)));
					outputForTesting.add(itemNum + REGEX_COLON + createContentForDeadline(listForPrint.get(i)));
					break;
				case TASK_TYPE_EVENT_LOWERCASE:
					log.log(Level.INFO, itemNum + REGEX_COLON + createContentForEvent(listForPrint.get(i)));
					outputForTesting.add(itemNum + REGEX_COLON + createContentForEvent(listForPrint.get(i)));
					break;
				case TASK_TYPE_FLOATING_LOWERCASE:
					log.log(Level.INFO, itemNum + REGEX_COLON + createContentForFloating(listForPrint.get(i)));
					outputForTesting.add(itemNum + REGEX_COLON + createContentForFloating(listForPrint.get(i)));
					break;
				default:
					log.log(Level.WARNING, "task type is invalid");
					break;
				}
			}
		}
		return outputForTesting;
	}

	// get a strig to display on input box to ask user to update accordingly

	public static String displayTaskNeedForUpdate(Task task) {
		log.log(Level.INFO, "start to display the task for update");
		String outputForTesting = "";
		switch (task.getTaskType()) {
		case TASK_TYPE_DEADLINE_LOWERCASE:
			log.log(Level.INFO,KEYWORD_ADD + updateContentForDeadline(task));
			outputForTesting = KEYWORD_ADD + updateContentForDeadline(task);
			break;
		case TASK_TYPE_FLOATING_LOWERCASE:
			log.log(Level.INFO,KEYWORD_ADD + updateContentForFloating(task));
			outputForTesting = KEYWORD_ADD + updateContentForFloating(task);
			break;
		case TASK_TYPE_EVENT_LOWERCASE:
			log.log(Level.INFO,KEYWORD_ADD + updateContentForEvent(task));
			outputForTesting = KEYWORD_ADD + updateContentForEvent(task);
			break;
		default:
			log.log(Level.WARNING,"task type is invalid");
		}
		return outputForTesting;
	}

	private static String updateContentForDeadline(Task task) {
		log.log(Level.INFO,"update Content For Deadline");
		return task.getTaskDescription() + KEYWORD_BY + task.getEndDate() + REGEX_SEMICOLON + task.getEndTime();
	}

	private static String updateContentForFloating(Task task) {
		log.log(Level.INFO,"update Content For Floating");
		return createContentForFloating(task);
	}

	private static String updateContentForEvent(Task task) {
		log.log(Level.INFO,"update Content For Event");
		return task.getTaskDescription() + KEYWORD_FROM + task.getStartDate() + REGEX_SEMICOLON + task.getStartTime() + " to "
				+ task.getEndDate() + REGEX_SEMICOLON + task.getEndTime();
	}

	private static String createContentForFloating(Task task) {
		log.log(Level.INFO,"create Content For Floating");
		return task.getTaskDescription();
	}

	private static String createContentForEvent(Task task) {
		log.log(Level.INFO,"create Content For Event");
		if (task.getEndTime().equals("-")){
			return KEYWORD_UPPER_FROM  + task.getStartDate() + KEYWORD_TO
					+ task.getEndDate() + REGEX_COLON + task.getTaskDescription();
		}
		return KEYWORD_UPPER_FROM + task.getStartTime() + REGEX_COMMA + task.getStartDate() + KEYWORD_TO + task.getEndTime() + ", "
				+ task.getEndDate() + REGEX_COLON + task.getTaskDescription();
	}

	private static String createContentForDeadline(Task task) {
		log.log(Level.INFO,"create Content For Deadline");
		if (task.getEndTime().equals("-")){
			return KEYWORD_BY + task.getEndDate() + REGEX_COLON + task.getTaskDescription();
		} 

		
		return KEYWORD_BY + task.getEndTime() + REGEX_COMMA + task.getEndDate() + REGEX_COLON + task.getTaskDescription();
	}

	
	//give out different feedback for the user to see
	public static String feedback(String action, int code) {
		// Code = 0: success
		String feedbackMsg="";

		if (code == 0) {
			feedbackMsg = action + " is successful";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;	
		}else if (code == 1){
            feedbackMsg = action + " is not a valid format, please refer to HELP for the correct format";
            log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 2){
			feedbackMsg = action + " is not successful. Task description cannot be empty.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 3){
			feedbackMsg = action + " is not successful. Task start time cannot be empty.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 4){
			feedbackMsg = action + " is not successful. No start time for this task or the new time is not a valid format.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 5){
			feedbackMsg = action + " is not successful. Task start date cannot be empty.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 6){
			feedbackMsg = action + " is not successful. No start date for this task or the new date is not a valid format.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 7){
			feedbackMsg = action + " is not successful. Task end time cannot be empty.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 8){
			feedbackMsg = action + " is not successful. No end time for this task or the new time is not a valid format.";
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 9){
			feedbackMsg = action + " is not successful. There is no task to be " + action.toLowerCase() + "ne" ;
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 10){
			feedbackMsg = "Unsuccessful. There is no task No. " + action + " on screen" ;
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code == 11){
			feedbackMsg = "Delete recurring tasks is not successful, task No. "+ action + " is not a recurring task" ;
			log.log(Level.INFO,feedbackMsg);
			return feedbackMsg;
		}else if (code < 0){
			feedbackMsg = action + " is not successful, please enter the correct format";
			log.log(Level.INFO,feedbackMsg);
		}else 
			log.log(Level.WARNING, "feedback code is invalid");
			
		return feedbackMsg;
	}

	// For testing, to print info in outputToUI
	public static void printOutputToUI(OutputToUI outputToUI) {
		ArrayList<ItemForUserScreen> itemList = outputToUI.getItemList();
		System.out.println("typeOfScreen: " + outputToUI.getTypeOfScreen());
		System.out.println("ItemList: ");
		if (itemList == null) {
			System.out.println("ItemList is empty");
		} else {
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i).getIfComplete() + "_" + itemList.get(i).getTaskType() + "_"
						+ itemList.get(i).getPrintOnScreenMsg());
			}
		}
		System.out.println("FeedbackMsg: " + outputToUI.getFeedbackMsg());
		System.out.println("inputBoxMsg: " + outputToUI.getInputBoxMsg());
	}
	
	//for testing, to print the command content
	public static void printUndoCommandList(){
		History history = History.getInstance();
		Stack<Command> undoCommandList = history.getUndoCommandList();
		System.out.println("Printing undo List:");
		int size = undoCommandList.size();
		for (int i = 0; i < size; i++){
			Command command = undoCommandList.get(i);
			System.out.println(command);
		}
	}
	
	public static void printRedoCommandList(){
		History history = History.getInstance();
		Stack<Command> redoCommandList = history.getRedoCommandList();
		System.out.println("Printing redo List:");
		int size = redoCommandList.size();
		for (int i = 0; i < size; i++){
			Command command = redoCommandList.get(i);
			System.out.println(command);
		}
	}
	
	/*
	 * @@author A0104278-unused
	 * Reason: Unused due to change of screen listviews 
	 */
	//private static final String LABEL_FLOATING = "Floating";
	//private static final String LABEL_EVENT = "Event";
	//private static final String LABEL_DEADLINE = "Deadline";
	//private static final String LABEL_ALL = "All";
	//private static final String LABEL_COMPLETE = "Complete";
	//private static final String LABEL_INCOMPLETE = "Incomplete";
	/*
	 * // To display summary in the summary list
	public static ArrayList<String> displaySummary(ArrayList<Task> summaryList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// To get a count of how many different type of tasks are there
		ArrayList<Integer> taskTypeCount = new ArrayList<Integer>(3);
		taskTypeCount = countTaskTypeNum(summaryList);
		// print empty if nothing in list
		if (summaryList.size() == 0) {
			System.out.println("No task exists, please add one task to start");
			outputForTesting.add("No task exists, please add one task to start");
			return outputForTesting;
		}

		// print different task with a label above them
		if (taskTypeCount.get(0) != 0) {
			// System.out.println(LABEL_DEADLINE);
			// outputForTesting.add(LABEL_DEADLINE);
			for (int i = 0; i < taskTypeCount.get(0); i++) {
				int itemNum = i + 1;
				// System.out.println(itemNum + ": " +
				// createContentForDeadline(summaryList.get(i)));
				outputForTesting.add(itemNum + ": " + createContentForDeadline(summaryList.get(i)));
			}
		}
		if (taskTypeCount.get(0) != 0) {
			// System.out.println(LABEL_EVENT);
			// outputForTesting.add(LABEL_EVENT);
			for (int i = 0; i < taskTypeCount.get(1); i++) {
				int j = i + taskTypeCount.get(0);
				int itemNum = j + 1;
				// System.out.println(itemNum + ": " +
				// createContentForEvent(summaryList.get(j)));
				outputForTesting.add(itemNum + ": " + createContentForEvent(summaryList.get(j)));
			}
		}
		if (taskTypeCount.get(0) != 0) {
			// System.out.println(LABEL_FLOATING);
			// outputForTesting.add(LABEL_FLOATING);
			for (int i = 0; i < taskTypeCount.get(2); i++) {
				int j = i + taskTypeCount.get(0) + taskTypeCount.get(1);
				int itemNum = j + 1;
				// System.out.println(itemNum + ": " +
				// createContentForFloating(summaryList.get(j)));
				outputForTesting.add(itemNum + ": " + createContentForFloating(summaryList.get(j)));
			}
		}
		return outputForTesting;
	}

	// To count the number of different task types
	public static ArrayList<Integer> countTaskTypeNum(ArrayList<Task> summaryList) {
		ArrayList<Integer> taskTypeCount = new ArrayList<Integer>(3);
		int deadlineCount = 0, eventCount = 0, floatingCount = 0;
		for (int i = 0; i < summaryList.size(); i++) {
			if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_DEADLINE_LOWERCASE)) {
				deadlineCount++;
			} else if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_EVENT_LOWERCASE)) {
				eventCount++;
			} else if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_FLOATING_LOWERCASE)) {
				floatingCount++;
			}
		}
		taskTypeCount.add(deadlineCount);
		taskTypeCount.add(eventCount);
		taskTypeCount.add(floatingCount);
		return taskTypeCount;
	}

	// Display all tasks including complete and incomplete tasks
	public static ArrayList<String> displayAll(ArrayList<Task> allList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_ALL);
		outputForTesting = displayList(allList);
		// outputForTesting.add(0, LABEL_ALL);
		return outputForTesting;
	}

	// Display all complete tasks
	public static ArrayList<String> displayComplete(ArrayList<Task> completeList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_COMPLETE);
		outputForTesting = displayList(completeList);
		// outputForTesting.add(0, LABEL_COMPLETE);
		return outputForTesting;
	}

	// Display all incomplete tasks
	public static ArrayList<String> displayIncomplete(ArrayList<Task> incompleteList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_INCOMPLETE);
		outputForTesting = displayList(incompleteList);
		// outputForTesting.add(0, LABEL_INCOMPLETE);
		return outputForTesting;
	}

	// Display all floating tasks
	public static ArrayList<String> displayFloating(ArrayList<Task> floatingList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_FLOATING);
		outputForTesting = displayList(floatingList);
		// outputForTesting.add(0, LABEL_FLOATING);
		return outputForTesting;
	}

	// Display all deadline tasks
	public static ArrayList<String> displayDeadline(ArrayList<Task> deadlineList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_DEADLINE);
		outputForTesting = displayList(deadlineList);
		// outputForTesting.add(0, LABEL_DEADLINE);
		return outputForTesting;
	}

	// Display all event tasks
	public static ArrayList<String> displayEvent(ArrayList<Task> eventList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		// System.out.println(LABEL_EVENT);
		outputForTesting = displayList(eventList);
		// outputForTesting.add(0, LABEL_EVENT);
		return outputForTesting;
	}
	*/
	

}
