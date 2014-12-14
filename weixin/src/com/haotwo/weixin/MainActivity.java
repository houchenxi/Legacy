package com.haotwo.weixin;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	private TabHost tabHost;
	
//	private View MakeIndicator(String text, int idImage)
//	{
//		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
//		((TextView) tabIndicator.findViewById(R.id.tabText)).setText(text);
//		((ImageView) tabIndicator.findViewById(R.id.tabicon)).setImageResource(idImage);
//		return tabIndicator;
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		// 正式添加标签页
		final int[] tabLayouts = { R.layout.tab1, R.layout.tab2, R.layout.tab3, R.layout.tab4 };
		inflateTab(tabLayouts);
		
//		drawableOfTabIcon = getResources().getDrawable(R.drawable.yongchang);
		
		// 添加标签名称
		tabHost.addTab(
				tabHost.newTabSpec("tab1")
				.setIndicator("微信",getResources().getDrawable(R.drawable.weixin))
				.setContent(R.id.tab1)
				);
		tabHost.addTab(
				tabHost.newTabSpec("tab2")
				.setIndicator("通讯录",getResources().getDrawable(R.drawable.contacts))
				.setContent(R.id.tab2)
				);
		tabHost.addTab(
				tabHost.newTabSpec("tab3")
				.setIndicator("发现",getResources().getDrawable(R.drawable.faxian))
				.setContent(R.id.tab3)
				);
		tabHost.addTab(
				tabHost.newTabSpec("tab4")
				.setIndicator("我",getResources().getDrawable(R.drawable.wo))
				.setContent(R.id.tab4)
				);
		
		// 改变标签页的文字颜色为蓝色
		for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
		{
			 TextView text = (TextView)tabHost.getTabWidget().getChildTabViewAt(i).findViewById(android.R.id.title);
			 text.setTextColor(Color.BLUE);
		}
		
		// 修改字号
		FrameLayout frameLay = (FrameLayout)tabHost.getTabContentView();
		for(int i = 0; i < frameLay.getChildCount(); i++)
		{
			// 记录 TAB编号
			Log.d(this.getClass().getName(),"tab#:" + i);
			
			// 取得tab1,tab2的 LINEARLAYOUT
			LinearLayout tabLinearLay = (LinearLayout) frameLay.getChildAt(i);
			
			// 取得TEXTVIEW，设置字号
			for(int j = 0; j < tabLinearLay.getChildCount(); j++)
			{
				TextView text = (TextView) tabLinearLay.getChildAt(j);
				text.setTextSize(60);
			}
		}
		
		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
		
		// 发送一条通知
		postNotification( 
				getResources().getStringArray(R.array.notification_strings)[0] );
	}

	private void inflateTab(int [] tabLayouts) {
		LayoutInflater inflater = LayoutInflater.from(this);
		FrameLayout	   tabContentView = tabHost.getTabContentView();
		for(int i = 0; i < tabLayouts.length; i++)
		{
			inflater.inflate(tabLayouts[i], tabContentView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// 横屏滑动相关的代码
	private static final int SWIPE_MIN_DISTANCE = 30;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 50;
	
	int currentView = 0;
	private static int maxTabIndex = 3;
	
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private NotificationManager notificationManager;
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}

	// 左右滑动刚好页面也有滑动效果
	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "right");
					if (currentView == maxTabIndex) {
						currentView = 0;
					} else {
						currentView++;
					}
					tabHost.setCurrentTab(currentView);
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "left");
					if (currentView == 0) {
						currentView = maxTabIndex;
					} else {
						currentView--;
					}
					tabHost.setCurrentTab(currentView);
				}
			} catch (Exception e) {
			}
			return false;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// 发送通知的测试代码
	private void postNotification(String popMsg)
	{
		Notification msg = new Notification(
				R.drawable.ic_launcher,
				popMsg,
				System.currentTimeMillis());
		
		// 设置点击后的动作
		PendingIntent contentIntent = PendingIntent.getActivity(
				this, 
				0,
				new Intent(this, MainActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		// 设置通知的标题和内容
		msg.setLatestEventInfo(
				MainActivity.this, 
				"这是微信启动的通知标题", 
				"这是微信启动的通知内容", 
				contentIntent);
		notificationManager.cancelAll();
		notificationManager.notify(0, msg);
	}
	///////////////////////////////////////////////////////////////////////////
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		postNotification( 
				getResources().getStringArray(R.array.notification_strings)[1] );
	}
}
