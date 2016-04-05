package com.strixian.android.gwamify.utility;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public class TextInputListener implements TextWatcher
{
	onTextChangedListener textListener;
	
	public TextInputListener(Activity caller)
	{
		try
		{
			textListener = (onTextChangedListener) caller;
		} catch (ClassCastException e)
		{
			throw new ClassCastException(caller.toString() + " must implement onTextChangedListener");
		}
		
	}
	
	@Override
	public void afterTextChanged(Editable s)
	{
		//Log.d("textInputListener", "afterTextChanged(Editable s) was called");
		textListener.onTextChanged();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{

	}
	
	public interface onTextChangedListener
	{
		public void onTextChanged();
	}

}
