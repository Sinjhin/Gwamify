package com.strixian.android.gwamify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Settings extends Activity
{
	private static final String TAG = MainMenu.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings);
	
	    Log.d(TAG, "Settings Activity just started");
	}

}
