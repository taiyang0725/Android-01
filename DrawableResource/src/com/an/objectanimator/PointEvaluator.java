package com.an.objectanimator;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class PointEvaluator implements TypeEvaluator<Object> {

	@Override
	public Object evaluate(float fraction, Object startValue, Object endValue) {

		Point startPoint = (Point) startValue;
		Point endPoint = (Point) endValue;

		float x = startPoint.getX() + fraction
				* (endPoint.getX() - startPoint.getX());

		float y = startPoint.getY() + fraction
				* (endPoint.getY() - startPoint.getY());
		Point point = new Point(x, y);
		return point;
	}

}
