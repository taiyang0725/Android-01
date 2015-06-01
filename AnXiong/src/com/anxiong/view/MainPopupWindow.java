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
import com.anxiong.mode.UIEntity;
import com.anxiong.util.Pic;
import com.example.testviewpage.R;

public class MainPopupWindow extends PopupWindow {
	private View conentView;
	private ListView lst;
	private ArrayList<UIEntity> list;
	

//	 /** 点击事件回调方法 */
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
		// 设置SelectPicPopupWindow的View

		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(w / 3);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);

		this.setOutsideTouchable(true);

		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(android.R.color.holo_red_dark);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		this.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		// this.setAnimationStyle(R.style.);

	}

	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			this.showAsDropDown(parent, parent.getLayoutParams().width / 3, 14);
		} else {
			this.dismiss();
		}

	}
}
