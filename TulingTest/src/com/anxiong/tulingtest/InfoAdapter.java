package com.anxiong.tulingtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class InfoAdapter extends BaseAdapter {
	
	private List<DataEntity> list;
	private Context context;
	private LayoutInflater inflater;
	private LinearLayout layout;
	
	public InfoAdapter(List<DataEntity> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(list.get(position).getFlag()==DataEntity.SEND_FLAG){
			layout=(LinearLayout) inflater.inflate(R.layout.leftitem,null);
		}
		if (list.get(position).getFlag()==DataEntity.RECEIVE_FLAG){
			layout=(LinearLayout) inflater.inflate(R.layout.rightitem,null);
		}
		return layout;
	}

}
