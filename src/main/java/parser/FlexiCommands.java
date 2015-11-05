package main.java.parser;
//@author: A0124524N; wenbin 

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
			//new update functions
			case "-n":
				return "-n";
			case "-sd":
				return "-sd";
			case "-st":
				return "-st";
			case "-ed":
				return "-ed";
			case "-et":
				return "-et";
			//display task commands
			case "display":
			case "view":
			case "v":
				return "display";
			// exit command
			case "exit":
			case "quit":
				return "exit";
			//help command
			case "help":
				return "help";
			//undo command
			case "undo":
				return "undo";
			// redo command
			case "redo":
				return "redo";
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
			case "incomplete":
				return "incomplete";
			default:
				return "invalid command";	
		}
	}
	
	public static String flexiDisplayCommands(String command){
		
		switch (command.toLowerCase()) {
		case "floating":
			command = "floating";
			break;
		case "event":
			command = "event";
			break;
		case "deadline":
			command = "deadline";
			break;
		case "incomplete":
			command = "incomplete";
			break;
		case "complete":
			command = "complete";
			break;
		case "today":
			command = "today";
			break;
		case "tomorrow":
		case "tmr":
		case "tml":
			command = "tomorrow";
			break;
		case "all":
			command = "all";
			break;
		}
		return command;
	}
}
