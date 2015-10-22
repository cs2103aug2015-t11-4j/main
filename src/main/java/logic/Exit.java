package main.java.logic;

import main.java.resources.OutputToUI;

public class Exit implements Command {

	@Override
	public OutputToUI execute() {
		OutputToUI outputToUI = new OutputToUI();
		outputToUI.setTypeOfListView("exit");
		return outputToUI;
	}

}
