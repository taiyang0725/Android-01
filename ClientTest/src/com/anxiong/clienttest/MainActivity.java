package com.anxiong.clienttest;

import com.anxiong.servicetest.aidl.MyAIDLService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {

	private MyAIDLService myAIDLService;

	ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myAIDLService = MyAIDLService.Stub.asInterface(service);

			try {
				String text = myAIDLService.plus("wangan");
				System.out.println(text);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick_bind_service(View view) {
		Intent bindIntent = new Intent(
				"com.anxiong.servicetest.aidl.MyAIDLService");
		bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
	}

	public void onClick_unbind_service(View view) {
		unbindService(serviceConnection);
	}

}
