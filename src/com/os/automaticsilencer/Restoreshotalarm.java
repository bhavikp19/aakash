package com.os.automaticsilencer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;
import android.media.*;

public class Restoreshotalarm extends BroadcastReceiver{

	Toast mToast;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stu
		mToast = Toast.makeText(context, "Alarm restored", Toast.LENGTH_LONG);
		mToast.show();
		AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		
	}
	
	
	
	

}
