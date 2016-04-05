package com.strixian.android.gwamify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Leaderboard extends Activity
{
	private static final String TAG = Leaderboard.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.leaderboard);
	    
	    Log.d(TAG, "Leaderboard Activity just started");
	}

}
