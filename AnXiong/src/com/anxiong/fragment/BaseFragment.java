package com.anxiong.fragment;

import android.support.v4.app.Fragment;
import android.view.View;



public class BaseFragment extends Fragment {
	
	public void setVisibility(View v){
		switch (v.getVisibility()) {
		case View.GONE:
			v.setVisibility(View.VISIBLE);
		case View.VISIBLE:
			v.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		
	}
	
	

}
