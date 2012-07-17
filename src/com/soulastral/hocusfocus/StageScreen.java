package com.soulastral.hocusfocus;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class StageScreen extends Activity {

	 MediaPlayer click;
	GridView gridview;
	int arr[]= new int[]{-99,-99,-99,-99,-99,-99}; //No. of Stages
	 static ArrayList<int[][]> arrayList= new ArrayList<int[][]>();
	    int stage1region[][]={{53,46,63,56},{33,79,55,97},{23,50,28,54},{86,75,98,86},{74,30,78,45}};
	    int stage2region[][]={{44,42,61,67},{41,3,52,25},{63,29,74,48},{27,31,31,36},{24,53,30,66},{1,46,9,62},{53,73,96,98}};
	    int stage3region[][]={{38,73,45,87},{63,70,79,78},{90,36,98,47},{63,26,76,37},{65,2,82,15},{29,51,54,63},{55,43,68,55}};
	    int stage4region[][]={{38,73,47,84},{62,12,80,23},{15,17,36,33},{62,50,67,59},{89,28,98,57},{49,69,58,77}};
	    int stage5region[][]={{42,42,53,48},{22,47,37,57},{65,66,70,77},{1,42,8,66},{78,12,94,28},{53,52,64,60}};
	    int stage6region[][]={{32,45,36,52},{30,76,37,81},{71,55,79,62},{90,45,99,58},{23,43,30,48},{7,55,22,65}};
		    int stagedetails[][]={{0,R.drawable.stage1hocus,R.drawable.stage1focus,5,3,3},
		    		{1,R.drawable.stage2hocus,R.drawable.stage2focus,7,3,2},
		    		{2,R.drawable.stage3hocus,R.drawable.stage3focus,7,2,2},
		    		{3,R.drawable.stage4hocus,R.drawable.stage4focus,6,2,1},
		    		{4,R.drawable.stage5hocus,R.drawable.stage5focus,6,1,1},
		    		{5,R.drawable.stage6hocus,R.drawable.stage6focus,6,0,1}};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        click = MediaPlayer.create(this, R.raw.click);
        
        setContentView(R.layout.stages);
        
        arrayList.add(stage1region);
        arrayList.add(stage2region);
        arrayList.add(stage3region);
        arrayList.add(stage4region);
        arrayList.add(stage5region);
        arrayList.add(stage6region);
        
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this,arr));
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
       //         Toast.makeText(StageScreen.this, "" + position, Toast.LENGTH_SHORT).show();
            	Home.splash.pause();
            	click.start();
		        Intent in=new Intent(StageScreen.this,HocusFocusActivity.class);
		        
		        in.putExtra("details",stagedetails[position]);
		        startActivity(in);
            }
        });
    }
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		KeyguardManager kgMgr = 
    	    (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
    	boolean showing = kgMgr.inKeyguardRestrictedInputMode();
    	PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
    	if(showing==false && pm.isScreenOn())
		Home.splash.start();
		
		
		SharedPreferences stasettings = getSharedPreferences("stages", 0);
	//	stasettings.edit().clear().commit();
		String stagepar_string=stasettings.getString("stagespaar", "");
		
		
		//string to array
		try
		{
			while(stagepar_string.indexOf(" ")!=-1)
			{
				int i=stagepar_string.indexOf(" ");
				int number=Integer.parseInt(stagepar_string.substring(0, i));				arr[number]=1;
				stagepar_string=stagepar_string.substring(i+1);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			
		}
		gridview.setAdapter(new ImageAdapter(this, arr));
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(Home.splash.isPlaying()==true)
		Home.splash.pause();
	}
}
