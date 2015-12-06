package com.recoverytoolbox.jtrax;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import com.firebase.client.*;
import com.firebase.client.core.*;
import android.support.v7.app.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{
    private Firebase myFirebaseRef;
	private EditText uname;
	private EditText pwd;
	private TextView errBox;
	private String errMsg;
	private ProgressBar spinner1;
	private Toolbar tb1;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupfields();
		setSupportActionBar(tb1);
		Firebase.setAndroidContext(this);
		
	}
	
	
	public void loginUser(View v)
	{
		
		myFirebaseRef= new Firebase("https://burning-torch-1239.firebaseIO.com");
		spinner1.setVisibility(spinner1.VISIBLE);
		
		/*authenticate with firebase*/
		myFirebaseRef.authWithPassword(uname.getText().toString(), pwd.getText().toString(), new Firebase.AuthResultHandler() {
				
			@Override
				public void onAuthenticated(AuthData authData) {
					//System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
						userRedirect();
					}
					
				@Override
				public void onAuthenticationError(FirebaseError firebaseError) {
					// there was an error
					spinner1.setVisibility(spinner1.GONE);
					errMsg = firebaseError.getMessage();
					showError(errMsg);
					
				}
			});
		
	}//end loginuser
	
	private void userRedirect()
	{
		Intent i = new Intent(this,UserHomeActivity.class);
		startActivity(i);
	}
	
	private void showError(String msg){
		
		errBox.setVisibility(0);
		errBox.setText(msg);
	}
	
	private void setupfields()
	{
		errBox = (TextView) findViewById(R.id.tvErrors);
		uname = (EditText) findViewById(R.id.username);
		pwd = (EditText) findViewById(R.id.password);
		spinner1 = (ProgressBar) findViewById(R.id.pbSpin);
		tb1 = (Toolbar) findViewById(R.id.my_toolbar);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate actionmenu.xml 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionmenu, menu);
		return true;
	}
	

	
	
	
	

	
}


