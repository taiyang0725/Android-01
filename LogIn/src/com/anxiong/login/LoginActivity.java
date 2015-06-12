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
			showToast("��½���˺Ų���ȷ�򲻴���!");
		} else {
			List<Person> listPassword = DataSupport
					.select("password")
					.where("userName = ? and password = ?", loginName,
							loginPassword).find(Person.class);
			if (listPassword.size() == 0) {
				showToast("���벻��ȷ!");
			} else {
				System.out.println(listPassword.get(0).getPassword());
				showToast("��ꑳɹ�!");
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
			showToast("��½���˺Ų���ȷ�򲻴���!");
		} else {
			List<Person> listPassword = DataSupport.select("password")
					.where("userName = ? ", loginName).find(Person.class);

			new AlertDialog.Builder(this)
					.setTitle("��ϸ��Ϣ")
					.setMessage(
							"�û���:" + listUserName.get(0).getUserName() + "\n"
									+ "����:" + listPassword.get(0).getPassword())
					.setNegativeButton("�õ�", null).show();

		}

	}

	public void onClick_login_register(View view) {

		hideView();
		layout_register.setVisibility(View.VISIBLE);

	}

}
