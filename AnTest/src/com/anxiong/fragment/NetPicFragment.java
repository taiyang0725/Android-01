package com.anxiong.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.anxiong.antest.R;
import com.anxiong.util.Pic;
import com.anxiong.util.Tools;

@SuppressLint("ValidFragment")
public class NetPicFragment extends BaseFragment {

	private View view;

	private EditText edt;
	private ImageView img, img1, imgNew;
	private NetworkImageView imgNetWork;

	private ProgressBar bar;

	private TextView txtTit;
	private ImageView imgBack;

	private LinearLayout[] layout;

	private OnFromMain0Listener callBack;
	private Spinner spinner;

	private String url;

	private ListView listView;
	private String[] path;
	private Boolean isDown;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (listView != null) {
					listView.setAdapter(new VolleyAdapter());
				}
			}

		};

	};

	public interface OnFromMain0Listener {

		void onFromMain();
	}

	public NetPicFragment(TextView txtTit, ImageView imgBack,
			LinearLayout[] layout, Spinner spinner) {
		super();
		this.txtTit = txtTit;
		this.imgBack = imgBack;

		this.layout = layout;
		this.spinner = spinner;
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

		path = Pic.getImagePath();
		isDown = false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// view = inflater.inflate(R.layout.net_pic, null);
		view = inflater.inflate(R.layout.volley_picture, null);
		initListView();
		return view;
	}

	private void initListView() {

		spinner.setVisibility(View.GONE);

		imgBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				callBack.onFromMain();

			}
		});

		view.findViewById(R.id.onClick_downLoader_picture).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (isDown) {
							isDown = false;
						} else if (!isDown) {
							isDown = true;
							handler.sendEmptyMessage(1);
						}

					}
				});

		listView = (ListView) view.findViewById(R.id.lst_volley);
		listView.setAdapter(new VolleyAdapter());

	}

	class VolleyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return path.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return path[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			imgNew = new ImageView(getActivity());

			volley_ImageLoader(path[position]);
			return imgNew;
		}

	}

	private void init() {

		spinner.setVisibility(View.GONE);

		edt = (EditText) view.findViewById(R.id.edt_net);
		bar = (ProgressBar) view.findViewById(R.id.progressBar1);
		img = (ImageView) view.findViewById(R.id.img_net);

		img1 = (ImageView) view.findViewById(R.id.img_net1);

		imgNetWork = (NetworkImageView) view
				.findViewById(R.id.network_image_view);

		url = edt.getText().toString();
		view.findViewById(R.id.btn_net).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// new PicDown().execute(edt.getText().toString());

						volley_ImageRequest(edt.getText().toString());
						volley_ImageLoader(edt.getText().toString());
						volley_networkImageView(edt.getText().toString());

						edt.setEnabled(false);

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

	/**
	 * Volley框架
	 * */

	/**
	 * ImageRequest的用法 1创建一个RequestQueue对象。 2. 创建一个Request对象。 3.
	 * 将Request对象添加到RequestQueue里面。
	 * 
	 * */
	@SuppressWarnings("unused")
	private void volley_ImageRequest(String path) {

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

		ImageRequest request = new ImageRequest(path,
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						img1.setImageBitmap(response);
						Tools.showHint(getActivity(), "成功了");

					}
				}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Tools.showHint(getActivity(), "失败了");
					}
				});

		requestQueue.add(request);

	}

	/**
	 * ImageLoader的用法 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 获取一个ImageListener对象。
	 * 
	 * 4. 调用ImageLoader的get()方法加载网络上的图片。
	 * 
	 * */

	private void volley_ImageLoader(String path) {

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

		ImageLoader imageLoader = new ImageLoader(requestQueue,
				new BitmapCache());

		@SuppressWarnings("static-access")
		ImageListener imageListener = imageLoader.getImageListener(imgNew,
				R.drawable.default_headphoto_icon, R.drawable.sorry);

		imageLoader.get(path, imageListener);

	}

	private class BitmapCache implements ImageLoader.ImageCache {

		private LruCache<String, Bitmap> cache;

		public BitmapCache() {
			int maxSize = 1024 * 1024 * 10;

			cache = new LruCache<String, Bitmap>(maxSize) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		@Override
		public Bitmap getBitmap(String url) {
			return cache.get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			cache.put(url, bitmap);

		}

	}

	/**
	 * NetworkImageView的用法 1. 创建一个RequestQueue对象。
	 * 
	 * 2. 创建一个ImageLoader对象。
	 * 
	 * 3. 在布局文件中添加一个NetworkImageView控件。
	 * 
	 * 4. 在代码中获取该控件的实例。
	 * 
	 * 5. 设置要加载的图片地址
	 * */
	public void volley_networkImageView(String path) {

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

		ImageLoader imageLoader = new ImageLoader(requestQueue,
				new BitmapCache());

		imgNetWork.setDefaultImageResId(R.drawable.default_headphoto_icon);
		imgNetWork.setErrorImageResId(R.drawable.sorry);
		imgNetWork.setImageUrl(path, imageLoader);

	}

	/**
	 * 获取原图片和需求图片大小的比率
	 * 
	 * */
	public int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 根据比率获取相应的图片
	 * */

	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;// 系統是否自动分配内存
		BitmapFactory.decodeResource(res, resId, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

}
