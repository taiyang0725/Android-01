package com.anxiong.login;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.view.View;

import com.anxiong.db.Person;

public class RegisterActivity extends LoginActivity {
	

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
					showdialog("註冊中");
					sleeps(500);
					progressDialog.dismiss();
					showToast("註冊成功!");

				}
			

		}

	}

}
