package model;

import java.util.LinkedList;

public class User {
	private int id;// unique for every user, start from 0
	private String name;// user name, also unique for every user
	private String ip;
	private boolean loggedin;
	private LinkedList<Integer> friends;
	private int score;
	private LinkedList<Integer> raisedRequests;
	private LinkedList<Integer> handledRequests;
	
	public User(){
		
	}
	
	public User(int id, String name, int score, String f, String raisedrequests, String handledrequests){
		this.id = id;
		this.name = name;
		this.score = score;
		ip = null;
		loggedin = false;
		
		this.friends = new LinkedList<Integer>();
		if(f.length() > 0){
			String [] strings = f.split(":");
			for(String s : strings){
				this.friends.add(Integer.parseInt(s));
			}
		}
		this.raisedRequests = new LinkedList<Integer>();
		if(raisedrequests.length() > 0){
			String [] strings = raisedrequests.split(":");
			for(String s : strings){
				this.raisedRequests.add(Integer.parseInt(s));
			}
		}
		this.handledRequests = new LinkedList<Integer>();
		if(handledrequests.length() > 0){
			String [] strings = handledrequests.split(":");
			for(String s : strings){
				this.handledRequests.add(Integer.parseInt(s));
			}
		}
	}
	
	public void setID(int value){
		id = value;
	}
	
	public int getID(){
		return id;
	}
	
	public void setName(String value){
		name = value;
	}
	
	public String getName(){
		return name;
	}
	
	public void setIP(String value){
		ip = value;
	}
	
	public String getIP(){
		return ip;
	}
	
	public void setLoggedin(boolean value){
		loggedin = value;
	}
	
	public boolean getLoggedin(){
		return loggedin;
	}
	
	public void setFriends(LinkedList<Integer> value){
		friends = value;
	}
	
	public LinkedList<Integer> getFriends(){
		return friends;
	}
	
	public void setScore(int value){
		score = value;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setRaisedRequests(LinkedList<Integer> value){
		raisedRequests = value;
	}
	
	public LinkedList<Integer> getRaisedRequests(){
		return this.raisedRequests;
	}
	
	public void setHandledRequests(LinkedList<Integer> value){
		this.handledRequests = value;
	}
	
	public LinkedList<Integer> getHandledRequests(){
		return this.handledRequests;
	}
}
