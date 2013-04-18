package com.os.automaticsilencer;

import java.util.Calendar;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;


import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

import android.widget.CheckBox;
@SuppressLint("NewApi")
public class InputSchedule extends Activity implements OnClickListener{

	static int ib= 1;
	static int ie= 1;
	
	public static int flag = 1;
	public static int flag2 = 1;
	DatabaseHandler dhs;
	
	CheckBox cb1;
	CheckBox cb2;
	CheckBox cb3;
	CheckBox cb4;
	CheckBox cb5;
	CheckBox cb6;
	CheckBox cb7;
	
    Toast mToast;
    
	
	public int bhh;
	public int bmn;
	public int ehh;
	public int emn;
	public int mon;
	public int tue;
	public int wed;
	public int thu;
	public int fri;
	public int sat;
	public int sun;
	public boolean checked;
	public static int type;

	static final int DIALOG_ID_1 = 0;

	
	OnClickListener checkBoxListener;

	
	static InputSchedule istemp = new InputSchedule();
	
	//---------------------------------------------------------------------------------
	
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
	    		istemp.bhh = hourOfDay;
	    		istemp.bmn = minute;
	    		int temp = istemp.bhh;
	    		if(temp > 12){
	    			temp = temp - 12;
	    			date = temp + " : " + istemp.bmn + "PM";
	    		}else{
	    			date = temp + " : " + istemp.bmn;
	    		}
				Button activityButton = (Button)getActivity().findViewById(R.id.btime);
				activityButton.setText (date);
	    	}else if(flag == 0){ // end time should be noted
	    		String date;
	    		istemp.ehh = hourOfDay;
	    		istemp.emn = minute;
	    		int temp = istemp.ehh;
	    	    if(temp > 12){
	    	    	temp = temp - 12;	  
	    	    	date = temp + " : " + istemp.emn + "PM";
	    	    }
	    	    date = temp + " : " + istemp.emn + "AM";
				Button activityButton = (Button)getActivity().findViewById(R.id.etime);
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
	
	
	// --------------------------------------------------------------------------------
	
	public void onClick(View v){
		switch(v.getId()){

		case R.id.delete1:
			Intent i1 = new Intent(this, MainActivity.class);
			startActivity(i1);
			break;

		case R.id.done1:
			long begint;
			long endt;
			
			begint = istemp.bhh * 60 + istemp.bmn;
			endt = istemp.ehh * 60 + istemp.emn;
			if(begint > endt){
			
				invaliddates(getBaseContext());
				break;
			}
			
			if(istemp.bhh == 0 || istemp.ehh == 0){
				notimeselected(getBaseContext());
				break;
			}
			else{
				final Calendar c = Calendar.getInstance();
		        int hour = c.get(Calendar.HOUR_OF_DAY);
		        int minute = c.get(Calendar.MINUTE);
		        int day = c.get(Calendar.DAY_OF_WEEK);
		        
		        EditText editText = (EditText) findViewById(R.id.name_schedule);
				String name = editText.getText().toString();
				
				String bhh = Integer.toString(istemp.bhh);
				String bmn = Integer.toString(istemp.bmn);
				String ehh = Integer.toString(istemp.ehh);
				String emn = Integer.toString(istemp.emn);
				
				
				String mon = Integer.toString(istemp.mon);
							
				String tue = Integer.toString(istemp.tue);
				String wed = Integer.toString(istemp.wed);
				
				String thu = Integer.toString(istemp.thu);
				String fri = Integer.toString(istemp.fri);
				String sat = Integer.toString(istemp.sat);
				String sun = Integer.toString(istemp.sun);
				
		        long ansbm;
		        long ansbtu;
		        long ansbw;
		        long ansbth;
		        long ansbf;
		        long ansbsa;
		        long ansbsu;
		        long ansem;
		        long ansetu;
		        long ansew;
		        long anseth;
		        long ansef;
		        long ansesa;
		        long ansesu;
		        int noday;
		        
		        if(istemp.mon == 1){
		        	if(day < 2){
		        		int temp = 2 - day - 1;
		        		ansbm = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansem = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
		        		function1(ansbm, ansem);
		           	}
		        	else if(day == 2){
		        		ansbm = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
		        		ansem = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
		        		function1(ansbm, ansem);
		        		      		
		        	}else if(day > 2){
		        		noday = (7 - day) + 1;
		        		ansbm = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
		        		ansem = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
		        		function1(ansbm, ansem);
		            }
		        	
		        }else{
		        	flag2 = flag2 + 1;
		        }
		        
		        if(istemp.tue == 1){
					if(day < 3){
						int temp = 3 - day - 1;
						ansbtu = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansetu = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbtu, ansetu);
					}	
					else if(day == 3){
						ansbtu = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
						ansetu = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
						function1(ansbtu, ansetu);
					}else if(day > 3){
						noday = (7 - day) + 1;
						ansbtu = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						ansetu = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbtu, ansetu);
			   		}
				}else{
		        	flag2 = flag2 + 1;
		        }
			
				if(istemp.wed == 1){
					if(day < 4){
						int temp = 4 - day - 1;
						ansbw = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansew = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbw, ansew);
					}
					else if(day == 4){
	        			ansbw = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
	        			ansew = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
	        			function1(ansbw, ansew);
					}else if(day > 4){
						noday = (7 - day) + 1;
						ansbw = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						ansew = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbw, ansew);
					}
					
				}else{
		        	flag2 = flag2 + 1;
		        }
				
				if(istemp.thu == 1){
					if(day < 5){
						int temp = 5 - day - 1;
						ansbth = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						anseth = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbth, anseth);
					}
					else if(day == 5){
	        			ansbth = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
	        			anseth = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
	        			function1(ansbth, anseth);
					}else if(day > 5){
						noday = (7 - day) + 1;
						ansbth = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						anseth = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbth, anseth);
					}
					
				}else{
		        	flag2 = flag2 + 1;
		        }
				
				if(istemp.fri == 1){
					if(day < 6){
					
						int temp = 6 - day - 1;
						ansbf = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansef = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbf, ansef);
					}
					else if(day == 6){
	        			ansbf = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
	        			ansef = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
	        			function1(ansbf, ansef);
					}else if(day > 6){
						noday = (7 - day) + 1;
						ansbf = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						ansef = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbf, ansef);
					}
					
				}else{
		        	flag2 = flag2 + 1;
		        }
		
				if(istemp.sat == 1){
					if(day < 7){
						int temp = 7 - day - 1;
						ansbsa = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansesa = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbsa, ansesa);
					}
					else if(day == 7){
	        			ansbsa = ((istemp.bhh - hour)*60)*60 + (istemp.bmn - minute)*60;
	        			ansesa = ((istemp.ehh - hour)*60)*60 + (istemp.emn - minute)*60;
	        			function1(ansbsa, ansesa);
					}else if(day > 7){
						noday = (7 - day) + 1;
						ansbsa = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						ansesa = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbsa, ansesa);
					}
					
				}else{
		        	flag2 = flag2 + 1;
		        }
				
				if(istemp.sun == 1){
					if(day < 1){
						int temp = 6 - day - 1;
						ansbsu = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.bhh *60 + istemp.bmn)*60;
						ansesu = ((temp*24*60) + ((24 - hour) * 60) + (60 - minute) + istemp.ehh *60 + istemp.emn)*60;
						function1(ansbsu, ansesu);
					}
					else if(day == 1){
						ansbsu = ((istemp.bhh - hour - 1)*60 + (60 - minute) + istemp.bmn) *60;
				        ansesu = ((istemp.ehh - hour - 1)*60 + (60 - minute) + istemp.emn) *60;
				        function1(ansbsu, ansesu);
					}else if(day > 1){
						noday = (7 - day) + 1;
						ansbsu = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.bhh * 60 + istemp.bmn)) *60;
						ansesu = noday*24*60*60 +  ((24 - hour)*60 + minute + (istemp.ehh * 60 + istemp.emn)) *60;
						function1(ansbsu, ansesu);
					}
				}else{
		        	flag2 = flag2 + 1;
		        }
				 
				if(flag2 == 8){
					nodayselected(getBaseContext());
					flag2 = 1;
					break;
				}
				String intentidb = Integer.toString(InputSchedule.ib);
				String intentide = Integer.toString(InputSchedule.ie);
				
				this.dhs = new DatabaseHandler(this);
				this.dhs.addschedule(name, bhh, bmn, ehh, emn, intentidb, intentide, mon, tue, wed, thu, fri, sat, sun);
				showDialog(DIALOG_ID_1);
				break;
			}
		}
	}
			
	public void function1 (long ansb, long anse){
		
		InputSchedule.type = 1;
		Intent intentb  = new Intent(InputSchedule.this, ScheduleAlarm.class);
		
		PendingIntent senderb = PendingIntent.getBroadcast(this.getApplicationContext(), InputSchedule.ib, intentb,  PendingIntent.FLAG_CANCEL_CURRENT);
		
		AlarmManager amb = (AlarmManager) getSystemService(ALARM_SERVICE);
		amb.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (ansb * 1000), (7*24*3600*1000), senderb);
		
		Intent intente  = new Intent(InputSchedule.this, Restoreshotalarm.class);
		PendingIntent sendere = PendingIntent.getBroadcast(this.getApplicationContext(), InputSchedule.ie, intente,  PendingIntent.FLAG_CANCEL_CURRENT);
		
		AlarmManager ame = (AlarmManager) getSystemService(ALARM_SERVICE);
		ame.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (anse * 1000), (7*24*3600*1000),  sendere);
			
		InputSchedule.ib  = InputSchedule.ib + 1;
		InputSchedule.ie = InputSchedule.ie + 1;

	}
	
	public void nodayselected(Context context){
		mToast = Toast.makeText(context, "Please select atleast one day", Toast.LENGTH_LONG);
		mToast.show();
	}
	
	public void notimeselected(Context context){
		mToast = Toast.makeText(context, "Please enter Begin/End time", Toast.LENGTH_LONG);
		mToast.show();
	}
	
	public void invaliddates(Context context){
		mToast = Toast.makeText(context, "End time should be greater than the begin time", Toast.LENGTH_LONG);
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
					InputSchedule.this.finish();

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
	//---------------------------------------------------------------------------------
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_schedule);
		// Show the Up button in the action bar.
	
		View done1 = findViewById(R.id.done1);
		done1.setOnClickListener(this);
		View delete1 = findViewById(R.id.delete1);
		delete1.setOnClickListener(this);   
		
		cb1=(CheckBox)findViewById(R.id.checkBox1);
		cb2=(CheckBox)findViewById(R.id.checkBox2);
		cb3=(CheckBox)findViewById(R.id.checkBox3);
		cb4=(CheckBox)findViewById(R.id.checkBox4);
		cb5=(CheckBox)findViewById(R.id.checkBox5);
		cb6=(CheckBox)findViewById(R.id.checkBox6);
		cb7=(CheckBox)findViewById(R.id.checkBox7);
		
		checkBoxListener =new OnClickListener() {
			@Override
			 public void onClick(View v) {
			 
			 if(cb1.isChecked())
			 {
				 istemp.mon = 1;
			 }else{
				 istemp.mon = 0;
			 }
			 if(cb2.isChecked())
			 {
				 istemp.tue = 1;
			 }else{
				 istemp.tue = 0;
			 }
			 if(cb3.isChecked())
			 {
				 istemp.wed = 1;
			 }else{
				 istemp.wed = 0;
			 }
			 if(cb4.isChecked())
			 {
				 istemp.thu = 1;
			 }else{
				 istemp.thu = 0;
			 }
			 if(cb5.isChecked())
			 {
				 istemp.fri = 1;
			 }else{
				 istemp.fri = 0;
			 }
			 if(cb6.isChecked())
			 {
				 istemp.sat = 1;
			 }else{
				 istemp.sat = 0;
			 }
			 if(cb7.isChecked())
			 {
				 istemp.sun = 1;
			 }else{
				 istemp.sun = 0;
			 }
			
			 
			 }
			 }; 
			 cb1.setOnClickListener(checkBoxListener);
			 cb2.setOnClickListener(checkBoxListener);
			 cb3.setOnClickListener(checkBoxListener);
			 cb4.setOnClickListener(checkBoxListener);
			 cb5.setOnClickListener(checkBoxListener);
			 cb6.setOnClickListener(checkBoxListener);
			 cb7.setOnClickListener(checkBoxListener);
			 
			 
		}
	
			
	}

	

	
