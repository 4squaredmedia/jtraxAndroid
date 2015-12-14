package com.recoverytoolbox.jtrax;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import android.support.v7.app.*;
import android.widget.*;
import android.support.v7.app.ActionBarActivity;
import com.firebase.client.*;
import java.util.*;
import android.app.AlertDialog;
import android.preference.*;


public class RegistrationActivity extends ActionBarActivity
{
	
	private Firebase myFirebaseRef;
	private ProgressBar mProgessSpinner;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
		setupfields();
		Firebase.setAndroidContext(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionmenu,menu);
		return true;
	}
	
	
	
	private void setupfields()
	{
		mProgessSpinner = (ProgressBar)findViewById(R.id.progessSpinner);
	}
	
	public void createUser(View v)
	{
		
		EditText email= (EditText) findViewById(R.id.rEmail);
		EditText pwd1 = (EditText) findViewById(R.id.rPassword1);
		EditText pwd2 = (EditText) findViewById(R.id.rPassword2);
		String pwd="";
		String txtPwd1 = pwd1.getText().toString();
		String txtPwd2= pwd2.getText().toString();
		
		
		if(txtPwd1.compareTo(txtPwd2)==0)
		{
			pwd = txtPwd1;
			mProgessSpinner.setVisibility(mProgessSpinner.VISIBLE);
			myFirebaseRef= new Firebase("https://burning-torch-1239.firebaseIO.com");
			myFirebaseRef.createUser(email.getText().toString(), pwd, new ValueResultHandler());
		}
		else
		{
			showErrorDialog("Passwords Do Not Match");
		}
		
		
	}
	
	private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
			.setTitle("Error")
			.setMessage(message)
			.setPositiveButton(android.R.string.ok, null)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.show();
    }
	
	private void redirectUser()
	{
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		
	}
	
	private class ValueResultHandler implements Firebase.ValueResultHandler<Map<String, Object>>
	{

		@Override
		public void onSuccess(Map<String, Object> result)
		{
			mProgessSpinner.setVisibility(mProgessSpinner.GONE);
			redirectUser();
		}

		@Override
		public void onError(FirebaseError fbError)
		{
			mProgessSpinner.setVisibility(mProgessSpinner.GONE);
			showErrorDialog(fbError.toString());
		}

	}
	
}
