package com.anxiong.fragment;


import com.example.testviewpage.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment") 
public class BFragment extends BaseFragment {

	private View view;

	private String txt;
	
	private OnFromPicLocalListener callBack;
	
	private TextView txtTit;
	private TextView txtBack;
	
	public BFragment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BFragment(TextView txtTit, TextView txtBack) {
		super();
		this.txtTit = txtTit;
		this.txtBack = txtBack;
	}

	

	
	public interface OnFromPicLocalListener{
		void fromPicLocal();
		void fromNet();
		
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callBack =  (OnFromPicLocalListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFromPicLocalListener");
		}
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);


	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.b, null);

		init();
		return view;
	}

	private void init() {
		
		view.findViewById(R.id.btn_pic_net).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				callBack.fromNet();
				
			}
		});
		
		view.findViewById(R.id.btn_pic_local).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				callBack.fromPicLocal();
				
			}
		});
		
	}


}
