package com.anxiong.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.anxiong.antest.R;
import com.anxiong.mode.UIEntity;

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
				R.drawable.ao6, R.drawable.ui, R.drawable.a9l,
				R.drawable.icon_002 };
		String[] txt = context.getResources().getStringArray(R.array.pop);

		for (int i = 0; i < txt.length; i++) {
			UIEntity u = new UIEntity();
			u.setIcon(icon[i]);
			u.setName(txt[i]);
			list.add(u);

		}

		return list;

	}

	public static String[] getImagePath() {
		String[] path = {
				"http://g.hiphotos.baidu.com/image/pic/item/f7246b600c33874455009062530fd9f9d62aa0e3.jpg",
				"http://d.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f9432aa7e1d30e924b899f345.jpg",
				"http://a.hiphotos.baidu.com/image/pic/item/d01373f082025aaf2c0f2dd6f9edab64034f1ae9.jpg",
				"http://h.hiphotos.baidu.com/image/pic/item/b151f8198618367a5d9dceb62c738bd4b31ce541.jpg",
				"http://e.hiphotos.baidu.com/image/pic/item/1f178a82b9014a90e2449db4ab773912b31bee45.jpg",
				"http://d.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e932054f94ba0e7bec54e7976b.jpg",
				"http://h.hiphotos.baidu.com/image/pic/item/72f082025aafa40fbc9b03d4a964034f78f0195e.jpg",
				"http://c.hiphotos.baidu.com/image/pic/item/aa18972bd40735fa948c15229c510fb30f240828.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
				"http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
				"http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg" };
		
		return path;

	}

}
