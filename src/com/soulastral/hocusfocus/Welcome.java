package com.soulastral.hocusfocus;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SlidingDrawer;

public class Welcome extends Activity {
	
	int x;
	Thread logoTimer;
	 public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash); 
	        
	    //    mpSlash = MediaPlayer.create(this, R.raw.splsh);
	    //	mpSlash.start();
	    	
	        
	        logoTimer = new Thread(){
	        	public void run(){
	        		try{
	        			sleep(1000);        		
	        		runOnUiThread(new Thread()
	        		{
	        			
	        			public void run()
	        			{
	        				startActivity(new Intent(Welcome.this,Home.class));
	        			}
	        		});
	        			
	        		}catch(InterruptedException e)
	        		{
	        			e.printStackTrace();
	        		}
	        	finally{
	        		finish();
	        	}
	        }
	        
		};
	   logoTimer.start();
	    }
	 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	 @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		logoTimer.interrupt();
	}
	 
	 
}
