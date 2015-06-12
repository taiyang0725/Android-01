package com.anxiong.login;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.anxiong.db.Person;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		txtFindpassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				find_password();
			}
		});
	}

	public void onClick_login(View view) {

		showLog("loginName:" + loginName + ",loginPassword:" + loginPassword);

		loginName = edtLoginUserName.getText().toString();
		loginPassword = edtLoginPassword.getText().toString();

		List<Person> listUserName = DataSupport.select("userName")
				.where("userName=?", loginName).find(Person.class);

		if (listUserName.size() == 0) {
			showToast("登陆的账号不正确或不存在!");
		} else {
			List<Person> listPassword = DataSupport
					.select("password")
					.where("userName = ? and password = ?", loginName,
							loginPassword).find(Person.class);
			if (listPassword.size() == 0) {
				showToast("密码不正确!");
			} else {
				System.out.println(listPassword.get(0).getPassword());
				showToast("登陸成功!");
				hideView();
				layout_main.setVisibility(View.VISIBLE);
			}

		}

	}

	public void find_password() {

		loginName = edtLoginUserName.getText().toString();
		
		List<Person> listUserName = DataSupport.select("userName")
				.where("userName=?", loginName).find(Person.class);

		if (listUserName.size() == 0) {
			showToast("登陆的账号不正确或不存在!");
		} else {
			List<Person> listPassword = DataSupport.select("password")
					.where("userName = ? ", loginName).find(Person.class);

			new AlertDialog.Builder(this)
					.setTitle("详细信息")
					.setMessage(
							"用户名:" + listUserName.get(0).getUserName() + "\n"
									+ "密码:" + listPassword.get(0).getPassword())
					.setNegativeButton("好的", null).show();

		}

	}

	public void onClick_login_register(View view) {

		hideView();
		layout_register.setVisibility(View.VISIBLE);

	}

}
