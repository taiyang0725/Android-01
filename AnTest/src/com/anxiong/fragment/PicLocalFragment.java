package com.anxiong.fragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.anxiong.adapter.PicAdapter;
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
public class PicLocalFragment extends BaseFragment implements OnClickListener,
		OnPageChangeListener {

	private View view;
	private ViewPager pager;
	private String[] lst;

	private TextView txtTit;
	private ImageView imgBack;

	private LinearLayout[] layout;
	private OnFromMainListener callBack;

	private static final int PHOTO_GALLERY = 1;

	private Spinner spinner;
	private ArrayAdapter<String> adapterSp;
	private String[] effectType;
	
	private List<Bitmap> list;

	public PicLocalFragment(TextView txtTit, ImageView imgBack,
			LinearLayout[] layout, Spinner spinner) {
		super();
		this.txtTit = txtTit;
		this.imgBack = imgBack;
		this.spinner = spinner;
		this.layout = layout;
		this.spinner = spinner;
	}

	public interface OnFromMainListener {

		void onFromMain();
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		try {
			callBack = (OnFromMainListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFromMainListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		txtTit.setText("图片来自本地");

		imgBack.setVisibility(View.VISIBLE);
		layout[4].setVisibility(View.GONE);

		imgBack.setOnClickListener(this);

		lst = Pic.getImagePath();
		
		list=new ArrayList<Bitmap>();
		effectType = new String[] { "默认", "深入浅出", "立方体", "旋转", "左右折叠", "右上角进入",
				"右下角进入", "淡入淡出" };
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.pic, null);

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

	}

	private void initViewPage() {

		pager = (ViewPager) view.findViewById(R.id.pic_page);
		PicAdapter pAdapter = new PicAdapter(lst, getActivity());
		pager.setAdapter(pAdapter);
		pager.setOnPageChangeListener(this);

	}

	@Override
	public void onClick(View v) {
		callBack.onFromMain();

	}

	OnItemSelectedListener ISelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			initViewPage();
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

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		spinner.setVisibility(View.GONE);

	}

	private void getLocalPicture() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);// 从列表中选择某项并返回所有数据
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
				"image/*");
		startActivityForResult(intent, PHOTO_GALLERY);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case PHOTO_GALLERY:
			// 图片信息需包含在返回数据中
			String[] proj = { MediaStore.Images.Media.DATA };
			// 获取包含所需数据的Cursor对象
			Cursor cursor = getActivity().managedQuery(data.getData(), proj,
					null, null, null);
			// 获取索引
			int photocolumn = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			// 将光标一直开头
			cursor.moveToFirst();
			// 根据索引值获取图片路径
			String path = cursor.getString(photocolumn);
            compressPhoto(path);
			break;

		default:
			break;
		}

	}

	private void compressPhoto(String path) {
		try {
			
			FileInputStream fis=new FileInputStream(path);
			Bitmap bitmap=BitmapFactory.decodeStream(fis);
			list.add(bitmap);
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
