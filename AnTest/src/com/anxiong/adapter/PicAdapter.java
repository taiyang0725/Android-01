package com.anxiong.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.anxiong.antest.R;

public class PicAdapter extends PagerAdapter{
	
	
	private String [] lst;
	private Context context;
	
	private LayoutInflater inflater;
	
	private ImageView img;
	
	private int index;

	public PicAdapter(String [] lst, Context context) {
		super();
		this.lst = lst;
		this.context = context;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lst.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0.equals(arg1);
	}
	
	@Override
	public Object instantiateItem(View container,  int position) {
		
		View view=inflater.inflate(R.layout.local_pic, null);
		img=(ImageView) view.findViewById(R.id.img_pic);
				
		volley_ImageLoader(lst[position]);
		
		((ViewGroup) container).addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		
//		container.removeView((View) object);
	}
	private void volley_ImageLoader(String path) {

		RequestQueue requestQueue = Volley.newRequestQueue(context);

		ImageLoader imageLoader = new ImageLoader(requestQueue,new BitmapCache());
				
		@SuppressWarnings("static-access")
		ImageListener imageListener = imageLoader.getImageListener(img,
				R.drawable.default_headphoto_icon, R.drawable.sorry);

		imageLoader.get(path, imageListener);

	}

	private class BitmapCache implements ImageLoader.ImageCache {
		
		private LruCache<String, Bitmap> cache;
		
		public BitmapCache(){
			int maxSize=1024*1024*10;
			
			cache=new LruCache<String, Bitmap>(maxSize){
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					return bitmap.getRowBytes()*bitmap.getHeight();
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
	
	

}
