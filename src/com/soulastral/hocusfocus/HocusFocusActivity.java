package com.soulastral.hocusfocus;

import java.io.Serializable;
import java.util.ArrayList;

import com.samsung.sdraw.CanvasView;
import com.samsung.sdraw.PenSettingInfo;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class HocusFocusActivity extends Activity{ //implements OnGestureListener{
    /** Called when the activity is first created. */
    ViewSwitcher v;
 //   CanvasView vi1;
    MediaPlayer correctsou,incorrectsou,win,loose,click;
    CustomView vi1;
    ImageView vi;
    Button but;
    String strcon="";
    static TextView correct,correcttotal,wrong,wrongtotal,lookback,lookbacktotal,score;
    int correctint=0,wrongint=0,lookbackint=0,totalcorrect=0,allowedincorrect=0,allowedlookback=0,scoreint=0;
  //  ArrayList<int[][]> arrayList= new ArrayList<int[][]>();
  //  int arr[][]={{55,48,60,51},{33,79,55,97},{23,50,28,54},{86,75,98,86},{74,30,78,45}};
	int arr[][];
			int details[],position;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        correctsou = MediaPlayer.create(this, R.raw.correct);
        incorrectsou = MediaPlayer.create(this, R.raw.wrong);
        win = MediaPlayer.create(this, R.raw.win);
        loose = MediaPlayer.create(this, R.raw.loose);
        click = MediaPlayer.create(this, R.raw.click);
        setContentView(R.layout.main);
   

        
        
        savedInstanceState=getIntent().getExtras();
       details=savedInstanceState.getIntArray("details");
       position=details[0];
       arr=StageScreen.arrayList.get(position);
       totalcorrect=details[3];
       allowedincorrect=details[4];
       allowedlookback=details[5];
       
       
       SharedPreferences settings = getSharedPreferences("scores", 0);
        correct=(TextView)findViewById(R.id.correct);
        correcttotal=(TextView)findViewById(R.id.correcttotal);
        wrong=(TextView)findViewById(R.id.wrong);
        wrongtotal=(TextView)findViewById(R.id.wrongtotal);
        lookback=(TextView)findViewById(R.id.lookback);
        lookbacktotal=(TextView)findViewById(R.id.lookbacktotal);
        score=(TextView)findViewById(R.id.score);
 
        but=(Button)findViewById(R.id.but);
        v=(ViewSwitcher)findViewById(R.id.viewSwitcher1);
        v.setSoundEffectsEnabled(true);
        
        correcttotal.setText(String.valueOf(totalcorrect));
        wrongtotal.setText(String.valueOf(allowedincorrect));
        lookbacktotal.setText(String.valueOf(allowedlookback));
        score.setText(String.valueOf(settings.getInt("score", 0)));
        
        vi=new ImageView(getApplicationContext());
       // vi.setBackgroundResource(R.drawable.stage1hocus);
        vi.setBackgroundResource(details[1]);
        vi1=new CustomView(getApplicationContext());
    /*    vi1=new CanvasView(getApplicationContext()){
    
        	public boolean onTouchEvent(MotionEvent event) {
        		// TODO Auto-generated method stub
        		 float startx=0;
        		 float starty=0;
        		if (event.getAction() == MotionEvent.ACTION_DOWN) {
        			
        			changeModeTo(CanvasView.PEN_MODE);
        
        			startx = ( (event.getX()/getWidth())*100);
        			starty = ( (event.getY()/getHeight())*100);
        		   
        		    		check(startx,starty);
     

        		  } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
        			  changeModeTo(CanvasView.INVISIBLE);
        			  
        			 
        		  }    		  
        		
        		  else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
        			  	
          		
        		  }
        		return super.onTouchEvent(event);
        	}
        };*/
        PenSettingInfo pen=new PenSettingInfo(getApplicationContext());
        pen.setPenColor(0x001B3F8B);
        pen.setPenAlpha(0x0A000000);
        pen.setPenWidth(25);
        vi1.setPenSettingInfo(pen);
      //  vi1.setBackgroundResource(R.drawable.stage1focus);
        vi1.setBackgroundResource(details[2]);
        v.addView(vi,0);
        v.addView(vi1,1);
    }
	public void check(float a,float b)
	{
		int temp=0;
		
		for(int i=0;i<arr.length;i++)
		{
			if(a>arr[i][0] && b>arr[i][1] && a<arr[i][2] && b<arr[i][3])
			{
				if(strcon.indexOf(String.valueOf(i))==-1)
				{
					correctsou.start();
					correctint=correctint+1;
					correct.setText(String.valueOf(correctint));
					updatescore();
					strcon=strcon+String.valueOf(i);
					temp=1;
				}
				else
					Toast.makeText(getApplicationContext(), "You Already Marked That", Toast.LENGTH_LONG).show();
					temp=1;
			}
		
		}
		if(temp==0)
		{
			incorrectsou.start();
			wrongint=wrongint+1;
			wrong.setText(String.valueOf(wrongint));
		}
	}
	
	void updatescore()
	{
	       SharedPreferences settings = getSharedPreferences("scores", 0);
	       scoreint=settings.getInt("score", 0);
	       scoreint=scoreint+100;
	       SharedPreferences.Editor editor = settings.edit();
	       editor.putInt("score", scoreint);
	       score.setText(String.valueOf(settings.getInt("score", 0)));
	       // Commit the edits!
	       editor.commit();
	}
	
	void count()
	{
		if(wrongint>allowedincorrect)
		{
			loose.start();
			Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
			finish();	//To finish the current activity
		}
		if(correctint==totalcorrect)
		{
			win.start();
			Toast.makeText(getApplicationContext(), "Well Done", Toast.LENGTH_LONG).show();
			scoreint=scoreint+allowedincorrect-wrongint+allowedlookback-lookbackint*100;
			
			SharedPreferences settings = getSharedPreferences("scores", 0);
			 SharedPreferences.Editor editor = settings.edit();
		       editor.putInt("score", scoreint);
		       score.setText(String.valueOf(settings.getInt("score", 0)));
		       // Commit the edits!
		       editor.commit();
		     
		       //Stage Paar saving in internal storage
		       SharedPreferences stasettings = getSharedPreferences("stages", 0);
			   SharedPreferences.Editor staeditor = stasettings.edit();
			   String posString=String.valueOf(position)+" ";
			   String tempstr=stasettings.getString("stagespaar", "");
			   if(tempstr.indexOf(posString)==-1)
			   {
				   tempstr=tempstr+posString;
			   		staeditor.putString("stagespaar",tempstr);
			   		staeditor.commit();
			   }
			finish();
		}
		
	}
	
	
	
	class CustomView extends CanvasView
	{
		public CustomView(Context arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
	
    		
    	public boolean onTouchEvent(MotionEvent event) {
    		// TODO Auto-generated method stub
    		 float startx=0;
    		 float starty=0;
    		 int downTime,eventTime;
    		 
    		if (event.getAction() == MotionEvent.ACTION_DOWN && event.getPressure()<10) {
    			
    			changeModeTo(CanvasView.PEN_MODE);
    
    			startx = ( (event.getX()/getWidth())*100);
    			starty = ( (event.getY()/getHeight())*100);
    		  
    		    		check(startx,starty);
    		    		count();

    		  } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
    			  changeModeTo(CanvasView.INVISIBLE);
    			  
    			 
    		  }    		  
    		
    		return super.onTouchEvent(event);
    	}
		
	}
	public void func(View view)
	{
		if((but.getText()).equals("Focus >>"))
		{
			nextView();
			but.setText("<< Hocus");
		}
		else if((but.getText()).equals("<< Hocus"))
		{
			lookbackint=lookbackint+1;
			lookback.setText(String.valueOf(lookbackint));
			previousView();
			but.setText("Focus >>");
			
			if(lookbackint>allowedlookback)
			{
				loose.start();
				Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
				finish();	//Finish the current Activity
			}
		}
		
	}
	
	private void previousView() {
		
		//Previous View
		click.start();
		v.setInAnimation(this,R.anim.in_animation);
		v.setOutAnimation(this, R.anim.out_animation1);
		v.showPrevious();
	
}
private void nextView() {
	
		//Next View
		click.start();
		v.setInAnimation(this, R.anim.in_animation);
		v.setOutAnimation(this, R.anim.out_animation);
		v.showNext();
	
}
	
}