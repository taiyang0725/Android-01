package com.anxiong.tulingtest;

public class DataEntity {
	
	public static final int SEND_FLAG=1;
	public static final int RECEIVE_FLAG=2;
	
	private String text;
	private int flag;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
