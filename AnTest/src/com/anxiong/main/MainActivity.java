package com.anxiong.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anxiong.antest.R;
import com.anxiong.fragment.BFragment.OnFromPicLocalListener;
import com.anxiong.fragment.CFragment.OnJumpToDriftListener;
import com.anxiong.fragment.MainFragment;
import com.anxiong.fragment.NetPicFragment;
import com.anxiong.fragment.NetPicFragment.OnFromMain0Listener;
import com.anxiong.fragment.PicLocalFragment;
import com.anxiong.fragment.PicLocalFragment.OnFromMainListener;
import com.anxiong.util.Tools;
import com.anxiong.view.MainPopupWindow;
import com.anxiong.zxing.avtivity.CaptureActivity;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class MainActivity extends BaseActivity implements
		OnFromPicLocalListener, OnFromMainListener, OnFromMain0Listener,
		OnJumpToDriftListener {

	private TextView txtTit;
	private ImageView imgBack;
	private ImageView imgPop;
	

	private ImageView imgSplit;

	private LinearLayout[] layout;
	private ImageView[] img;
	private TextView[] txt;

	private MainPopupWindow window;

	private Spinner spinner;

	private ProgressDialog dialog;
	
	/**
	 * 计数器，统计点击返回键的量
	 * */
	private int onClickBackCount;

	private LocationClient locationClient;
	public TextView txtLocalInfo;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				dialog.cancel();
				break;

			default:
				break;
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		initView();
		

	}

	private void initView() {

		layout = new LinearLayout[5];
		img = new ImageView[4];
		txt = new TextView[4];

		spinner = (Spinner) findViewById(R.id.spinner);

		
		txtTit = (TextView) findViewById(R.id.txt);
		imgBack = (ImageView) findViewById(R.id.img_back);
		imgPop = (ImageView) findViewById(R.id.img_pop);
		
		imgSplit=(ImageView) findViewById(R.id.img_split);

		imgPop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				window = new MainPopupWindow(MainActivity.this,
						new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								switch (position) {
								case 0:
									showLocal();
									window.dismiss();
									break;
								case 1:
									Intent openCameraIntent = new Intent(
											MainActivity.this,
											CaptureActivity.class);
									startActivityForResult(openCameraIntent, 0);
									window.dismiss();

									break;
								case 2:

									break;
								case 3:

									break;
								case 4:
									// setVisibility(spinner);
									spinner.setVisibility(View.VISIBLE);
									window.dismiss();
									break;
								case 5:
									jump(MainActivity.this,DrawableResourceActivity.class);
									window.dismiss();
									break;
								case 6:
									System.exit(0);
									window.dismiss();
									break;

								default:
									break;
								}

							}
						});
				window.showPopupWindow(imgPop);

			}
		});

		img[0] = (ImageView) findViewById(R.id.btn_a);
		img[1] = (ImageView) findViewById(R.id.btn_b);
		img[2] = (ImageView) findViewById(R.id.btn_c);
		img[3] = (ImageView) findViewById(R.id.btn_d);

		txt[0] = (TextView) findViewById(R.id.txt_a);
		txt[1] = (TextView) findViewById(R.id.txt_b);
		txt[2] = (TextView) findViewById(R.id.txt_c);
		txt[3] = (TextView) findViewById(R.id.txt_d);

		layout[0] = (LinearLayout) findViewById(R.id.a);
		layout[1] = (LinearLayout) findViewById(R.id.b);
		layout[2] = (LinearLayout) findViewById(R.id.c);
		layout[3] = (LinearLayout) findViewById(R.id.d);
		layout[4] = (LinearLayout) findViewById(R.id.include);

		replace(new MainFragment(layout, img, txt, txtTit, 0, spinner));

	}

	@Override
	public void fromPicLocal() {
		replace(new PicLocalFragment(txtTit, imgBack, layout, spinner));

	}

	@Override
	public void onFromMain() {

		imgBack.setVisibility(View.GONE);
		layout[4].setVisibility(View.VISIBLE);
		txtTit.setText("安兄");
		replace(new MainFragment(layout, img, txt, txtTit, 1, spinner));

	}

	@Override
	public void fromNet() {
		replace(new NetPicFragment(txtTit, imgBack, layout, spinner));

	}

	private void showLocal() {
		
		locationClient=((MainApplication)getApplication()).mlocationClient;
		txtLocalInfo=new TextView(MainActivity.this);
		
		((MainApplication)getApplication()).txtLocationResult=txtLocalInfo;

		configLocation();
		
		locationClient.start();
		
		new AlertDialog.Builder(this).setTitle("Where")
		        .setView(txtLocalInfo)
				.setPositiveButton("好的", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						locationClient.stop();

					}
				}).show();

	}
	
	@Override
	protected void onStop() {
		if (locationClient!=null){
			
			locationClient.stop();
		}
		super.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			new AlertDialog.Builder(this).setTitle("扫描结果")
					.setMessage(scanResult).setNegativeButton("I know", null)
					.show();
		}
	}

	@Override
	public void jumpToDrift() {
		
		jump(MainActivity.this, DriftActivity.class);

	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		onClickBackCount=0;
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public void finish() {
		onClickBackCount++;
		
		if(onClickBackCount==1){
			Tools.showHint(this, "再按一次退出！");
		}
		if(onClickBackCount==2){
			
			super.finish();
		}
	}
	/**
	 * 设置定位的参数
	 * */
	
	public void configLocation() {
		Tools.showLog("LocationClientOption option=new LocationClientOption();");
		LocationClientOption option=new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("gcj02");//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		locationClient.setLocOption(option);
	}

}
