package com.anxiong.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import android.os.Handler;
import android.os.Message;

public class ReadTxt {
	
	private Reader reader;
	private BufferedReader br;
	private String content;
	private StringBuffer stringBuffer;
	
	private static ReadTxt readTxt;
	
	private ReadTxt(){};
	
	public static ReadTxt getReadTxt(){
		if(readTxt==null){
			readTxt=new ReadTxt();
		}
		return readTxt;
	}
	
	public  String read(Handler handler){
		File file=new File("E:\\Android\\TestViewPage\\book.txt");
		stringBuffer=new StringBuffer();
		
		 try {
			reader=new FileReader(file);
			 br=new BufferedReader(reader);
			 while ((content=br.readLine())!=null) {
				 
				stringBuffer.append(content+"\n");
				
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
//		    Message message = new Message();
//			message.what = 1;
//			message.obj=stringBuffer.toString();
//			handler.sendMessage(message);
			return stringBuffer.toString();
		
		
		
	}

}
