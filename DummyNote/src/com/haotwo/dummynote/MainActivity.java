package com.haotwo.dummynote;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onStop");
		super.onStop();
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onMenuItemSelected:" +  "featureId=" + featureId + ",itemText=" + item.getTitle());
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "hcx onMenuOpened:" + "featureId=" + featureId + ",menuHash=" + menu.hashCode());
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.d(this.getClass().getName(), "hcx onListItemClick:" + "position=" + position + ",id=" + id+",itemText="+((TextView)v).getText()+",ListViewCnt="+l.getCount());
	}


	private String[] note_array = {"∆˚”Õ", "≤Ò”Õ"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.listitem, note_array);
		Log.d(this.getClass().getName(), "hcx onCreate");
		this.setListAdapter(listAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.d(this.getClass().getName(), "hcx onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
		Log.d(this.getClass().getName(), "hcx onOptionsMenuClosed");
	}


}
