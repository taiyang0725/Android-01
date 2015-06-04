package com.anxiong.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.anxiong.adapter.PopupAdapter;
import com.anxiong.antest.R;
import com.anxiong.mode.UIEntity;
import com.anxiong.util.Pic;

public class MainPopupWindow extends PopupWindow {
	private View conentView;
	private ListView lst;
	private ArrayList<UIEntity> list;
	

//	 /** ����¼��ص����� */
//	 public void setOnItemClick(OnItemClickListener OnItemClick) {
//	 this.OnItemClick = OnItemClick;
//	 }

	public MainPopupWindow( Activity context,
			OnItemClickListener OnItemClick) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		list = (ArrayList<UIEntity>) Pic.getPop(context);

		conentView = inflater.inflate(R.layout.activity_pop, null);
		lst = (ListView) conentView.findViewById(R.id.lst_popup);
		PopupAdapter adapter = new PopupAdapter(context, list);
		lst.setOnItemClickListener(OnItemClick);
		lst.setAdapter(adapter);

		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		// ����SelectPicPopupWindow��View

		this.setContentView(conentView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(w/3);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);

		this.setOutsideTouchable(true);

		// ˢ��״̬
		this.update();
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(android.R.color.holo_red_dark);
		// ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener �����������ؼ��仯�Ȳ���
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);
		// ����SelectPicPopupWindow�������嶯��Ч��
		// this.setAnimationStyle(R.style.);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent,-100, 5);
		} else {
			this.dismiss();
		}

	}
}
