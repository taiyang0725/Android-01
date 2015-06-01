package com.anxiong.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.anxiong.mode.UIEntity;
import com.example.testviewpage.R;

public class Pic {

	public static int[] getPicNor() {
		int[] bg_normal = { R.drawable.alc, R.drawable.ala, R.drawable.ale,
				R.drawable.alg };

		return bg_normal;

	}

	public static int[] getPicFoc() {

		int[] bg_focus = { R.drawable.alb, R.drawable.al_, R.drawable.ald,
				R.drawable.alf };
		return bg_focus;

	}

	public static List<UIEntity> getPop(Context context) {

		List<UIEntity> list = new ArrayList<UIEntity>();

		int[] icon = { R.drawable.amz, R.drawable.anm, R.drawable.ao0,
				R.drawable.ao6 ,R.drawable.ui,R.drawable.a9l,R.drawable.icon_002};
		String[] txt = context.getResources().getStringArray(R.array.pop);

		for (int i = 0; i < txt.length; i++) {
			UIEntity u = new UIEntity();
			u.setIcon(icon[i]);
			u.setName(txt[i]);
			list.add(u);

		}

		return list;

	}

}
