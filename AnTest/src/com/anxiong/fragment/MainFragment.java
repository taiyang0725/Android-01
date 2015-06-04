package com.anxiong.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anxiong.adapter.MyAdapter;
import com.anxiong.antest.R;
import com.anxiong.util.Pic;
import com.anxiong.viewpage.effect.AccordionTransformer;
import com.anxiong.viewpage.effect.CubeTransformer;
import com.anxiong.viewpage.effect.DefaultTransformer;
import com.anxiong.viewpage.effect.DepthPageTransformer;
import com.anxiong.viewpage.effect.InRightDownTransformer;
import com.anxiong.viewpage.effect.InRightUpTransformer;
import com.anxiong.viewpage.effect.RotateTransformer;
import com.anxiong.viewpage.effect.ZoomOutPageTransformer;

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment implements OnPageChangeListener,
		OnClickListener {

	private View view;

	private ViewPager pager;
	private List<Fragment> list;
	private MyAdapter adapter;

	private LinearLayout[] layout;
	private ImageView[] img;
	private TextView[] txt;
	private int pageNum;

	private int[] picNor;
	private int[] picFoc;

	private TextView txtTit;//

	private Spinner spinner;
	private ArrayAdapter<String> adapterSp;
	private String[] effectType;

	

	// private List<ImageView> mData = new ArrayList<ImageView>();

	public MainFragment() {
	}

	public MainFragment(LinearLayout[] layout, ImageView[] img, TextView[] txt,
			TextView txtTit, int pageNum, Spinner spinner) {
		super();
		this.layout = layout;
		this.img = img;
		this.txt = txt;
		this.txtTit = txtTit;
		this.pageNum = pageNum;
		this.spinner = spinner;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		picNor = Pic.getPicNor();
		picFoc = Pic.getPicFoc();
		
		effectType = new String[] { "默认", "深入浅出", "立方体", "旋转","左右折叠", "右上角进入" , "右下角进入", "淡入淡出" };

		list = new ArrayList<Fragment>();
		list.add(new AFragment());
		list.add(new BFragment());
		list.add(new CFragment());
		list.add(new DFragment());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_base, null);
		initSpinner();

		return view;

	}

	private void initSpinner() {

		adapterSp = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, effectType);
		adapterSp
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapterSp);
		spinner.setOnItemSelectedListener(ISelectedListener);

		initViewPage();

		for (int i = 0; i < layout.length; i++) {
			layout[i].setOnClickListener(this);
		}

		backPage(pageNum);
	}

	private void initViewPage() {
		pager = (ViewPager) view.findViewById(R.id.viewPage);
		pager.setOnPageChangeListener(this);

		adapter = new MyAdapter(getChildFragmentManager(), list);
		pager.setAdapter(adapter);
		// pager.setCurrentItem(mData.size() / 2);
//		pager.setPageTransformer(true, null);

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {	
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		
		spinner.setVisibility(View.GONE);
		backPage(arg0);
		
		

	}

	private void backPage(int index) {

		pager.setCurrentItem(index);

		switch (index) {
		case 0:
			setImgBg(img, 0);
			setTxtColor(txt, 0);

			break;
		case 1:
			setImgBg(img, 1);
			setTxtColor(txt, 1);

			break;
		case 2:
			setImgBg(img, 2);
			setTxtColor(txt, 2);

			break;
		case 3:
			setImgBg(img, 3);
			setTxtColor(txt, 3);
			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		spinner.setVisibility(View.GONE);
		switch (v.getId()) {
		case R.id.a:
			pager.setCurrentItem(0);
			setImgBg(img, 0);
			setTxtColor(txt, 0);
			break;
		case R.id.b:
			pager.setCurrentItem(1);
			setImgBg(img, 1);
			setTxtColor(txt, 1);
			break;
		case R.id.c:
			pager.setCurrentItem(2);
			setImgBg(img, 2);
			setTxtColor(txt, 2);
			break;
		case R.id.d:
			pager.setCurrentItem(3);
			setImgBg(img, 3);
			setTxtColor(txt, 3);
			break;

		default:
			break;
		}

	}

	public void setImgBg(ImageView[] img, int i) {
		img[i].setBackgroundResource(picFoc[i]);
		for (int j = 0; j < img.length; j++) {
			if (j != i) {
				img[j].setBackgroundResource(picNor[j]);
			}
		}

	}

	public void setTxtColor(TextView[] txt, int i) {
		txt[i].setTextColor(Color.parseColor("#45C01A"));

		for (int j = 0; j < txt.length; j++) {
			if (j != i) {
				txt[j].setTextColor(Color.parseColor("#444444"));
			}

		}

	}

	OnItemSelectedListener ISelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
//			initViewPage();
			switch (position) {
			case 0:
				pager.setPageTransformer(true, new DefaultTransformer());

				break;
			case 1:
				pager.setPageTransformer(true, new DepthPageTransformer());

				break;
			case 2:
				pager.setPageTransformer(true, new CubeTransformer());

				break;
			case 3:
				pager.setPageTransformer(true, new RotateTransformer());

				break;
			case 4:
				pager.setPageTransformer(true, new AccordionTransformer());

				break;
			case 5:
				pager.setPageTransformer(true, new InRightUpTransformer());

				break;
			case 6:
				pager.setPageTransformer(true, new InRightDownTransformer());

				break;
			case 7:
				pager.setPageTransformer(true, new ZoomOutPageTransformer());

				break;
			default:
				break;

			}

			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}

	};

}
