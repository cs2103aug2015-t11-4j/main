package main.java.parser;

//@@author: A0124524N; wenbin 
import static org.junit.Assert.*;

import org.junit.Test;

public class FlexiCommandsTest {

	@Test
	public void testFlexiCommands() {
		//flexi commands for adding a task
		assertTrue(FlexiCommands.flexiCommands("add").equals("add"));
		assertTrue(FlexiCommands.flexiCommands("a").equals("add"));
		assertTrue(FlexiCommands.flexiCommands("create").equals("add"));
		assertTrue(FlexiCommands.flexiCommands("c").equals("add"));
		assertFalse(FlexiCommands.flexiCommands("addddddd").equals("add"));
		//delete task commands
		assertTrue(FlexiCommands.flexiCommands("delete").equals("delete"));
		assertTrue(FlexiCommands.flexiCommands("del").equals("delete"));
		assertTrue(FlexiCommands.flexiCommands("d").equals("delete"));
		assertFalse(FlexiCommands.flexiCommands("ddddddd").equals("delete"));
		//update task commands
		assertTrue(FlexiCommands.flexiCommands("-n").equals("-n"));
		assertFalse(FlexiCommands.flexiCommands("-nn").equals("-n"));
		
		assertTrue(FlexiCommands.flexiCommands("-sd").equals("-sd"));
		assertFalse(FlexiCommands.flexiCommands("sd").equals("-sd"));
		
		assertTrue(FlexiCommands.flexiCommands("-ed").equals("-ed"));
		assertFalse(FlexiCommands.flexiCommands("-e").equals("-ed"));
		
		assertTrue(FlexiCommands.flexiCommands("-st").equals("-st"));
		assertFalse(FlexiCommands.flexiCommands("-stt").equals("-st"));
		
		assertTrue(FlexiCommands.flexiCommands("-et").equals("-et"));
		assertFalse(FlexiCommands.flexiCommands("etttt").equals("-et"));
		//display task commands
		assertTrue(FlexiCommands.flexiCommands("display").equals("display"));
		assertTrue(FlexiCommands.flexiCommands("view").equals("display"));
		assertFalse(FlexiCommands.flexiCommands("vvv").equals("display"));
		// exit command
		assertTrue(FlexiCommands.flexiCommands("exit").equals("exit"));
		assertTrue(FlexiCommands.flexiCommands("quit").equals("exit"));
		//help command
		assertFalse(FlexiCommands.flexiCommands("help help").equals("help"));
		//undo command
		// redo command
		assertTrue(FlexiCommands.flexiCommands("redo").equals("redo"));
		assertTrue(FlexiCommands.flexiCommands("undo").equals("undo"));
		//complete command
		assertTrue(FlexiCommands.flexiCommands("complete").equals("complete"));
		assertTrue(FlexiCommands.flexiCommands("completed").equals("complete"));
		assertTrue(FlexiCommands.flexiCommands("done").equals("complete"));
		assertTrue(FlexiCommands.flexiCommands("finished").equals("complete"));
		assertTrue(FlexiCommands.flexiCommands("finish").equals("complete"));
		assertTrue(FlexiCommands.flexiCommands("incomplete").equals("incomplete"));
		assertTrue(FlexiCommands.flexiCommands("incompleted").equals("incomplete"));
		//search command
		assertTrue(FlexiCommands.flexiCommands("search").equals("search"));
		assertTrue(FlexiCommands.flexiCommands("find").equals("search"));
		assertTrue(FlexiCommands.flexiCommands("s").equals("search"));
		assertTrue(FlexiCommands.flexiCommands("f").equals("search"));
		//recurring task
		assertTrue(FlexiCommands.flexiCommands("recurring").equals("recurring"));
		assertTrue(FlexiCommands.flexiCommands("recur").equals("recurring"));
		assertTrue(FlexiCommands.flexiCommands("r").equals("recurring"));

		assertTrue(FlexiCommands.flexiCommands("set").equals("set"));
		
		//invalid cases
		assertTrue(FlexiCommands.flexiCommands("hahaha").equals("invalid command"));
		assertFalse(FlexiCommands.flexiCommands("abcdefg add adwd").equals("add"));
	}

	@Test
	public void testFlexiDisplayCommands() {
		//tasks: deadline event floating
		assertTrue(FlexiCommands.flexiDisplayCommands("deadline").equals("deadline"));
		assertTrue(FlexiCommands.flexiDisplayCommands("event").equals("event"));
		assertTrue(FlexiCommands.flexiDisplayCommands("floating").equals("floating"));
		
		//complete incomplete
		assertTrue(FlexiCommands.flexiDisplayCommands("completed").equals("complete"));
		assertTrue(FlexiCommands.flexiDisplayCommands("complete").equals("complete"));
		assertTrue(FlexiCommands.flexiDisplayCommands("finished").equals("complete"));
		assertTrue(FlexiCommands.flexiDisplayCommands("done").equals("complete"));
		
		assertTrue(FlexiCommands.flexiDisplayCommands("incomplete").equals("incomplete"));
		assertTrue(FlexiCommands.flexiDisplayCommands("incompleted").equals("incomplete"));

		//today tomorrow all
		assertTrue(FlexiCommands.flexiDisplayCommands("today").equals("today"));
		
		assertTrue(FlexiCommands.flexiDisplayCommands("tomorrow").equals("tomorrow"));
		assertTrue(FlexiCommands.flexiDisplayCommands("tmr").equals("tomorrow"));
		assertTrue(FlexiCommands.flexiDisplayCommands("tml").equals("tomorrow"));
		
		assertTrue(FlexiCommands.flexiDisplayCommands("all").equals("all"));
	}

}
