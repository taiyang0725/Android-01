package com.anxiong.tulingtest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements HttpGetDataListener {

	private HttpData httpData;
	private List< DataEntity> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list=new ArrayList<DataEntity>();

		httpData = (HttpData) new HttpData(
				"http://www.tuling123.com/openapi/api?key=54cef338a3324c16a39d4343078b46ba&info=北京今天天气",
				this);
		httpData.execute();
	}
	
	

	@Override
	public void getData(String result) {
		parseJosn(result);
	}

	private void parseJosn(String data){
		try {
			JSONObject jo=new JSONObject(data);
			DataEntity dataEntity=new DataEntity();
			dataEntity.setText(jo.getString("text"));
			Tools.showHint(this,jo.getString("text"));
			list.add(dataEntity);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
}
