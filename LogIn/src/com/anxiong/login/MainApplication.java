package com.anxiong.login;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

public class MainApplication  extends LitePalApplication{
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Connector.getDatabase();
	}

}
