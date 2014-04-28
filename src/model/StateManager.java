package model;

import net.DataSender;
import android.os.Handler;

/* manages the states of the client */
public class StateManager {
	private Handler currentHandler;
	private DataSender sender;
	private User localUser;
	
	// singleton
	private static StateManager manager = null;
	private StateManager(){
		currentHandler = null;
		sender = null;
		localUser = null;
	}
	
	public static StateManager getManager(){
		if(manager == null){
			manager = new StateManager();
		}
		
		return manager;
	}
	
	/*
	 * should be called in each activity's onResume function
	 */
	public void setCurrentHandler(Handler handler){
		currentHandler = handler;
	}
	
	public Handler getCurrantHandler(){
		return currentHandler;
	}
	
	public void setDataSender(DataSender s){
		sender = s;
	}
	
	public DataSender getDataSender(){
		return sender;
	}
	
	public void setLocalUser(User value){
		localUser = value;
	}
	
	public User getLocalUser(){
		return localUser;
	}
}