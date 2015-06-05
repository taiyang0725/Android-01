package com.anxiong.tulingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String> {

	private HttpClient httpClient;
	private HttpGet httpGet;
	private String uri;
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	private String line;
	
	private HttpGetDataListener listener;
	
	

	public HttpData(String uri, HttpGetDataListener listener) {
		super();
		this.uri = uri;
		this.listener = listener;
	}

	@Override
	protected String doInBackground(String... params) {

		try {

			httpClient = (HttpClient) new DefaultHttpClient();
			httpGet = new HttpGet(uri);
			httpResponse = httpClient.execute(httpGet);

			httpEntity = httpResponse.getEntity();
			
			is = httpEntity.getContent();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			StringBuffer builder = new StringBuffer();

			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			
			return builder.toString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		listener.getData(result);
		super.onPostExecute(result);
	}

	

}
