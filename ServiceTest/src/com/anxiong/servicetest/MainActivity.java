package com.anxiong.servicetest;

import com.anxiong.servicetest.aidl.MyAIDLService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private MyService.MyBind myBind;
	private ImageView img;
	
	private MyAIDLService myAIDLService;

	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
//			myBind = (MyService.MyBind) service;
			myAIDLService=MyAIDLService.Stub.asInterface(service);
			try {
				String text=myAIDLService.plus("dasdasd");
				Tools.showLog(text);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Tools.showLog("Activity:"+Process.myPid());
		img=(ImageView) findViewById(R.id.img);
		img.setOnClickListener(this);
		
		
		
	}

	public void onClick_start_service(View view) {
		Intent startIntent = new Intent(this, MyService.class);
		startService(startIntent);
		Tools.showLog("onClick_start_service");

	}

	public void onClick_stop_service(View view) {
		Intent stopIntent = new Intent(this, MyService.class);
		stopService(stopIntent);
		Tools.showLog("onClick_stop_service");

	}

	public void onClick_bind_service(View view) {
		Intent bindIntent = new Intent(this, MyService.class);
		bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
		Tools.showLog("onClick_bind_service");

	}

	public void onClick_unbind_service(View view) {
       
		unbindService(serviceConnection);
		Tools.showLog("onClick_unbind_service");

	}

	@Override
	public void onClick(View v) {
		if(myBind!=null){
			
			myBind.downLoader();
		}
		
	}

}
