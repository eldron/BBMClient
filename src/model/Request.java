package model;

import java.util.LinkedList;

public class Request {
	private int id;// request id, unique for each request
	private String content;// content of the request
	private String time;
	private String location;
	private int owner;// who raised the request
	private LinkedList<Integer> handlers;// people who handled the request
	private boolean handled;// whether the request is handled
	
	public Request(int id, String content, String time, String location, int owner, String hs, int hd){
		this.id = id;
		this.content = content;
		this.time = time;
		this.location = location;
		this.owner = owner;
		this.handlers = new LinkedList<Integer>();
		if(hs.length() > 0){
			String [] strings = hs.split(":");
			for(String s : strings){
				this.handlers.add(Integer.parseInt(s));
			}
		}
		this.handled = (hd == 1) ? true : false;
	}
	public void setID(int value){
		id = value;
	}
	
	public int getID(){
		return id;
	}
	
	public void setContent(String value){
		content = value;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setTime(String value){
		time = value;
	}
	
	public String getTime(){
		return time;
	}
	
	public void setLocation(String value){
		location = value;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setOwner(int value){
		owner = value;
	}
	
	public int getOwner(){
		return owner;
	}
	
	public void setHandlers(LinkedList<Integer> value){
		handlers = value;
	}
	
	public LinkedList<Integer> getHandlers(){
		return handlers;
	}
	
	public void setHandled(boolean value){
		handled = value;
	}
	
	public boolean getHandled(){
		return handled;
	}
}
