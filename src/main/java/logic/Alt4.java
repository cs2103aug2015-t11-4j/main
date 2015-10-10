package main.java.logic;
import java.util.ArrayList;

import main.java.logic.Logic;
import main.java.resources.Task;
import main.java.ui.UI;
import main.java.ui.todoapp;

//@@Author: Jiahuan

public class Alt4 {
	public static ArrayList<Task> taskList;
	public static void main(String[] args) {
		//Jiahuan's version
		//ArrayList<String> contentList = Storage.initializeFile();
		/*ArrayList<String> contentList = new ArrayList<String>();
		UI.welcome();
		String command = UI.promoteCommand();
		ArrayList<String> commandAfterParser = Logic.passToParser(command);
		Logic.takeAction(commandAfterParser, contentList);*/
		
		//Yu Ju's version
		/*ArrayList<String> contentList = new ArrayList<String>();
		String command = args[0];
		String[] text = args;*/
		//ArrayList<String> commandAfterParser = Logic.passToParser(command);
		//contentList = Logic.passToParser(command);
		//Logic.takeAction(commandAfterParser, contentList);
		//Logic.takeAction(contentList);  //I comment off the second para cuz its not used in the method itself
	}

}
