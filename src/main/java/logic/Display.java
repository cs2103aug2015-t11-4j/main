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
		switch (userInput.get(1).toLowerCase()){
		case "today":
			DataDisplay.displaySummary(Search.obtainTodaySummary());
			break;
		case "tommorrow":
			DataDisplay.displaySummary(Search.obtainTommorrowSummary());
			break;
		case "floating":
			DataDisplay.displayFloating(Search.obtainFloatingTasks());
			break;
		case "event":
			DataDisplay.displayEvent(Search.obtainEventTasks());
			break;
		case "deadline":
			DataDisplay.displayDeadline(Search.obtainDeadlineTasks());
			break;
		case "complete":
			DataDisplay.displayComplete(Search.obtainAllCompleteTasks());
			break;
		case "incomplete":
			DataDisplay.displayIncomplete(Search.obtainAllIncompleteTasks());
			break;
		case "all":
			DataDisplay.displayAll(Search.obtainAllTasks());
		}
	}

}
