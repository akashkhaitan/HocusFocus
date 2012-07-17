package com.soulastral.hocusfocus;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

public class CommonScreen extends Activity {
	   public void onCreate(Bundle savedInstanceState) 
	    {  
		   super.onCreate(savedInstanceState);
		   savedInstanceState=getIntent().getExtras();
		   setContentView(savedInstanceState.getInt("layout"));
	    }

	   protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			KeyguardManager kgMgr = 
	    	    (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			
	    	boolean showing = kgMgr.inKeyguardRestrictedInputMode();

	    	if(showing==false && pm.isScreenOn())
			Home.splash.start();
		}
		   public void onBackPressed() {
				// TODO Auto-generated method stub
				super.onBackPressed();
				Home.splash.pause();
				finish();
			}
		   @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			if(Home.splash.isPlaying())
			Home.splash.pause();
		}
}
