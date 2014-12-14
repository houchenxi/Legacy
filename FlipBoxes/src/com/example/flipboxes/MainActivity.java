package com.example.flipboxes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private int nRow;
	private int nColumn;
	private GridLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nColumn = 4;
		nRow = 4;
		
		layout = (GridLayout)findViewById(R.id.layout);
		
		// 逻辑上很直接，实际中不奏效
		for(int i = 0; i < nRow; i++)
		{
			for(int j = 0; j < nColumn; j++)
			{
				ImageView view = new ImageView(MainActivity.this);
				view.setImageResource(R.drawable.box);
				view.setPadding(5, 5, 5, 5);

				view.setOnClickListener(new OnClickListener() {
				
					private int index = 0;
					
					private int[] pic = {
							R.drawable.box,
							R.drawable.box1,
							R.drawable.box2,
							R.drawable.box3,
							R.drawable.box4,
							};

					@Override
					public void onClick(View v) {
						((ImageView)v).setImageResource(pic[(++index)%(pic.length)]);
					}
				});
				
				view.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						
						return false;
					}
				});
				
				layout.addView(view);
			}
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
