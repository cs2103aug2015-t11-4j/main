//@@author Jiahuan

package main.java.resources;

import java.util.ArrayList;

public class DataDisplay {

	//private static final String LABEL_FLOATING = "Floating";
	//private static final String LABEL_EVENT = "Event";
	//private static final String LABEL_DEADLINE = "Deadline";
	//private static final String LABEL_ALL = "All";
	//private static final String LABEL_COMPLETE = "Complete";
	//private static final String LABEL_INCOMPLETE = "Incomplete";
	private static final String TASK_TYPE_FLOATING_LOWERCASE = "floating";
	private static final String TASK_TYPE_EVENT_LOWERCASE = "event";
	private static final String TASK_TYPE_DEADLINE_LOWERCASE = "deadline";

	

	// To display a given list of task in order
	public static ArrayList<String> displayList(ArrayList<Task> listForPrint) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		if (listForPrint.isEmpty()) {
			System.out.println("No task. Please enter one to start");
		} else {
			for (int i = 0; i < listForPrint.size(); i++) {
				int itemNum = i + 1;
				switch (listForPrint.get(i).getTaskType().toLowerCase()) {
				case TASK_TYPE_DEADLINE_LOWERCASE:
				//	System.out.println(itemNum + ": " + createContentForDeadline(listForPrint.get(i)));
					outputForTesting.add(itemNum + ": " + createContentForDeadline(listForPrint.get(i)));
					break;
				case TASK_TYPE_EVENT_LOWERCASE:
				//	System.out.println(itemNum + ": " + createContentForEvent(listForPrint.get(i)));
					outputForTesting.add(itemNum + ": " + createContentForEvent(listForPrint.get(i)));
					break;
				case TASK_TYPE_FLOATING_LOWERCASE:
				//	System.out.println(itemNum + ": " + createContentForFloating(listForPrint.get(i)));
					outputForTesting.add(itemNum + ": " + createContentForFloating(listForPrint.get(i)));
					break;
				}
			}
		}
		return outputForTesting;
	}

	// get a strig to display on input box to ask user to update accordingly
	// TODO: j-unit testing
	public static String displayTaskNeedForUpdate(Task task) {
		String outputForTesting = "";
		switch (task.getTaskType()) {
		case "deadline":
			// System.out.println("add " + updateContentForDeadline(task));
			outputForTesting = "add " + updateContentForDeadline(task);
			break;
		case "floating":
			// System.out.println("add " + updateContentForFloating(task));
			outputForTesting = "add " + updateContentForFloating(task);
			break;
		case "event":
			// System.out.println("add " + updateContentForEvent(task));
			outputForTesting = "add " + updateContentForEvent(task);
			break;
		}
		return outputForTesting;
	}

	private static String updateContentForDeadline(Task task) {
		return task.getTaskDescription() + " by " + task.getEndDate() + ";" + task.getEndTime();
	}

	private static String updateContentForFloating(Task task) {
		return createContentForFloating(task);
	}

	private static String updateContentForEvent(Task task) {
		return task.getTaskDescription() + " from " + task.getStartDate() + ";" + task.getStartTime() + " to "
				+ task.getEndDate() + ";" + task.getEndTime();
	}

	private static String createContentForFloating(Task task) {
		return task.getTaskDescription();
	}

	private static String createContentForEvent(Task task) {
		if (task.getEndTime().equals("-")){
			return "From "  + task.getStartDate() + " to " 
					+ task.getEndDate() + ": " + task.getTaskDescription();
		}
		return "From " + task.getStartTime() + ", " + task.getStartDate() + " to " + task.getEndTime() + ", "
				+ task.getEndDate() + ": " + task.getTaskDescription();
	}

	private static String createContentForDeadline(Task task) {

		if (task.getEndTime().equals("-")){
			return "By " + task.getEndDate() + ": " + task.getTaskDescription();
		} 

		
		return "By " + task.getEndTime() + ", " + task.getEndDate() + ": " + task.getTaskDescription();
	}

	public static String feedback(String action, int code) {
		// TODO: Code = 0: success
		String feedbackMsg;

		if (code == 0) {
			feedbackMsg = action + " is successful";
			System.out.println(feedbackMsg);
			return feedbackMsg;	
		}else if (code == 1){
            feedbackMsg = action + " is not a valid format, please refer to HELP for the correct format";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 2){
			feedbackMsg = action + " is not successful. Task description cannot be empty.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 3){
			feedbackMsg = action + " is not successful. Task start time cannot be empty.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 4){
			feedbackMsg = action + " is not successful. No start time for this task or the new time is not a valid format.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 5){
			feedbackMsg = action + " is not successful. Task start date cannot be empty.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 6){
			feedbackMsg = action + " is not successful. No start date for this task or the new date is not a valid format.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 7){
			feedbackMsg = action + " is not successful. Task end time cannot be empty.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 8){
			feedbackMsg = action + " is not successful. No end time for this task or the new time is not a valid format.";
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 9){
			feedbackMsg = action + " is not successful. There is no task to be " + action.toLowerCase() + "ne" ;
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}else if (code == 10){
			feedbackMsg = "Unsuccessful. There is no task No. " + action + " on screen" ;
			System.out.println(feedbackMsg);
			return feedbackMsg;
		}
		feedbackMsg = action + " is not successful, please enter the correct format";
		System.out.println(feedbackMsg);
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
	
	
	//Unused due to change of screen listviews
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
