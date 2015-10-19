package main.java.gui;

public class LocalEvent {

	private String des;
	
	public LocalEvent() {
		
	}
	
	public LocalEvent(String des) {
		this.des = des;
	}
	
	public String getDes() {
		return des;
	}
	
	public void setDes(String des) {
		this.des = des;
	}
	
	/*@Override
	public String toString() {
		return "added: " + this.getDes();
	}*/
}
