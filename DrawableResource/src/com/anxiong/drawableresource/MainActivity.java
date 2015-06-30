package com.anxiong.drawableresource;

import com.an.objectanimator.AnimActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imgLevel;
	private ImageView imgAnim;
	private ImageView imgCilp;
	private ImageView imgRefresh;

	private Boolean isClip;
	private int mclip = 0;

	private ClipDrawable clipDrawable;

	private AnimationDrawable animationDrawable;
	
	private Animation animation;

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:
				if (clipDrawable != null) {
					int a = msg.arg1;
					clipDrawable.setLevel(a);
				}
				break;
				
			case 2:
				if(imgRefresh!=null){
					imgRefresh.clearAnimation();
				}

			default:
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		imgLevel = (ImageView) findViewById(R.id.img_level);
		imgLevel.setImageLevel(2);

		imgAnim = (ImageView) findViewById(R.id.img_amim);

		imgCilp = (ImageView) findViewById(R.id.img_cilp);
		clipDrawable = (ClipDrawable) imgCilp.getBackground();
		
		imgRefresh=(ImageView) findViewById(R.id.img_wechat_refresh);
		
		animation=AnimationUtils.loadAnimation(this,R.animator.refresh_anim);
		imgRefresh.setAnimation(animation);

	}

	public void onClick_pressed(View view) {

		showAnim();
		new ClipDome().start();

	}

	/**
	 * ����֡�����Լ�����
	 * */
	@SuppressLint("NewApi")
	private void showAnim() {
		imgAnim.setBackground(null);
		imgAnim.setImageResource(R.drawable.anim_an);
		animationDrawable = (AnimationDrawable) imgAnim.getDrawable();
		int time = 0;
		for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
			time += animationDrawable.getDuration(i);
		}

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				/**
				 * ���ŽY��
				 * */
				imgAnim.setBackgroundColor(Color.RED);

			}
		}, time);

	}

	public void onClick_one(View view) {
		imgLevel.setImageLevel(2);

	}

	public void onClick_two(View view) {
		imgLevel.setImageLevel(4);

	}

	public void onClick_three(View view) {
		imgLevel.setImageLevel(7);

	}

	public void onClick_four(View view) {
		imgLevel.setImageLevel(10);

	}
	
	public void onClick_jump(View view){
		startActivity(new Intent(this,AnimActivity.class));
		
		
	}

	class ClipDome extends Thread {
		@Override
		public void run() {
			super.run();

			for (int i = 0; i < 21; i++) {
				mclip = i * 1000;
				Message message = new Message();
				message.arg1 = mclip;
				message.what = 1;
				handler.sendMessage(message);
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				handler.sendEmptyMessage(2);
			}
		}
	}

}
