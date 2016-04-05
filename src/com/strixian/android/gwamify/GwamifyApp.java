package com.strixian.android.gwamify;

import java.io.IOException;

import com.strixian.android.gwamify.utility.GwamCalculator;

import android.app.Application;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class GwamifyApp extends Application
{
	// This is an over-encompasing class that is above all other activities.
	// I am not sure what we can use it for yet, but we might think of something fun.
	// It can maintain global application states. *shrug*
	
	private static final String TAG = GwamifyApp.class.getSimpleName();
	
	public static MediaPlayer mediaPlayer = new MediaPlayer();
	
	public void onCreate()
	{
		Log.d(TAG, "GwamifyApp was just created.");
		
		// Lets play some tunes.
		
		Uri path = Uri.parse("android.resource://com.strixian.android.gwamify/" + R.raw.menumusic);
		
		try {
			mediaPlayer.setDataSource(this, path);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// We need to prepare the mediaPlayer async.
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//mediaPlayer.start();
		
		//Intent startGwamify = new Intent(GwamifyApp.this, Splash.class);
		//GwamifyApp.this.startActivity(startGwamify);
		
	}
	
	public void onStart()
	{
		//EasyTracker.getTracker().startNewSession();
	}
	
	public void onStop()
	{
		//EasyTracker.getTracker().stopSession();
	}
	
	public void onDestroy()
	{
		if (mediaPlayer != null)
		{
			// This is normally released @ MainMenu.onDestroy();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
}
