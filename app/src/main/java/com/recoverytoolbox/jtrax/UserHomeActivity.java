package com.recoverytoolbox.jtrax;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.support.v7.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import com.firebase.client.*;

public class UserHomeActivity extends AppCompatActivity
{
	private Firebase FBref;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);
		FBref = new Firebase(getResources().getString(R.string.firebase_url));
		
		//Toolbar t = (Toolbar) findViewById(R.id.my_toolbar);
		//setSupportActionBar(t);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate actionmenu.xml 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionmenu, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logoff();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	/*method to log off users*/
	public void logoff()
	{
		FBref.unauth();
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
		finish();
	}
}
