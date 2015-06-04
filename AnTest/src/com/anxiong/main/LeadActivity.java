package com.anxiong.main;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.anxiong.antest.R;

public class LeadActivity extends BaseActivity {
	
	private ImageView img;
	private Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_lead);
		
		img=(ImageView) findViewById(R.id.img_lead);
		
		animation=AnimationUtils.loadAnimation(this, R.anim.lead_anim);
		img.setAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				finish();
				jump(getApplication(), MainActivity.class);
				
			}
		});
		
		
	}
	
	
	class Jump extends Thread{
		
		@Override
		public void run() {
			super.run();
			
			try {
				sleep(5000);
				finish();
				jump(getApplication(), MainActivity.class);
				
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
