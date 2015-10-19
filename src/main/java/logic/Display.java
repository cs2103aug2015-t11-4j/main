//@author : Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.resources.DataDisplay;

public class Display implements Command {
	private ArrayList<String> userInput = new ArrayList<String>();

	public Display(ArrayList<String> userInput) {
		this.userInput = userInput;
	}

	@Override
	public void execute() {
		Search search; //TODO: TO change search to static methods
		switch (userInput.get(1).toLowerCase()){
		case "today":
			DataDisplay.displaySummary(search.obtainTodaySummary());
			break;
		case "tommorrow":
			DataDisplay.displaySummary(search.obtainTommorrowSummary());
			break;
		case "floating":
			DataDisplay.displayFloating(search.obtainFloatingTasks());
			break;
		case "event":
			DataDisplay.displayEvent(search.obtainEventTasks());
			break;
		case "deadline":
			DataDisplay.displayDeadline(search.obtainDeadlineTasks());
			break;
		case "complete":
			DataDisplay.displayComplete(search.obtainAllCompleteTasks());
			break;
		case "incomplete":
			DataDisplay.displayIncomplete(search.obtainAllIncompleteTasks());
			break;
		case "all":
			DataDisplay.displayAll(search.obtainAllTasks());
		}
	}

}
