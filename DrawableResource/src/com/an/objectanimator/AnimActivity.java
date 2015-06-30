package com.an.objectanimator;

import com.anxiong.drawableresource.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AnimActivity extends Activity {

	private TextView txt;
	private ObjectAnimator animator;
	
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_anim);

		txt = (TextView) findViewById(R.id.txt);
		img=(ImageView) findViewById(R.id.img);
		
		
	}

	/**
	 * ͸����
	 * */
	public void onClick_alpha(View view) {
		animator = ObjectAnimator.ofFloat(txt, "alpha", 1f, 0f, 1f);
		animator.setDuration(5000);
		animator.start();

	}

	/**
	 * �ƶ�
	 * */
	public void onClick_translationX(View view) {
		float currentTxtX = txt.getTranslationX();
		animator = ObjectAnimator.ofFloat(txt, "translationX", currentTxtX,
				-500f, currentTxtX);
		animator.setDuration(5000);
		animator.start();

	}

	/**
	 * ����
	 * */
	public void onClick_scaleY(View view) {
		animator = ObjectAnimator.ofFloat(txt, "scaleY", 1f, 4f, 1f);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * ��ת
	 * */
	public void onClick_rotation(View view) {
		animator = ObjectAnimator.ofFloat(txt, "rotation", 0f, 360f);
		animator = ObjectAnimator.ofFloat(img, "rotation", 0f, 360f);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * ��϶��� 
	 * after(Animator anim) �����ж������뵽����Ķ���֮��ִ�У�
	 *  after(long delay) �����ж����ӳ�ָ�������ִ�� 
	 * before(Animator anim) �����ж������뵽����Ķ���֮ǰִ�� 
	 * with(Animator anim) �����ж����ʹ���Ķ���ͬʱִ��
	 * 
	 * */
	public void onClick_groupAnim(View view) {
		
		ObjectAnimator moveIn = ObjectAnimator.ofFloat(txt, "translationX", -500f, 0f);
		ObjectAnimator rotate = ObjectAnimator.ofFloat(txt, "rotation", 0f, 360f);
		ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(txt, "alpha", 1f, 0f, 1f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(rotate).with(fadeInOut).after(moveIn);
		animSet.setDuration(5000);
		animSet.start();
		moveIn.addListener(new AnimatorListenerAdapter(){
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				
				startActivity(new Intent(AnimActivity.this,MoveActivity.class));
			}
			
		});

	}

}
