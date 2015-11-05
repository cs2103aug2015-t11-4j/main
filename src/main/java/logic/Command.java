//@Author:Jiahuan

package main.java.logic;

import main.java.resources.OutputToUI;

//Command interface
public interface Command {
	OutputToUI outputToUI = new OutputToUI();

	OutputToUI execute();
	OutputToUI undo();
	OutputToUI redo();
}
