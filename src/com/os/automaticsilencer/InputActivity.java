package com.os.automaticsilencer;

import java.util.Calendar;
import java.util.Date;



import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.Settings.Global;


@SuppressLint("NewApi")
public class InputActivity extends Activity implements OnClickListener{
	public static int ib = 1;
	public static int ie = 1;
	Toast mToast;
	public static int flag = 1;
	DatabaseHandler dbh;
	
	public int bdd;
	public int bmm;
	public int byy;
	public int bhh;
	public int bmn;
	public int edd;
	public int emm;
	public int eyy;
	public int ehh;
	public int emn;
	static final int DIALOG_ID_1 = 0;
	static final int DIALOG_ID_2 = 1;
	
	
	public static int type;
	
	static InputActivity iptemp = new InputActivity();
	
	
	
	public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
		
		
		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the current time as the default values for the picker
	        final Calendar c = Calendar.getInstance();
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);
	        // Create a new instance of TimePickerDialog and return it
	        return new TimePickerDialog(getActivity(), this, hour, minute,
	                DateFormat.is24HourFormat(getActivity()));
	    }

	    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	        // Do something with the time chosen by the user
	    	
	    	if(flag == 1){ // begin time should be noted
	    		String date;
	    		iptemp.bhh = hourOfDay;
	    		iptemp.bmn = minute;
	    		int temp = iptemp.bhh;
	    		if(temp > 12){
	    			temp = temp - 12;
	    			date = temp + " : " + iptemp.bmn + "PM";
	    		}else{
	    			 date = temp + " : " + iptemp.bmn + "AM";
	    		}
				Button activityButton = (Button)getActivity().findViewById(R.id.begin2);
				activityButton.setText (date);
	    	}
	    	else if(flag == 0){ // end time should be noted
	    		String date;
	    		iptemp.ehh = hourOfDay;
	    		iptemp.emn = minute;
	    		int temp = iptemp.ehh;
	    		if(temp > 12){
	    			temp = temp - 12;
	    			date = temp + " : " + iptemp.emn + "PM";
	    		}else{
	    			date = temp + " : " + iptemp.emn + "AM";
	    		}
				Button activityButton = (Button)getActivity().findViewById(R.id.end2);
				activityButton.setText (date);
	    	}
	    	
	    }
	}
	
	
	public void showTimePickerDialogb(View v) {
		flag = 1;
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public void showTimePickerDialoge(View v) {
		flag = 0;
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
	
			if(flag == 1){ // begin time should be noted
				iptemp.bdd = day;
				iptemp.bmm = month;
				int month1 = iptemp.bmm + 1;
				iptemp.byy = year;
				String date = iptemp.bdd + "/" + month1 + "/" + iptemp.byy;
				Button activityButton = (Button)getActivity().findViewById(R.id.begin1);
				activityButton.setText (date);
				
	    	}else if(flag == 0){ // end time should be noted
	    		iptemp.edd = day;
	    		iptemp.emm = month;
	    		iptemp.eyy = year;
	    		int month1 = iptemp.emm + 1;
				iptemp.byy = year;
				String date = iptemp.edd + "/" + month1 + "/" + iptemp.eyy;
				Button activityButton = (Button)getActivity().findViewById(R.id.end1);
				activityButton.setText (date);
	    	}
			
			
		}
	}
	

	public void showDatePickerDialogb(View v) {
		flag = 1;
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	    
	}
	
	public void showDatePickerDialoge(View v) {
		flag = 0;
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

	public void changetext(){
		Button begin2 = (Button)findViewById(R.id.begin1);
		begin2.setText("text");
	}
	public void onClick(View v){
		switch(v.getId()){

		case R.id.delete:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			break;

		case R.id.done:
			
			long begintime;
			long endtime;
			long begint;
			long endt;
			
			begintime = iptemp.byy * 366 + iptemp.bmm * 31 + iptemp.bdd;
			endtime = iptemp.eyy * 366 + iptemp.emm * 31 + iptemp.edd;
			begint = iptemp.bhh * 60 + iptemp.bmn;
			endt = iptemp.ehh * 60 + iptemp.emn;
			
			if(begintime > endtime){
				invaliddates(getBaseContext());
				break;
				
				
			}else if(begintime == endtime){
				// Check time
				if(begint > endt){
					invaliddates(getBaseContext());
					break;
				}
			}
			
			
			EditText editText = (EditText) findViewById(R.id.name_schedule);
			String name = editText.getText().toString();
			String bdd = Integer.toString(iptemp.bdd);
			
			String bmm = Integer.toString(iptemp.bmm + 1);
			String byy = Integer.toString(iptemp.byy);
			String bhh = Integer.toString(iptemp.bhh);
			String bmn = Integer.toString(iptemp.bmn);
			String edd = Integer.toString(iptemp.edd);
			String emm = Integer.toString(iptemp.emm + 1);
			String eyy = Integer.toString(iptemp.eyy);
			String ehh = Integer.toString(iptemp.ehh);
			String emn = Integer.toString(iptemp.emn);
			String intentidb = Integer.toString(InputActivity.ib);
			String intentide = Integer.toString(InputActivity.ie);
		
			
			this.dbh = new DatabaseHandler(this);
			this.dbh.addschedulesingle(name, bdd, bmm, byy, bhh, bmn, edd, emm, eyy, ehh, emn, intentidb, intentide);

			// Code to register at broadcast reciever by creating pending intent when the 
			// done button is clickeds
			
			final Calendar c = Calendar.getInstance();
			
			
			
			
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);
	        int day = c.get(Calendar.DAY_OF_WEEK);
	        int date = c.get(Calendar.DATE);
	        int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int sec = c.get(Calendar.SECOND);
			
			Date date1 = new Date(year, month, date, hour, minute);
			Date date2 = new Date(iptemp.byy, iptemp.bmm, iptemp.bdd, iptemp.bhh, iptemp.bmn);
			Date date3 = new Date(iptemp.eyy, iptemp.emm, iptemp.edd, iptemp.ehh, iptemp.emn);
	        long diff1 = date2.getTime() - date1.getTime();
	        long diff2 = date3.getTime() - date1.getTime();
	        
	        InputActivity.type = 1;
	        Intent intentb  = new Intent(InputActivity.this, Oneshotalarm.class);
	        
	       
			PendingIntent senderb = PendingIntent.getBroadcast(this.getApplicationContext(), InputActivity.ib, intentb, PendingIntent.FLAG_CANCEL_CURRENT);
			
			AlarmManager amb = (AlarmManager) getSystemService(ALARM_SERVICE);
			amb.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + diff1, senderb);
			
			Intent intente  = new Intent(InputActivity.this, Restoreshotalarm.class);
			PendingIntent sendere = PendingIntent.getBroadcast(this.getApplicationContext(), InputActivity.ie, intente, PendingIntent.FLAG_CANCEL_CURRENT);
			
			AlarmManager ame = (AlarmManager) getSystemService(ALARM_SERVICE);
			ame.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + diff2, sendere);
			Log.d("idb", ""+InputActivity.ib);
			Log.d("ide", ""+InputActivity.ie);
			
			InputActivity.ib = InputActivity.ib + 1;
			InputActivity.ie = InputActivity.ie + 1;
		
			if(mToast != null){
				mToast.cancel();
				
			}
			mToast = Toast.makeText(InputActivity.this, "One shot scheduled", Toast.LENGTH_LONG);
			mToast.show();
			showDialog(DIALOG_ID_1);
			
            break;

		}
	}  
	
	public void invaliddates(Context context){
		mToast = Toast.makeText(context, "End time should be greater than the begin time", Toast.LENGTH_LONG);
		mToast.show();
	}
	
	public void notimeselected(Context context){
		mToast = Toast.makeText(context, "Please enter Begin/End time", Toast.LENGTH_LONG);
		mToast.show();
	}
	
	public void nodateelected(Context context){
		mToast = Toast.makeText(context, "Please enter Begin/End date", Toast.LENGTH_LONG);
		mToast.show();
	}
	
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch(id) {
		case DIALOG_ID_1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Event added successfully ! Add Another event?")
			.setCancelable(false)
			.setPositiveButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					InputActivity.this.finish();

              }
			})
			.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create(); 
			dialog = alert;
			break;
		default:

		}
		return dialog;
	}	

	
		

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		// Show the Up button in the action bar.
		setupActionBar();
		View done = findViewById(R.id.done);
		done.setOnClickListener(this);
		View delete = findViewById(R.id.delete);
		delete.setOnClickListener(this);   
		
			
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

}
