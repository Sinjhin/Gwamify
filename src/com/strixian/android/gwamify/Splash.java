package com.strixian.android.gwamify;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class Splash extends Activity
{
	private static final String TAG = Splash.class.getSimpleName();
	
	// This is the ammount of time in seconds that you want the
	// splash screen to display.
	private final int SPLASH_DISPLAY_SECONDS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Log.d(TAG, "Splash Activity just started");
        
        GwamifyApp.mediaPlayer.start();
        
        // It might not be totally neccesary to do it here, but I am creating and filling the SharedPrefs.
        final SharedPreferences settings = getSharedPreferences("MYSETTINGS", 0);
        int appStarts = settings.getInt("appStarts", 0);
        float highScore = settings.getFloat("highScore", 0);
        float lastScore = settings.getFloat("lastScore", 0);
        SharedPreferences.Editor editor = settings.edit();
		editor.putInt("appStarts", appStarts++);
		editor.putFloat("highScore", highScore);
		editor.putFloat("lastScore", lastScore);
		editor.commit();
        
        // We create a Runnable() to add a delay before starting the next activity.
        new Handler().postDelayed(new Runnable()
        {
        	public void run()
        	{
    			Intent startMainMenuIntent = new Intent(Splash.this, MainMenu.class);
    			Splash.this.startActivity(startMainMenuIntent);
        		
    			// This ensures that the back button will not go back to the splash screen.
        		Splash.this.finish();
        	}
        }, SPLASH_DISPLAY_SECONDS * 1000);
        
    }
    
	public void onStart()
	{
		super.onStart();
		//EasyTracker.getInstance().activityStart(this);
	}
	
	public void onStop()
	{
		super.onStop();
		//EasyTracker.getInstance().activityStop(this);
	}
    
}
