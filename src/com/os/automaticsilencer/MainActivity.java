 package com.os.automaticsilencer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	static final int DIALOG_ID_1 = 0;
	static final int DIALOG_ID_2 = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View button1Click = findViewById(R.id.button1);
		button1Click.setOnClickListener(this);
		View button2Click = findViewById(R.id.button2);
		button2Click.setOnClickListener(this);  
		
	}
	
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch(id) {
		case DIALOG_ID_1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Select the type of event you want to set")
			.setCancelable(true)
			.setPositiveButton("Single Event", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					startsingle();
              }
			})
			.setNegativeButton("Scheduled event", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					startschedule();
				}
			});
			AlertDialog alert = builder.create(); 
			dialog = alert;
			break;
		case DIALOG_ID_2:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setMessage("View")
			.setCancelable(true)
			.setPositiveButton("Single Events", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					viewsingle();
              }
			})
			.setNegativeButton("Scheduled events", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					viewschedule();
				}
			});
			AlertDialog alert1 = builder1.create(); 
			dialog = alert1;
			break;

		
		default:

		}
		return dialog;
	}

	public void startsingle(){
		Intent i = new Intent(this, InputActivity.class);  
		startActivity(i);
	}
	
	public void startschedule(){
		Intent i = new Intent(this, InputSchedule.class);  
		startActivity(i);
	}
	
	public void viewsingle(){
		Intent i = new Intent(this, ViewSingle.class);  
		startActivity(i);
	}
	
	public void viewschedule(){
		Intent i = new Intent(this, ViewSchedule.class);  
		startActivity(i);
	}
	// Add event button listener
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.button1:
			showDialog(DIALOG_ID_1);
		
			break;
		case R.id.button2:
			showDialog(DIALOG_ID_2);
		
			break;
		
		}
	}
	

}
