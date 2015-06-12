package com.anxiong.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	/**
	 * 登陸界面
	 * */
	protected LinearLayout layout_login;
	protected EditText edtLoginUserName;
	protected EditText edtLoginPassword;
	protected String loginName;
	protected String loginPassword;

	protected TextView txtFindpassword;

	/**
	 * 注册界面
	 * */

	protected LinearLayout layout_register;
	protected EditText edtRegisterUserName;
	protected EditText edtRegisterPassword;
	protected EditText edtRegisterPassword_again;
	protected String registerName;
	protected String regPassword;
	protected String regPasswordre;

	protected LinearLayout layout_main;

	protected ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout_login = (LinearLayout) findViewById(R.id.include_login);
		edtLoginUserName = (EditText) findViewById(R.id.edt_log_in);
		edtLoginPassword = (EditText) findViewById(R.id.edt_log_in_password);

		txtFindpassword = (TextView) findViewById(R.id.txt_find_password);

		layout_register = (LinearLayout) findViewById(R.id.include_register);
		edtRegisterUserName = (EditText) findViewById(R.id.edt_register_user);
		edtRegisterPassword = (EditText) findViewById(R.id.edt_register_password);
		edtRegisterPassword_again = (EditText) findViewById(R.id.edt_register_password_re);

		layout_main = (LinearLayout) findViewById(R.id.include_main);

		hideView();
		layout_login.setVisibility(View.VISIBLE);

		// Person person=new Person();
		// person.setUserName("wangan");
		// person.setPassword("123456");
		// person.save();

	}

	protected void querydata() {

	}

	protected void hideView() {

		layout_login.setVisibility(View.GONE);
		layout_register.setVisibility(View.GONE);
		layout_main.setVisibility(View.GONE);

	}

	protected void sleeps(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	protected void showLog(String text) {
		Log.d("AnXiong", text);
	}

	protected void showdialog(String text) {
		progressDialog = new ProgressDialog(this).show(this, text, "等待......");
	}
}
