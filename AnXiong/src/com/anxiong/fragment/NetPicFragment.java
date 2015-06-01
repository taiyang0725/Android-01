package com.anxiong.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.anxiong.util.Tools;
import com.example.testviewpage.R;

@SuppressLint("ValidFragment")
public class NetPicFragment extends BaseFragment {

	private View view;

	private EditText edt;
	private ImageView img;

	private ProgressBar bar;

	private TextView txtTit;
	private ImageView imgBack;
	
	private LinearLayout[] layout;

	private OnFromMain0Listener callBack;
	private Spinner spinner;

	public interface OnFromMain0Listener {

		void onFromMain();
	}

	public NetPicFragment(TextView txtTit,ImageView imgBack,LinearLayout[] layout,Spinner spinner) {
		super();
		this.txtTit = txtTit;
		this.imgBack=imgBack;
		
		this.layout=layout;
		this.spinner=spinner;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		txtTit.setText("图片来自网络");
		imgBack.setVisibility(View.VISIBLE);
		layout[4].setVisibility(View.GONE);

		try {
			callBack = (OnFromMain0Listener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFromMainListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.net_pic, null);
		init();
		return view;
	}

	private void init() {
		
		spinner.setVisibility(View.GONE);

		edt = (EditText) view.findViewById(R.id.edt_net);
		bar = (ProgressBar) view.findViewById(R.id.progressBar1);
		img = (ImageView) view.findViewById(R.id.img_net);
		view.findViewById(R.id.btn_net).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new PicDown().execute(edt.getText().toString());
						edt.setEnabled(true);

					}
				});

		view.findViewById(R.id.btn_net_cancel).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new PicDown().cancel(true);

					}
				});

		imgBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMain();

			}
		});

	}

	class PicDown extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			bar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			InputStream stream = null;

			try {

				String path = params[0];
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();

				stream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(stream);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}

			return bitmap;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			bar.setProgress(values[0]);
			Tools.showHint(getActivity(), "进度" + values[0]);
			Tools.showLog("进度" + values[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			img.setImageBitmap(result);
			bar.setVisibility(View.GONE);

		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

}
