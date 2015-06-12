package com.anxiong.servicetest;

import com.anxiong.servicetest.aidl.MyAIDLService.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

public class MyService extends Service {

	private MyBind myBind = new MyBind();

	@Override
	public void onCreate() {
		Tools.showLog(" onCreate");
		Tools.showLog("Service:"+Process.myPid());

		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Tools.showLog(" onStartCommand");

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Tools.showLog("onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Tools.showLog("onBind");
		return mstub;
	}

	/**
	 * ºóÌ¨service
	 * */
	class MyBind extends Binder {

		public void downLoader() {
			Tools.showLog("downLoader");
		}

	}

	/**
	 * Ô¶³Ìservice
	 * */
	Stub mstub = new Stub() {

		@Override
		public String plus(String a) throws RemoteException {
			if (a != null) {
				return a.toUpperCase();
			}
			return null;
		}
	};

}
