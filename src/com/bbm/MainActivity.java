package com.bbm;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.StateManager;
import net.SocketHandler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Socket sock;
	
	//private WakeLock wakeLock;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //wakeLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "VideoReceiverMainActivity");
		sock = null;
		new Thread(){
			public void run(){
				try {
					sock = new Socket(SystemConstants.SERVER_IP, SystemConstants.SERVER_PORT);
					SocketHandler socketHandler = new SocketHandler(sock);
					new Thread(socketHandler).start();
					
					handler.obtainMessage(1).sendToTarget();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					handler.obtainMessage(0).sendToTarget();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					handler.obtainMessage(0).sendToTarget();
				}
			}
		}.start();
    }
    
    protected void onResume(){
		super.onResume();
		//wakeLock.acquire();
		StateManager.getManager().setCurrentHandler(handler);
	}
	
	protected void onPause(){
		super.onPause();
		//wakeLock.release();
	}
	
	protected void onDestroy(){
		super.onDestroy();
		
		if(sock != null){
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what == 1){
				Toast.makeText(MainActivity.this, "Connected to server!", Toast.LENGTH_LONG);
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
			} else {
				// failed connecting to server
				Toast.makeText(MainActivity.this, "Failed connecting to server!", Toast.LENGTH_LONG).show();
			}
		}
	};
}
