package com.an.objectanimator.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.an.objectanimator.Point;
import com.an.objectanimator.PointEvaluator;

@SuppressLint("NewApi")
public class AaimView extends View {

	private static final float RADIUS = 50F;// СԲ�뾶

	private Point currentPoint;

	private Paint paint;

	public AaimView(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (currentPoint == null) {
			currentPoint = new Point(RADIUS, RADIUS);
			drawCircle(canvas);
			startAnimation();
		} else {
			drawCircle(canvas);
		}

	}

	private void drawCircle(Canvas canvas) {

		float x = currentPoint.getX();
		float y = currentPoint.getY();
		canvas.drawCircle(x, y, RADIUS, paint);

	}

	private void startAnimation() {
		Point startPoint = new Point(RADIUS, RADIUS);
		Point rightPoint = new Point(getWidth() - RADIUS, RADIUS);
		Point belowPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
		Point leftPoint = new Point(RADIUS,getHeight() - RADIUS);
		Point upPoint = new Point(RADIUS,RADIUS);

		ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),
				startPoint, rightPoint, belowPoint, leftPoint, upPoint);

		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				currentPoint = (Point) animation.getAnimatedValue();
				invalidate();

			}
		});
		animator.setDuration(5000);
		animator.start();
		

	}
}
