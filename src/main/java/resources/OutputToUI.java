package main.java.resources;
//@Author: Jiahuan

import java.util.ArrayList;

public class OutputToUI {
	private String typeOfListView;
	private ArrayList<ItemForUserScreen> itemList;
	private String feedbackMsg;
	private String inputBoxMsg;
	
	public OutputToUI (){
		typeOfListView = null;
		itemList = null;
		feedbackMsg = null;
		inputBoxMsg = null;
	}
	
	public OutputToUI(String typeOfListView, ArrayList<ItemForUserScreen> itemList, String feedbackMsg,
			String inputBoxMsg) {
		this.typeOfListView = typeOfListView;
		this.itemList = itemList;
		this.feedbackMsg = feedbackMsg;
		this.inputBoxMsg = inputBoxMsg;
	}
	
	//Accessor
	
	public String getTypeOfListView(){
		return typeOfListView;
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
	public void setTypeOfListView(String typeOfListView){
		this.typeOfListView = typeOfListView;
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
