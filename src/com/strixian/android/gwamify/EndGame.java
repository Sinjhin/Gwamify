package com.strixian.android.gwamify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EndGame extends Activity
{
	private static final String TAG = EndGame.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.endgame);
	    
	    Log.d(TAG, "End Game Activity just started");
	    
	    final SharedPreferences settings = getSharedPreferences("MYSETTINGS", 0);
        float highScore = settings.getFloat("highScore", 0);
        float lastScore = settings.getFloat("lastScore", 0);
        
        // Temporary Score display.
        Button buttonHighScore = (Button) findViewById(R.id.buttonHighScore);
        buttonHighScore.setText("High Score: " + String.format("%.1f", highScore));
        Button buttonLastScore = (Button) findViewById(R.id.buttonLastScore);
        buttonLastScore.setText("Last Score: " + String.format("%.1f", lastScore));
        
        Button buttonMainMenu = (Button) findViewById(R.id.buttonMainMenu);
        buttonMainMenu.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				Intent startMainMenu = new Intent(EndGame.this, MainMenu.class);
				startActivity(startMainMenu);
				EndGame.this.finish();
			}
		});
	
	}

}
