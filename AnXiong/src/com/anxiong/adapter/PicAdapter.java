package com.anxiong.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testviewpage.R;

public class PicAdapter extends PagerAdapter{
	
	
	private int[] lst;
	private Context context;
	
	private LayoutInflater inflater;

	public PicAdapter(int[] lst, Context context) {
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
	public Object instantiateItem(View container, int position) {
		
		View view=inflater.inflate(R.layout.local_pic, null);
		view.findViewById(R.id.img_pic).setBackgroundResource(lst[position]);
		((ViewGroup) container).addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		
//		container.removeView((View) object);
	}
}
