package com.anxiong.fragment;

import java.io.IOException;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.testviewpage.R;

public class DFragment extends BaseFragment implements SurfaceHolder.Callback,
		OnClickListener {
	/**
	 * 
	 * 
	 * SurfaceHolder 1. 简介 : 是 Surface 的控制器, 用于控制 SurfaceView 绘图, 处理画布上的动画,
	 * 渲染效果, 大小等; 2. 常用方法 : -- abstract void addCallback(SurfaceHolder.Callback
	 * callback) : 添加一个 SurfaceHolder.Callback 接口对象, 监听 Surface 的开始结束绘制大小改变事件;
	 * -- abstract Canvas lockCanvas() : 锁定画布, 可以获得 Canvas 对象, 之后就可以在 Canvas
	 * 上绘图了;
	 * 
	 * SurfaceHolder.Callback接口 : 1. Surface 绘图边界 : 所有的绘图工作都在 Surface 创建之后才能进行,
	 * 在 Surface 销毁之前结束; 2. Callback 接口对应的 Surface 边界 : surfaceCreated()
	 * 方法在开始绘制时回调, surfaceDestroyed() 在 Surface 销毁前回调; 3. 该接口中的方法 : --
	 * surfaceChanged() : 在 Surface 大小改变时回调; -- surfaceCreated() : 在 Surface
	 * 创建时回调; -- surfaceDestroyed() : 在 Surface 销毁时回调;
	 * 
	 * @author octopus
	 * 
	 */
	private View view;

	private AutoCompleteTextView txtUrl;

	private SurfaceView surfaceView;/* 播放视频载体* */
	private SurfaceHolder surfaceHolder;/* SurfaceView控制器* */

	private MediaPlayer mediaPlayer;/* 播放器* */

	private TextView txtStatus;
	private Button btnPlay, btnPause, btnReset, btnStop;

	private Boolean isStartPlay=false;/* 是否开始播放* */

	private String[] urls;

	/** 自动提示/设置一个列表适配器 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/* 设置一个列表适配器 */
		urls = new String[] {
				"http://daily3gp.com/vids/747.3gp",
				"http://daily3gp.com/vids/Funny%20women%20cannot%20understand.3gp",
				"http://k.youku.com/player/getFlvPath/sid/9409280845322127f6c57_00/st/flv/fileid/0300020100540024BC9E5C08BD8A98D8200E2B-7950-B9A5-8669-DC283BDCC077?K=3a58dc2cdcc532df261dddec&ctype=12&ev=1&oip=1931322792&token=5696&ep=eyaUE0uFVsYE4CDdij8bYHrkJ3IIXP4J9h%2BFg9JjALshTOi%2FmzqjtJTFS4xCHottelMPGJ%2F5qdDnH0JmYfdKrGgQrUfZPPro%2BPbq5dkgxpgDFG1FAc3Qs1SbRTn3",
				"http://k.youku.com/player/getFlvPath/sid/9409280845322127f6c57_00/st/flv/fileid/030002040053FFB59E433100422C39BAFA46CC-4DED-E928-87B8-91706CDB5FF2?K=645d8478a3aa59052411eb8a&ctype=12&ev=1&oip=1931322792&token=5696&ep=eyaUE0uFVsYE4CDdij8bYHrkJ3IIXP4J9h%2BFg9JmALshS57J6zvYspmzTf5CFv0bcFEFGZmA3aHjbDNnYfQ33BwQqkeqMfro%2BYLr5aRSw5AGFW1Ed7uhtlSbRTn3"

		};

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.d, null);
		init();
		return view;
	}

	private void init() {

		txtUrl = (AutoCompleteTextView) view.findViewById(R.id.txt_url);
		surfaceView = (SurfaceView) view.findViewById(R.id.surfaceview);
		txtStatus = (TextView) view.findViewById(R.id.txt_status);
		btnPlay = (Button) view.findViewById(R.id.btn_play);
		btnPlay.setOnClickListener(this);
		btnPause = (Button) view.findViewById(R.id.btn_pause);
		btnPause.setOnClickListener(this);
		btnReset = (Button) view.findViewById(R.id.btn_reset);
		btnReset.setOnClickListener(this);
		btnStop = (Button) view.findViewById(R.id.btn_stop);
		btnStop.setOnClickListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, urls);

		txtUrl.setAdapter(adapter);

		/* 使窗口支持透明度, 把当前 Activity 窗口设置成透明, 设置了该选项就可以使用 setAlpha 等函数设置窗口透明度 */
		getActivity().getWindow().setFormat(PixelFormat.TRANSPARENT);

		initData();

	}

	private void initData() {
		/*
		 * 根据 SurfaceView 组件, 获取 SurfaceHolder 对象
		 */
		surfaceHolder = surfaceView.getHolder();
		/* 为 SurfaceHolder 设置回调函数, 即 SurfaceHolder.Callback 子类对象 */
		surfaceHolder.addCallback(this);
		surfaceHolder.setFixedSize(128, 160);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);/* 设置视频类型 */

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("SurfaceHolder.Callback.surfaceCreated : Surface 开始创建");

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		System.out.println("SurfaceHolder.Callback.surfaceChanged : Surface 大小发生改变");

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		System.out.println("SurfaceHolder.Callback.surfaceDestroyed : Surface 销毁");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play:

			playVideo(txtUrl.getText().toString());

			break;
		case R.id.btn_pause:
			if (mediaPlayer != null) {
				mediaPlayer.pause();
				txtStatus.setText("暂停");
			}
			break;
		case R.id.btn_reset:
			if (mediaPlayer != null) {
				mediaPlayer.seekTo(0);
				mediaPlayer.start();
				txtStatus.setText("播放中");

			}

			break;
		case R.id.btn_stop:

			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();
				isStartPlay = false;
				txtStatus.setText("停止");

			}

			break;

		default:
			break;
		}

	}

	/**
	 * 播放网络视频 a. 创建并配置 MediaPlayer 对象 (音量, SurfaceHolder) b. 为 MediaPlayer
	 * 设置错误监听器, 缓冲进度监听器, 播放完毕监听器, 准备完毕监听器 c. 未 MediaPlayer 设置数据源 d. 调用 prepare()
	 * 进入 Prapared 状态 e. 调用 start() 进入 Started 状态
	 * 
	 * @param path
	 *            播放视频的网络地址
	 */

	private void playVideo(final String path) {

		/*
		 * 点击播放有两种情况 a. 第一次点击 : 需要初始化 MediaPlayer 对象, 设置监听器 b. 第二次点击 : 只需要 调用
		 * mediaPlayer 的 start() 方法 两种情况通过 isStartPlaying 点击时间判断
		 */

		if (isStartPlay) {
			mediaPlayer.start();
		} else {

			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(2);/* 设置播放音量 */
			mediaPlayer.setDisplay(surfaceHolder);/* 设置播放载体 */
			/* 
			 * 设置 MediaPlayer 错误监听器, 如果出现错误就会回调该方法打印错误代码 
			 * */
			mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {

					System.out.println("MediaPlayer 出现错误 what : " + what + " , extra : " + extra);
					return false;
				}
			});
			/* 
			 * 设置缓冲进度更新监听器 
			 * */
			mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
				
				@Override
				public void onBufferingUpdate(MediaPlayer mp, int percent) {
					/* 打印缓冲的百分比, 如果缓冲 */
					
					System.out.println("缓冲了的百分比 : " + percent + " %");
					
				}
			});
			/* 
			 * 设置播放完毕监听器 
			 * */
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					System.out.println("播放完毕了");
					txtStatus.setText("播放完必");
					
				}
			});
			
			/* 
			 * 设置准备完毕监听器 
			 * */
			
			mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					System.out.println("准备完毕");
					txtStatus.setText("播放中");
					
				}
			});
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("设置数据源");
					try {
						
						mediaPlayer.setDataSource(path);
						mediaPlayer.prepare();
						
						/*
						 *播放长度 
						 **/
						System.out.println("视频播放长度 : " + mediaPlayer.getDuration());
						
						mediaPlayer.start();
						
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
			
			/* 设置 MediaPlayer 开始播放标识为 true */
			
			isStartPlay = true;
			/* 设置播放状态 */
			txtStatus.setText("正在缓冲");
		}

	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mediaPlayer!=null){
			mediaPlayer.release();
		}
		
		
	}

}
