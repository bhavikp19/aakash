package com.os.automaticsilencer;




import android.os.Bundle;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

import android.support.v4.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;


public class ViewSingle extends ListActivity {

	DatabaseHandler dm;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_single);
		printf();
		
	}
	public void printf(){
		dm = new DatabaseHandler(this);
		
		Log.d("oncreate", "test1");
		 
		Cursor cursor = dm.fetchAllevents();
		Log.d("filter", "test");
		
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.activity_item_layout, cursor, new String[] {"name", "bdd", "bmm", "byy","bhh", "bmn", "edd", "emm", "eyy","ehh", "emn"}, new int[] {R.id.textView20, R.id.textView3, R.id.textView5, R.id.textView7, R.id.textView8, R.id.textView10, R.id.textView12, R.id.textView14, R.id.textView16, R.id.textView17, R.id.textView19});
		
		setListAdapter(adapter);
		ListView listview = (ListView)findViewById(android.R.id.list);
		listview.setTextFilterEnabled(true);
	
		listview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.d("filter", "testin item click");
					Cursor cur = (Cursor)parent.getItemAtPosition(position);
					int id1 = cur.getInt(cur.getColumnIndex("_id"));
					Log.d("simple", ""+id1);
					int intentidb = dm.getintentidb(id1);
					int intentide = dm.getintentide(id1);
					deleterow(id1, intentidb, intentide);
		
	}
	});
	}
	
	public void deleterow(int id1, int intentidb, int intentide){
		InputActivity.type = 0;
		Intent intentb  = new Intent(ViewSingle.this, Oneshotalarm.class);
		PendingIntent senderb = PendingIntent.getBroadcast(this.getApplicationContext(), intentidb, intentb, 0);
		PendingIntent sendere = PendingIntent.getBroadcast(this.getApplicationContext(), intentide, intentb, 0);
		
		AlarmManager amb = (AlarmManager) getSystemService(ALARM_SERVICE);
		amb.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1, senderb);
		amb.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3, sendere);
		
		dm.deletesingle(id1);
		printf();
	}
}
