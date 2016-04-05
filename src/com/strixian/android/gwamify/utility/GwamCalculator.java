package com.strixian.android.gwamify.utility;

import android.util.Log;

public class GwamCalculator extends MillisecondTimer
{
	// This class will be in charge of using the Millisecond timer class to calculate
	// the gwam of someone playing the game.
	
	private boolean isTimed;
	private int keysPressed;
	
	public GwamCalculator()
	{
		isTimed = false;
		keysPressed = 0;
	}
	
	public float Calculate()
	{
		float gwamScore = 0;
		// Calculations for GWAM go here.
		Log.d("GwamCalculator", "Calculating gwamScore! " + keysPressed + " keys were pressed.");
		
		if(getElapsedMS() != -1)
		{
			gwamScore = (((float) keysPressed) / 5) / getElapsedMinutes();
		}
		else
		{
			gwamScore = -1;
		}
		
		return gwamScore;
	}
	
	public void reset()
	{
		setIsTimed(false);
		keysPressed = 0;
		resetTimer();
	}
	
	public void startTiming()
	{
		startTimer();
		Log.d("GwamCalculator", "Timer just started!");
		
	}
	
	public void stopTiming()
	{
		stopTimer();
		Log.d("GwamCalculator", "Timer stopped.");
	}
	
	public boolean getIsTimed()
	{
		return isTimed;
	}
	
	public void setIsTimed(boolean b)
	{
		isTimed = b;
	}

	public void upKeysPressed()
	{
		keysPressed++;
	}
	
}
