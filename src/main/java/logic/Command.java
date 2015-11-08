/*
 * @@author A0104278 
 */

package main.java.logic;

/*Command interface
 * For all command to implement, so it all can managed as a command with same method:
 * execute, undo and redo*/

import main.java.resources.OutputToUI;

public interface Command {
	OutputToUI outputToUI = new OutputToUI();

	OutputToUI execute();

	OutputToUI undo();

	OutputToUI redo();
}
