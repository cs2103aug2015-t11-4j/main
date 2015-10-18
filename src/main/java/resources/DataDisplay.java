//@Author: Jiahuan

package main.java.resources;

import java.util.ArrayList;


public class DataDisplay {
	
	private static final String LABEL_FLOATING = "Floating";
	private static final String LABEL_EVENT = "Event";
	private static final String LABEL_DEADLINE = "Deadline";
	private static final String LABEL_ALL = "All";
	private static final String LABEL_COMPLETE = "Complete";
	private static final String LABEL_INCOMPLETE = "Incomplete";
	private static final String TASK_TYPE_FLOATING_LOWERCASE = "floating";
	private static final String TASK_TYPE_EVENT_LOWERCASE = "event";
	private static final String TASK_TYPE_DEADLINE_LOWERCASE = "deadline";
	
	
	//To display summary in the summary list
	public static ArrayList<String> displaySummary(ArrayList<Task> summaryList){
		ArrayList<String> outputForTesting = new ArrayList<String>();
		//To get a count of how many different type of tasks are there
		ArrayList<Integer> taskTypeCount = new ArrayList<Integer>(3); 
		taskTypeCount = countTaskTypeNum (summaryList); 
		//print different task with a label above them
		if (taskTypeCount.get(0)!=0){
			System.out.println(LABEL_DEADLINE);
			outputForTesting.add(LABEL_DEADLINE);
			for (int i = 0; i < taskTypeCount.get(0); i++){
				int itemNum = i + 1;
				System.out.println(itemNum + ": " + createContentForDeadline(summaryList.get(i)));
				outputForTesting.add(itemNum + ": " + createContentForDeadline(summaryList.get(i)));
			}
		}
		if (taskTypeCount.get(0)!=0){
			System.out.println(LABEL_EVENT);
			outputForTesting.add(LABEL_EVENT);
			for (int i = 0; i < taskTypeCount.get(1); i++){
				int j = i + taskTypeCount.get(0);
				int itemNum = j + 1;
				System.out.println(itemNum + ": " + createContentForEvent(summaryList.get(j)));
				outputForTesting.add(itemNum + ": " + createContentForEvent(summaryList.get(j)));
			}
		}
		if (taskTypeCount.get(0)!=0){
			System.out.println(LABEL_FLOATING);
			outputForTesting.add(LABEL_FLOATING);
			for (int i = 0; i < taskTypeCount.get(2); i++){
				int j = i + taskTypeCount.get(0)+taskTypeCount.get(1);
				int itemNum = j + 1;
				System.out.println(itemNum + ": " + createContentForFloating(summaryList.get(j)));
				outputForTesting.add(itemNum + ": " + createContentForFloating(summaryList.get(j)));
			}
		}
		return outputForTesting;
	}
	
	
	//To count the number of different task types
	public static ArrayList<Integer> countTaskTypeNum(ArrayList<Task> summaryList) {
		ArrayList<Integer> taskTypeCount = new ArrayList<Integer>(3);
		int deadlineCount =0, eventCount = 0, floatingCount = 0;
		for (int i = 0; i < summaryList.size(); i++){
			if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_DEADLINE_LOWERCASE)){
				deadlineCount++;
			} else if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_EVENT_LOWERCASE)){
				eventCount++;
			} else if (summaryList.get(i).getTaskType().equalsIgnoreCase(TASK_TYPE_FLOATING_LOWERCASE)){
				floatingCount++;
			}
		}
		taskTypeCount.add(deadlineCount);
		taskTypeCount.add(eventCount);
		taskTypeCount.add(floatingCount);
	return taskTypeCount;
	}
	
	//Display all tasks including complete and incomplete tasks
	public static ArrayList<String> displayAll(ArrayList<Task> allList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_ALL);
		outputForTesting = displayList(allList);
		outputForTesting.add(0, LABEL_ALL);
		return outputForTesting;
	}	
	
	//Display all complete tasks
	public static ArrayList<String> displayComplete(ArrayList<Task> completeList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_COMPLETE);
		outputForTesting = displayList(completeList);
		outputForTesting.add(0, LABEL_COMPLETE);
		return outputForTesting;
	}	
	
	//Display all incomplete tasks
	public static ArrayList<String> displayIncomplete(ArrayList<Task> incompleteList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_INCOMPLETE);
		outputForTesting = displayList(incompleteList);
		outputForTesting.add(0, LABEL_INCOMPLETE);
		return outputForTesting;
	}	
	
	//Display all floating tasks
	public static ArrayList<String> displayFloating(ArrayList<Task> floatingList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_FLOATING);
		outputForTesting = displayList(floatingList);
		outputForTesting.add(0, LABEL_FLOATING);
		return outputForTesting;
	}	
	
	//Display all deadline tasks
	public static ArrayList<String> displayDeadline(ArrayList<Task> deadlineList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_DEADLINE);
		outputForTesting = displayList(deadlineList);
		outputForTesting.add(0, LABEL_DEADLINE);
		return outputForTesting;
	}	
	
	//Display all event tasks
	public static ArrayList<String> displayEvent(ArrayList<Task> eventList) {
		ArrayList<String> outputForTesting = new ArrayList<String>();
		System.out.println(LABEL_EVENT);
		outputForTesting = displayList(eventList);
		outputForTesting.add(0, LABEL_EVENT);
		return outputForTesting;
	}	
	
	//To display a given list of task in order
	private static ArrayList<String> displayList(ArrayList<Task> listForPrint){
		ArrayList<String> outputForTesting = new ArrayList<String>();
		for (int i = 0; i < listForPrint.size(); i++){
			int itemNum = i + 1;
			switch (listForPrint.get(i).getTaskType().toLowerCase()){
			case TASK_TYPE_DEADLINE_LOWERCASE:
				System.out.println(itemNum + ": " + createContentForDeadline(listForPrint.get(i)));
				outputForTesting.add(itemNum + ": " + createContentForDeadline(listForPrint.get(i)));
			break;
			case TASK_TYPE_EVENT_LOWERCASE:
				System.out.println(itemNum + ": " + createContentForEvent(listForPrint.get(i)));
				outputForTesting.add(itemNum + ": " + createContentForEvent(listForPrint.get(i)));
			break; 
			case TASK_TYPE_FLOATING_LOWERCASE:
				System.out.println(itemNum + ": " + createContentForFloating(listForPrint.get(i)));
				outputForTesting.add(itemNum + ": " + createContentForFloating(listForPrint.get(i)));
			break;
			}
		}
		return outputForTesting;
	}
	


	private static String createContentForFloating(Task task) {
		
		return task.getTaskDescription();
	}

	private static String createContentForEvent(Task task) {
		
		return "From " + task.getStartTime() + ", " + task.getStartDate()+ 
				" to " + task.getEndTime() + ", " + task.getEndDate()+ ": " + task.getTaskDescription();
	
	}

	private static String createContentForDeadline(Task task) {

		return "By "+task.getEndTime() + ", " + task.getEndDate()+": "+ task.getTaskDescription();
	}

}
