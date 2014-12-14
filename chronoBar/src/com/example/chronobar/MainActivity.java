package com.example.chronobar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chronobar.util.TimeDigitFormattor;

public class MainActivity extends ActionBarActivity {

	private ProgressBar progress;
	private ProgressBar dateProgress;
	private TextView	textDate;
	private TextView	textTime;
	
	Handler handler=new Handler();
	
	boolean bIsStarted = false;
	
	int secondsInDay = 0;
	
	Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progress = (ProgressBar) findViewById(R.id.progress);
		dateProgress = (ProgressBar) findViewById(R.id.dateProgress);
		textTime	 = (TextView) findViewById(R.id.textTime);
		textDate	 = (TextView) findViewById(R.id.textDate);
		
		textDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
				
				alert.setIcon(android.R.drawable.btn_dialog);
				alert.setTitle("提示");
				alert.setMessage("请选择");
				android.content.DialogInterface.OnClickListener listenerCancel = new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "已经取消啦", Toast.LENGTH_SHORT)
						.show();
					}
				};
				android.content.DialogInterface.OnClickListener listenerOK = new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "已经确定啦", Toast.LENGTH_SHORT)
						.show();
					}
				};
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", listenerCancel);
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listenerOK);
				alert.show();
			}
		});
		
		if (runnable == null)
		{
			runnable = new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Time time = new Time();
					time.setToNow();
					Time firstDay = new Time();
					firstDay.set(1, 0, time.year);
					secondsInDay = time.hour * 3600 + time.minute * 60 + time.second;//(int) (System.currentTimeMillis() / 1000 % 86400);
					progress.setProgress((int)(secondsInDay));
					
					textDate.setText(time.year + "年" + time.month + "月" + time.monthDay + "日");
					dateProgress.setProgress(
						(int)((time.toMillis(false)
								- firstDay.toMillis(false)) / 86400000)
							);
					
					Log.d("hcx", "day in year1:" + (int)( time.toMillis(false) / 86400000));
					Log.d("hcx", "day in year2:" + ( firstDay.toMillis(false) / 86400000) );
					
					String timeFormat = "";
					int[] hms = {time.hour, time.minute, time.second};
					for(int i = 0; i < hms.length; i++)
					{
						if ( i > 0 )
							timeFormat += ":";
						timeFormat += TimeDigitFormattor.Convert(hms[i]);
					} 
					textTime.setText(timeFormat);
					
					handler.postDelayed(this, 1000);
					Log.d("hcx", "working @" + secondsInDay);
				}
			};
		}
		
		if ( false == bIsStarted)
		{
			handler.postDelayed(runnable, 2000);
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
