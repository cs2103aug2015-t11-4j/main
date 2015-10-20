package main.java.parser;

public class FlexiCommands {
	
	public static String flexiCommands(String command) {
		switch(command) {
			//create task commands
			case "add":
			case "a":
			case "create":
			case "c":
				return "add";
			//delete task commands
			case "delete":
			case "del":
			case "d":
				return "delete";
			//update task commands
			case "update":
			case "u":
			case "edit":
			case "e":
				return "update";
			//display task commands
			case "display":
			case "view":
			case "v":
				return "display";
			// exit command
			case "exit":
				return "exit";
			//help command
			case "help":
				return "help";
			//undo command
			case "undo":
				return "undo";
			//complete command
			case "complete":
			case "completed":
			case "done":
			case "finished":
			case "finish":
				return "complete";
			//search command
			case "search":
			case "s":
			case "find":
			case "f":
				return "search";
			//recurring task
			case "recurring":
			case "recur":
			case "r":
				return "recurring";
			default:
				return "invalid command";	
		}
	}
}
