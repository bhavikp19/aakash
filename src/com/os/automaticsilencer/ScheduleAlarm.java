package com.os.automaticsilencer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;
import android.media.*;
import android.os.Bundle;

public class ScheduleAlarm extends BroadcastReceiver{

	Toast mToast;
		
	@Override
	public void onReceive(Context context, Intent intent) {
		
	
		if(InputSchedule.type == 0){
			mToast = Toast.makeText(context, "Scheduled Alarm Deleted", Toast.LENGTH_LONG);
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
			InputSchedule.type = 1;
			
			
		}
		else if(InputSchedule.type == 1){
			mToast = Toast.makeText(context, "Scheduled Alarm started", Toast.LENGTH_LONG);
			mToast.show();
			
			AudioManager audioManager1 = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			audioManager1.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	
		}
			
		
	}
	
	
	

}
