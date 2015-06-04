package com.anxiong.main;

import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.anxiong.util.Tools;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class MainApplication extends Application {

	public LocationClient mlocationClient;
	public TextView txtLocationResult;
	public MyLocationListener myLocationListener;
	
	
	/**
	 * 震动
	 * */
	public Vibrator vibrator;

	@Override
	public void onCreate() {
		super.onCreate();

		mlocationClient = new LocationClient(this.getApplicationContext());
		myLocationListener = new MyLocationListener();
		mlocationClient.registerLocationListener(myLocationListener);

		vibrator = (Vibrator) getApplicationContext().getSystemService(
				Context.VIBRATOR_SERVICE);
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			logMsg(sb.toString());
			Log.i("BaiduLocationApiDem", sb.toString());
		}

	}

	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg(String str) {
		Tools.showHint(getApplicationContext(), "我在哪里呢？");
//		if (txtLocationResult != null)
			    txtLocationResult.setTextSize(35f);
				txtLocationResult.setText("我在:"+str);
		
	}
}
