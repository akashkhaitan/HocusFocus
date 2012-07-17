package com.soulastral.hocusfocus;

import java.io.IOException;
import java.util.ArrayList;

import com.soulastral.hocusfocus.R.drawable;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Activity {

	  Button stage_btn,credit_btn,reset_btn,instruction_btn;
	  Bitmap stage_bm,credit_bm,reset_bm,instruction_bm;
	  static MediaPlayer click,splash;
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);

	        splash= MediaPlayer.create(this, R.raw.splsh);
        	click = MediaPlayer.create(this, R.raw.click);
	    	
	        setContentView(R.layout.home);
	        stage_btn=(Button)findViewById(R.id.stages);
	        credit_btn=(Button)findViewById(R.id.credits);
	        reset_btn=(Button)findViewById(R.id.reset);
	        instruction_btn=(Button)findViewById(R.id.instructions);
	        
	        
	        stage_bm = BitmapFactory.decodeResource(getResources(),R.drawable.stages);
	        credit_bm = BitmapFactory.decodeResource(getResources(),R.drawable.credits);
	        reset_bm = BitmapFactory.decodeResource(getResources(),R.drawable.scores);
	        instruction_bm = BitmapFactory.decodeResource(getResources(),R.drawable.instructions);
	        
	        
	        stage_btn.setOnTouchListener(new OnTouchListener() {            
	            public boolean onTouch(View v, MotionEvent event) {
	                int action = event.getAction();
	                if (action == MotionEvent.ACTION_DOWN && stage_bm.getPixel((int)event.getX(),(int)event.getY())!=0)
	                
	                {
	                	click.start();
	                //	Toast.makeText(getApplicationContext(), "God", Toast.LENGTH_LONG).show();
	                	 stage_btn.setSelected(true);
	                	
	                }
	                if (action == MotionEvent.ACTION_UP)
	                {
	                	 stage_btn.setSelected(false);
	                	 Intent in=new Intent(Home.this,StageScreen.class);
	         	        startActivity(in);
	                }
	               
	                return true;
	                
	            }
	            
	           
	        });
	        
	        
	        credit_btn.setOnTouchListener(new OnTouchListener() {            
	            public boolean onTouch(View v, MotionEvent event) {
	                int action = event.getAction();
	                if (action == MotionEvent.ACTION_DOWN && credit_bm.getPixel((int)event.getX(),(int)event.getY())!=0)
	                
	                {
	                	click.start();
	                //	Toast.makeText(getApplicationContext(), "God", Toast.LENGTH_LONG).show();
	                	 credit_btn.setSelected(true);
	                	
	                }
	                if (action == MotionEvent.ACTION_UP)
	                {
	                	 credit_btn.setSelected(false);
	                	 Intent in=new Intent(Home.this,CommonScreen.class);
	                	 in.putExtra("layout",R.layout.credits);
		         	        startActivity(in);
	                }
	              
	                return true;
	            }
	        });
	        
	        
	        
	        reset_btn.setOnTouchListener(new OnTouchListener() {            
	            public boolean onTouch(View v, MotionEvent event) {
	                int action = event.getAction();
	                if (action == MotionEvent.ACTION_DOWN && reset_bm.getPixel((int)event.getX(),(int)event.getY())!=0)
	                
	                {
	                	click.start();
	                //	Toast.makeText(getApplicationContext(), "God", Toast.LENGTH_LONG).show();
	                	 reset_btn.setSelected(true);
	                	
	                }
	                if (action == MotionEvent.ACTION_UP)
	                {
	                	 reset_btn.setSelected(false);
	                }
	                SharedPreferences stasettings = getSharedPreferences("stages", 0);
	                SharedPreferences settings = getSharedPreferences("scores", 0);
	            	settings.edit().clear().commit();
	            	stasettings.edit().clear().commit();
	            	Toast.makeText(getApplicationContext(), "Game Reset Done", Toast.LENGTH_SHORT).show();
	                return true;
	            }
	        });
	        
	        instruction_btn.setOnTouchListener(new OnTouchListener() {            
	            public boolean onTouch(View v, MotionEvent event) {
	                int action = event.getAction();
	                if (action == MotionEvent.ACTION_DOWN && instruction_bm.getPixel((int)event.getX(),(int)event.getY())!=0)
	                
	                {
	                	click.start();
	                //	Toast.makeText(getApplicationContext(), "God", Toast.LENGTH_LONG).show();
	                	 instruction_btn.setSelected(true);
	                	
	                }
	                if (action == MotionEvent.ACTION_UP)
	                {
	                	 instruction_btn.setSelected(false);
	                	 Intent in=new Intent(Home.this,CommonScreen.class);
	                	 in.putExtra("layout",R.layout.instructions);
		         	        startActivity(in);
	                }
	                
	                return true;
	            }
	        });
	        
	    }
	    @Override
	    protected void onResume() {
	    	// TODO Auto-generated method stub
	    		    	super.onResume();
	    		    
	    		    	PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    		    	
	    		    	
	    		    	KeyguardManager kgMgr = 
	    		    	    (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
	    		    	boolean showing = kgMgr.inKeyguardRestrictedInputMode();
	
	    		    	if(showing==false && pm.isScreenOn())
	    		    //	if(splash.isPlaying()==false)
	    		    	{
	    		    		splash.start();		
	    		    		splash.setLooping(true);
	    		    	}
	    }
	    @Override
	    public void onBackPressed() {
	    	// TODO Auto-generated method stub
	    	super.onBackPressed();
	    	if(splash.isPlaying()==true)
	    	splash.stop();
	    }

	    @Override
	    protected void onPause() {
	    	// TODO Auto-generated method stub
	    	super.onPause();
	    	splash.pause();
	    }
}