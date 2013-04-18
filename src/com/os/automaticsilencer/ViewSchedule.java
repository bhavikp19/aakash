package com.os.automaticsilencer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ViewSchedule extends ListActivity  {     
	
	
DatabaseHandler dm;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_single);
		printf();
		
	}
	public void printf(){
		dm = new DatabaseHandler(this);
		
		Log.d("oncreate", "test1");
		 
		Cursor cursor = dm.fetchAlleventss();
		Log.d("filter", "test");
		
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.activity_item_layoutschedule, cursor, new String[] {"name","bhh", "bmn","ehh", "emn" }, new int[] {R.id.textView2, R.id.textView4, R.id.textView6, R.id.textView8, R.id.textView10});
		
		setListAdapter(adapter);
		ListView listview = (ListView)findViewById(android.R.id.list);
		listview.setTextFilterEnabled(true);
	
		listview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Log.d("filter", "testin item click");
					Cursor cur = (Cursor)parent.getItemAtPosition(position);
					int id1 = cur.getInt(cur.getColumnIndex("_id"));
					Log.d("simple", ""+id1);
					int intentidb = dm.getintentidb2(id1);
					int intentide = dm.getintentide2(id1);
					
					deleterow(id1, intentidb, intentide);
		
	}
	});
	}
	
	public void deleterow(int id1, int intentidb, int intentide){
		InputSchedule.type = 0;
		Intent intentb  = new Intent(ViewSchedule.this, ScheduleAlarm.class);
		PendingIntent senderb = PendingIntent.getBroadcast(this.getApplicationContext(), intentidb, intentb, 0);
		PendingIntent sendere = PendingIntent.getBroadcast(this.getApplicationContext(), intentide, intentb, 0);
		Toast.makeText(getBaseContext(), "Schedule alarm deleted", Toast.LENGTH_SHORT).show();
		AlarmManager amb = (AlarmManager) getSystemService(ALARM_SERVICE);
		amb.cancel(senderb);
		amb.cancel(sendere);
		dm.deleteschedule(id1);
		printf();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	