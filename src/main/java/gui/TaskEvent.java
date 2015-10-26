package main.java.gui;

/**
 * 
 * @author Yu Ju
 *
 */

public class TaskEvent {

	private String command;
	private String flexi;
	private String format;
	private String comment;
	private String result;
	
	public TaskEvent(String newCommand, String newFlexi, String newFormat, 
			String newComment, String newResult) {
		
		setCommand(newCommand);
		setFlexi(newFlexi);
		setFormat(newFormat);
		setComment(newComment);
		setResult(newResult);
	}
	
	public void setCommand(String newCommand) {
		command = newCommand;
	}
	
	public void setFlexi(String newFlexi) {
		flexi = newFlexi;
	}
	
	public void setFormat(String newFormat) {
		format = newFormat;
	}
	
	public void setComment(String newComment) {
		comment = newComment;
	}
	
	public void setResult(String newResult) {
		result = newResult;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getFlexi() {
		return flexi;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getResult() {
		return result;
	}
}
