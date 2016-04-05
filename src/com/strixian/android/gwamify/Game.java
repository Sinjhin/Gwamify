package com.strixian.android.gwamify;

import com.strixian.android.gwamify.utility.GwamCalculator;
import com.strixian.android.gwamify.utility.TextInputListener;
import com.strixian.android.gwamify.utility.TextInputListener.onTextChangedListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;

public class Game extends Activity implements onTextChangedListener
{
	private static final String TAG = Game.class.getSimpleName();
	
	private EditText editTextInput;
	private TextView textViewDisplay;
	
	//this is default branch
	
	// Set the length you want the game (in seconds) here.
	private static int GAME_TIME_LIMIT = 60;
	
	private GwamCalculator gwamCalc;
	public float gwamScore;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.game);
	    
	    gwamCalc = new GwamCalculator();
	    gwamScore = 0;
	    
	    
	    // Get a reference to the objects we need.
	    textViewDisplay = (TextView) findViewById(R.id.textViewDisplay);
	    editTextInput = (EditText) findViewById(R.id.editTextInput);
	    editTextInput.requestFocus();
	    
	    // Going to try to do a scroller animation here.
	    // Scroller scroller = new Scroller(textViewDisplay.getContext());
	    //scroller.startScroll(0, 0, 0, 50, 2500);
	    
	    // This sets the keyboard to not be full screened when the EditText view is focused.
	    editTextInput.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
	    // This brings the keyboard up manually.
	    //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	    //imm.showSoftInput(this.getCurrentFocus(), 0);
	    
	    // Set the text of our textViewDisplay.
	    textViewDisplay.setText(R.string.testWords);
	    
	    // Setup our TextInputListener so we can record user input.
	    editTextInput.addTextChangedListener(new TextInputListener(this));
	    
	    Log.d(TAG, "Game Activity was just started");	    
	    
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(TAG, "Game Activity was just stopped");
	}

	@Override
	public void onTextChanged()
	{
		//Scrolls text as you type
		textViewDisplay.scrollTo(0, editTextInput.getScrollY());
		
		//changes correct text to BLUE!
		CharSequence text =  textViewDisplay.getText();
		Editable text2 = editTextInput.getText();
		Spannable textToSpan = new SpannableString(text);
	
		boolean stillCorrect = false;
		int start = 0;
		int end = text2.length()-1;

		for (int i=0; i<text2.length(); i++)
		{
			if (text.charAt(i) == text2.charAt(i))
			{
				if (!stillCorrect)
				{
					start = i;
					stillCorrect = true;
				}
			}
			else
			{
				if (stillCorrect)
					end = i;
				break;			
			}
		}
		if (stillCorrect)
			textToSpan.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		textViewDisplay.setText(textToSpan);
		
		// Code for when text changes goes here.
		if(!gwamCalc.getIsTimed())
		{
			// This is the first keypress of the game.
			gwamCalc.setIsTimed(true);
			gwamCalc.startTiming();
			
			// For now we will use a delayed Runnable to end the game.
			new Handler().postDelayed(new Runnable()
	        {
	        	public void run()
	        	{
	        		// Game has ended. Calculate the score and move on.
	        		gwamCalc.stopTiming();
	        		Log.d(TAG, "Elapsed time in ms: " + gwamCalc.getElapsedMS());
	        		Log.d(TAG, "Elapsed time in sec: " + gwamCalc.getElapsedSeconds());
	        		Log.d(TAG, "Elapsed time in minutes: " + gwamCalc.getElapsedMinutes());
	        		gwamScore = gwamCalc.Calculate();
	        		Log.d(TAG, "Your GWAM Score is: " + gwamScore);
	        		
	        		// Putting score into SharedPrefs.
	        		final SharedPreferences settings = getSharedPreferences("MYSETTINGS", 0);
	                float highScore = settings.getFloat("highScore", 0);
	                float lastScore = settings.getFloat("lastScore", 0);
	                SharedPreferences.Editor editor = settings.edit();
	                editor.putFloat("lastScore", gwamScore);
	                if(gwamScore > highScore)
	                {
	                	Log.d(TAG, "You just got your highest score YET!");
	                	editor.putFloat("highScore", gwamScore);
	                }
	        		editor.commit();
	        		
	        		// Reseting things and moving to EndGame Activity.
	        		gwamCalc.reset();
	                Intent startEndGame = new Intent(Game.this, EndGame.class);
	                Game.this.startActivity(startEndGame);
	                Game.this.finish();
	        	}
	        	
	        }, GAME_TIME_LIMIT * 1000);
		}
		else
		{
			
		}
		gwamCalc.upKeysPressed();
	}

}
