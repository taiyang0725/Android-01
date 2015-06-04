package com.anxiong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anxiong.antest.R;



public class AFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return setLayout();
	}

	private View setLayout(){
		RelativeLayout layout=new RelativeLayout(getActivity());
		
		DisplayMetrics dm=new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int screenH=dm.heightPixels;
		
		RelativeLayout.LayoutParams p1=new RelativeLayout.LayoutParams
				(RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams p2=new RelativeLayout.LayoutParams
				(RelativeLayout.LayoutParams.MATCH_PARENT,
						(int) (screenH*0.8));
		RelativeLayout.LayoutParams p3=new RelativeLayout.LayoutParams
				(RelativeLayout.LayoutParams.MATCH_PARENT,
						(int) (screenH*0.1));
		
		layout.setLayoutParams(p1);
		
		ImageView img=new ImageView(getActivity());
		img.setLayoutParams(p2);
		img.setBackgroundResource(R.drawable.ae);
		layout.addView(img);
		
		Button btn=new Button(getActivity());
		btn.setLayoutParams(p3);
		
		btn.setText("ͼƬ");
		btn.setTextColor(Color.parseColor("#FFFFFF"));
		btn.setBackgroundColor(Color.parseColor("#FF0000"));
		
//		layout.addView(btn);
		return layout;
		
		
	}
}
