
import java.util.ArrayList;

import main.java.logic.Logic;
import main.java.resources.Task;
import main.java.ui.UI;

//@@Author: Jiahuan

public class Alt4 {
	public static ArrayList<Task> taskList;
	public static void main(String[] args) {
//		ArrayList<String> contentList = Storage.initializeFile();
		ArrayList<String> contentList = new ArrayList<String>();
		UI.welcome();
		String command = UI.promoteCommand();
		ArrayList<String> commandAfterParser = Logic.passToParser(command);
		Logic.takeAction(commandAfterParser, contentList);
	}

}
