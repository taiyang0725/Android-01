package com.anxiong.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.testviewpage.R;

public class BaseActivity extends FragmentActivity {

	/**
	 * 屏宽
	 * */
	protected int screenW;
	/**
	 * 屏高
	 * */
	protected int screenH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setFullScreen();

		initScreen();
	}

	/**
	 * 设置全屏的方法
	 * */
	protected void setFullScreen() {

		/**
		 * 设置标题栏为隐藏的方法
		 * */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 *  获取屏幕大小
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}

	/**
	 * Fragment替换的方法
	 * */
	protected void replace(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.framelayout, fragment).commit();
	}

	protected void setVisibility(View v) {
		if (v.getVisibility() == View.GONE) {
			v.setVisibility(View.VISIBLE);
		} else if (v.getVisibility() == View.VISIBLE) {
			v.setVisibility(View.GONE);

		}

	}
	
	protected void  jump(Context context,Class<?> cls) {
		
		Intent intent=new Intent(context, cls);
		startActivity(intent);
		
	}
	/**
	 * 设置标题栏隐藏的方法
	 * */
	protected void setStatus() {
		getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
		         WindowManager.LayoutParams.FLAG_FULLSCREEN);


	}
}
