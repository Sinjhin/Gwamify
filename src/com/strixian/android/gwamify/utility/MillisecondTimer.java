package com.strixian.android.gwamify.utility;

import android.os.SystemClock;

public class MillisecondTimer
{
	// This will be a reusable helper class to handle getting small and very precise
	// time measurements.
	
	private float initTime;
	private float finalTime;
	private float elapsedTime;
	private boolean isValidInterval;
	
	public MillisecondTimer()
	{
		initTime = 0;
		finalTime = 0;
		elapsedTime = 0;
		isValidInterval = true;
	}
	
	public void startTimer()
	{
		initTime = getTimeTick();
		isValidInterval = false;
	}
	
	// This will return the time in ms since startTimer().
	public void stopTimer()
	{
		finalTime = getTimeTick();
		elapsedTime =  finalTime - initTime;
		
		if(elapsedTime < 0)
		{
			isValidInterval = false;
		}
		else
		{
			isValidInterval = true;
		}
		
	}
	
	public float getElapsedMS()
	{
		if(isValidInterval)
		{
			return elapsedTime;
		}
		else
		{
			return -1;
		}
		
	}
	
	// This will return the time in sec stored after last stopTimer().
	public float getElapsedSeconds()
	{
		if(isValidInterval)
		{
			return (elapsedTime / 1000);
		}
		else
		{
			return -1;
		}
	}
	
	// This will return the time in sec stored after last stopTimer().
	public float getElapsedMinutes()
	{
		if(isValidInterval)
		{
			return (elapsedTime / 60000);
		}
		else
		{
			return -1;
		}
	}
	
	public float getTimeTick()
	{
		return SystemClock.uptimeMillis();
	}
	
	public boolean isIntervalValid()
	{
		return isValidInterval;
	}
	
	public void resetTimer()
	{
		initTime = 0;
		finalTime = 0;
		elapsedTime = 0;
		isValidInterval = true;
	}

}
