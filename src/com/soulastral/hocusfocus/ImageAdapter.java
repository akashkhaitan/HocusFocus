package com.soulastral.hocusfocus;

import android.R.anim;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	 private Integer[] mThumbIds = {R.drawable.stage1hocus, R.drawable.stage2hocus,
			 R.drawable.stage3hocus, R.drawable.stage4hocus,
			 R.drawable.stage5hocus, R.drawable.stage6hocus
	 };
	 public int[] stapar;
    public ImageAdapter(Context c,int []stapar) {
        mContext = c;
        this.stapar=stapar;
        int i=0;
        while(i<stapar.length)
        {
        	if(stapar[i]==1)
        	mThumbIds[i]=R.drawable.paar;
        	i++;
        }
        
    }

    public int getCount() {
        return mThumbIds.length;
      
    }


	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        //    imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mThumbIds[position]);
	    //    imageView.startAnimation(AnimationUtils.loadAnimation(mContext,anim.slide_in_left));
	        return imageView;
	    }

	    // references to our images
	   

	}