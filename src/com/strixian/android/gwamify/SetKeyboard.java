package com.strixian.android.gwamify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SetKeyboard extends Activity
{
	private static final String TAG = SetKeyboard.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setkeyboard);
	    
	    Log.d(TAG, "SetKeyboard Activity just started");
	}

}
