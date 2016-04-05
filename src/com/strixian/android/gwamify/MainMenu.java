package com.strixian.android.gwamify;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity
{
	private static final String TAG = MainMenu.class.getSimpleName();
	
	Button buttonNewGame;
	Button buttonSetKeyboard;
	Button buttonLeaderboards;
	Button buttonSettings;
	Button buttonQuit;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mainmenu);
	    
	    Log.d(TAG, "Main Menu Activity was just started");
	    
	    //EasyTracker myTracker = (EasyTracker) EasyTracker.getTracker();
	    
	    // Instantiate our buttons.
		buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
		buttonSetKeyboard = (Button) findViewById(R.id.buttonSetKeyboard);
		buttonLeaderboards = (Button) findViewById(R.id.buttonLeaderboards);
		buttonSettings = (Button) findViewById(R.id.buttonSettings);
		buttonQuit = (Button) findViewById(R.id.buttonQuit);
	    
	    ////////////////////////////////////////////////
	    ////////// Make the buttons do things //////////
	    ////////////////////////////////////////////////
	    
	    buttonNewGame.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				Log.d(TAG, "New Game Button Pressed");
				
				//EasyTracker.getTracker().trackEvent("ButtonPress", "New Game", "pressed", 1);
				
				final Intent startGameIntent = new Intent(MainMenu.this, Game.class);
				startActivity(startGameIntent);
			}
		});
	    
	    buttonSetKeyboard.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				// We should eventually find a way to automatically get the keyboard type.
				Log.d(TAG, "Set Keyboard Button Pressed");
				
				final Intent startSetKeyboardIntent = new Intent(MainMenu.this, SetKeyboard.class);
				startActivity(startSetKeyboardIntent);
			}
		});
	    
	    buttonLeaderboards.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				// We need to figure out a good leaderboard api. NOT OPENFEINT!!!
				Log.d(TAG, "Leaderboards Button Pressed");
				
				final Intent startLeaderboardIntent = new Intent(MainMenu.this, Leaderboard.class);
				startActivity(startLeaderboardIntent);
			}
		});
	    
	    buttonSettings.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				Log.d(TAG, "Settings Button Pressed");
				
				final Intent startSettingsIntent = new Intent(MainMenu.this, Settings.class);
				startActivity(startSettingsIntent);
			}
		});
	    
	    buttonQuit.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				Log.d(TAG, "Quit Button Pressed");
				MainMenu.this.finish();
			}
		});
	
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
		Log.d(TAG, "Main Menu Activity was just stopped");
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		if (GwamifyApp.mediaPlayer != null)
		{
			// This is normally released @ MainMenu.onDestroy();
			GwamifyApp.mediaPlayer.release();
			GwamifyApp.mediaPlayer = null;
		}
	}

}
