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
			showToast("�û�������Ϊ��......");
			
		}
		

		showLog(registerName + "  " + regPassword + "  " + regPasswordre);

		List<Person> regName = DataSupport.select("userName")
				.where("userName = ?", registerName).find(Person.class);
            System.out.println(regName);
			if (regName.size()!=0) {
				showToast("�û����Ѿ�����,����������......");
				System.out.println(regName.get(0).getUserName());
			} else {
				 
				if (!regPassword.equals(regPasswordre)) {
					showToast("�����������벻һ��!");
				} else {
					Person person = new Person();
					person.setUserName(registerName);
					person.setPassword(regPassword);
					person.save();
					showdialog("�]����");
					sleeps(500);
					progressDialog.dismiss();
					showToast("�]�Գɹ�!");

				}
			

		}

	}

}
