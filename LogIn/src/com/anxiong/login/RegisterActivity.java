package com.anxiong.login;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.os.Bundle;
import android.view.View;

import com.anxiong.db.Person;

public class RegisterActivity extends LoginActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("++++---------------------------------------------");
	}

	public void onClick_register_register(View view) {
		
		registerName = edtRegisterUserName.getText().toString();
		regPassword = edtRegisterPassword.getText().toString();
		regPasswordre = edtRegisterPassword_again.getText().toString();
		
		if(registerName.equals("")){
			showToast("用户名不能为空......");
			
		}
		

		showLog(registerName + "  " + regPassword + "  " + regPasswordre);

		List<Person> regName = DataSupport.select("userName")
				.where("userName = ?", registerName).find(Person.class);
            System.out.println(regName);
			if (regName.size()!=0) {
				showToast("用户名已经存在,请重新输入......");
				System.out.println(regName.get(0).getUserName());
			} else {
				 
				if (!regPassword.equals(regPasswordre)) {
					showToast("两次输入密码不一致!");
				} else {
					Person person = new Person();
					person.setUserName(registerName);
					person.setPassword(regPassword);
					person.save();
					showToast("註冊成功!");
					hideView();
					layout_main.setVisibility(View.VISIBLE);

				}
			

		}

	}

}
