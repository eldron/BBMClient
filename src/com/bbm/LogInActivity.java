package com.bbm;

import genDoc.LogInDocGen;
import genDoc.RegisterDocGen;
import model.StateManager;
import model.User;

import org.jdom.Document;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity {
	private EditText edit;
	private WakeLock wakeLock;
	private ProgressDialog progressDialog;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		edit = (EditText) findViewById(R.id.name);
		wakeLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "VideoReceiverMainActivity");
	}
	
	protected void onResume(){
		super.onResume();
		
		wakeLock.acquire();
		StateManager.getManager().setCurrentHandler(handler);
	}
	
	protected void onPause(){
		super.onPause();
		wakeLock.release();
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case HandlerConstants.REGISTER_RESULT:
				progressDialog.dismiss();
				int userID = (Integer) msg.obj;
				if(userID != -1){
					Toast.makeText(LogInActivity.this, "Registered Successfully!", Toast.LENGTH_LONG).show();
					StateManager.getManager().getLocalUser().setID(userID);
				} else {
					Toast.makeText(LogInActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
				}
				break;
			case HandlerConstants.LOGIN_FAILED:
				progressDialog.dismiss();
				Toast.makeText(LogInActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
				break;
			case HandlerConstants.LOGIN_SUCCESS:
				// launch another activity
				progressDialog.dismiss();
				startActivity(new Intent(LogInActivity.this, FriendsActivity.class));
				break;
			default:
				break;
			}
		}
	};
	
	public void onLogInClick(View view){
		String name = edit.getText().toString();
		if(name == null || name.equals("")){
			Toast.makeText(LogInActivity.this, "Please input the user name", Toast.LENGTH_LONG).show();
		} else {
			User localUser = new User();
			localUser.setName(name);
			StateManager.getManager().setLocalUser(localUser);
			Document doc = LogInDocGen.genDoc(name);
			StateManager.getManager().getDataSender().add(doc);
			progressDialog = ProgressDialog.show(LogInActivity.this, "", "Logging in to server, wait...", true);
		}
	}
	
	public void onRegisterClick(View view){
		String name = edit.getText().toString();
		if(name == null || name.equals("")){
			Toast.makeText(LogInActivity.this, "Please input the user name", Toast.LENGTH_LONG).show();
		} else {
			Document doc = RegisterDocGen.genDoc(name);
			StateManager.getManager().getDataSender().add(doc);
			progressDialog = ProgressDialog.show(LogInActivity.this, "", "Registering, wait...", true);
		}
	}
}
