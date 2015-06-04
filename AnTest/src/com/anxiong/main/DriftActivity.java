package com.anxiong.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anxiong.antest.R;

public class DriftActivity extends BaseActivity {

	private ImageView imgAir;
	private RelativeLayout layoutTitle, layoutUIDrift;
	private Animation animation;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:

				layoutTitle.setVisibility(View.GONE);

				break;
			case 2:
				setStatus();
				break;

			default:
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_drift);

		init();
	}

	private void init() {
		layoutTitle = (RelativeLayout) findViewById(R.id.layout_title);
		imgAir=(ImageView) findViewById(R.id.img_air_balloon);
		new HideView().start();
		
		
		animation=new TranslateAnimation(0,100, 0,200);
		animation.setDuration(20000);
		imgAir.setAnimation(animation);
		
		
		
		findViewById(R.id.layout_ui_drift).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						setVisibility(layoutTitle);
						new Thread(new Runnable() {

							@Override
							public void run() {

							}
						}).start();
					}
				});
	}

	class HideView extends Thread {
		@Override
		public void run() {
			try {
				sleep(1000);
				handler.sendEmptyMessage(1);
				sleep(500);
				handler.sendEmptyMessage(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			super.run();
		}

	}
	
	public void onClick_layout_pick_up(View view){
		
	}

}
