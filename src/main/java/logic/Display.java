//@author : Jiahuan
package main.java.logic;

import java.util.ArrayList;

import main.java.resources.DataDisplay;
import main.java.resources.OutputToUI;
import main.java.storage.Storage;

public class Display implements Command {
	private ArrayList<String> userInput = new ArrayList<String>();
	private Storage storage;

	public Display(ArrayList<String> userInput, Storage storage) {
		this.userInput = userInput;
		this.storage = storage;
	}

	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
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
		return outputToUI;
	}

}
