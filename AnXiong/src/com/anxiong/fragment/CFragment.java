package com.anxiong.fragment;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.anxiong.main.MainActivity;
import com.anxiong.util.Tools;
import com.example.testviewpage.R;

public class CFragment extends BaseFragment {

	private View view;
	private LinearLayout linearLayout;
	private ImageView img;
	
	private OnJumpToDriftListener jumpToDriftListener;
	
	public interface OnJumpToDriftListener{
		
		void jumpToDrift();
		
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		jumpToDriftListener=(OnJumpToDriftListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.c, null);
        initView();
		return view;
	}

	private void initView() {
		view.findViewById(R.id.layout_drift).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTargetFragment();
				jumpToDriftListener.jumpToDrift();
				
			}
		});
		
	}

	private void init() {

		img = (ImageView) view.findViewById(R.id.img);

		linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
		linearLayout.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Tools.showLog("action_down");

					break;
				case MotionEvent.ACTION_MOVE:
					Tools.showLog("action_move");
					LayoutParams l = (LayoutParams) img.getLayoutParams();
					l.leftMargin = (int) event.getX();
					l.topMargin = (int) event.getY();
					img.setLayoutParams(l);
					Tools.showLog(String.format("x:%f, y:%f", event.getX(),
							event.getY()));

					break;
				case MotionEvent.ACTION_UP:
					Tools.showLog("action_up");

					break;

				default:
					break;
				}
				return true;
			}
		});

		img.setOnClickListener(

		new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Tools.showLog("我点击了");

				Intent intent = new Intent(getActivity(), MainActivity.class);
				PendingIntent intents = PendingIntent.getActivity(
						getActivity(), 0, intent, 0);

				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						getActivity());
				builder.setSmallIcon(R.drawable.alf);
				builder.setContentTitle("来自大荒山");
				builder.setContentText("绛珠仙草面世了.......");
				builder.setContentInfo("林黛玉");
				builder.setContentIntent(intents);
				builder.setWhen(50000);
				Notification notification = builder.build();
				NotificationManager notificationManager = (NotificationManager) getActivity()
						.getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.notify(R.id.img, notification);
				// notificationManager.cancel(R.id.btn_notifi);

			}
		});

	}


}
