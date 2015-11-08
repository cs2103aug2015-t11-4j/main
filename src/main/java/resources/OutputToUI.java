/*
 * @@author A0104278 
 */

package main.java.resources;

import java.util.ArrayList;

public class OutputToUI {
	private String typeOfScreen;
	private ArrayList<ItemForUserScreen> itemList;
	private String feedbackMsg;
	private String inputBoxMsg;
	
	public OutputToUI (){
		typeOfScreen = null;
		itemList = null;
		feedbackMsg = null;
		inputBoxMsg = null;
	}
	
	public OutputToUI(String typeOfScreen, ArrayList<ItemForUserScreen> itemList, String feedbackMsg,
			String inputBoxMsg) {
		this.typeOfScreen = typeOfScreen;
		this.itemList = itemList;
		this.feedbackMsg = feedbackMsg;
		this.inputBoxMsg = inputBoxMsg;
	}
	
	//Accessor
	
	public String getTypeOfScreen(){
		return typeOfScreen;
	}
	
	public ArrayList<ItemForUserScreen> getItemList(){
		return itemList;
	}
	
	public String getFeedbackMsg(){
		return feedbackMsg;
	}
	
	public String getInputBoxMsg(){
		return inputBoxMsg;
	}
	
	//mutator
	public void setTypeOfScreen(String typeOfScreen){
		this.typeOfScreen = typeOfScreen;
	}
	
	public void setItemList(ArrayList<ItemForUserScreen> itemList){
		this.itemList = itemList;
	}
	
	public void setFeedbackMsg(String feedbackMsg){
		this.feedbackMsg = feedbackMsg;
	}
	
	public void setInputBoxMsg(String inputBoxMsg){
		this.inputBoxMsg = inputBoxMsg;
	}
}
