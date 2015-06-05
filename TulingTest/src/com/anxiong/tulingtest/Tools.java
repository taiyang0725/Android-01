package com.anxiong.tulingtest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Tools {
	
	private static final String LOG="an-xiong";
	
	public static void showHint(Context context,String text){
		
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLog(String text){
		Log.d(LOG,text);
	}

}
