import java.util.ArrayList;

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
