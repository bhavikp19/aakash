package com.os.automaticsilencer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;
import android.media.*;
import android.os.Bundle;

public class Oneshotalarm extends BroadcastReceiver{

	Toast mToast;
		
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stu
		
		
	
		if(InputActivity.type == 0){
			mToast = Toast.makeText(context, "Alarm Deleted", Toast.LENGTH_LONG);
			mToast.show();
			AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		
			if(audioManager.getRingerMode() == audioManager.RINGER_MODE_NORMAL){
				audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
			
			else if(audioManager.getRingerMode() == audioManager.RINGER_MODE_SILENT){
				audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
			
			
			else if(audioManager.getRingerMode() == audioManager.RINGER_MODE_VIBRATE){
				audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}
			InputActivity.type = 1;
			
			
		}
		else if(InputActivity.type == 1){
			mToast = Toast.makeText(context, "Alarm started", Toast.LENGTH_LONG);
			mToast.show();
			
			AudioManager audioManager1 = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			audioManager1.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	
		}
			
		
	}
	
	
}
