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
import android.support.v7.app.AlertDialog;

public class LoginActivity extends AppCompatActivity
{
    private Firebase myFirebaseRef;
	private EditText uname;
	private EditText pwd;
	private TextView errBox;
	private String errMsg;
	private ProgressBar spinner1;
	private AuthData mAuthData;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Firebase.setAndroidContext(this);
		//set firebase ref
		myFirebaseRef= new Firebase(getResources().getString(R.string.firebase_url));
		
		//get references to ui elements
		setupfields();
		
		if(myFirebaseRef.getAuth() != null)
		{
		    userRedirect(myFirebaseRef.getAuth());
		}
	}
	
	
	//method used when clicking the login button
	public void loginUser(View v)
	{
		
		spinner1.setVisibility(spinner1.VISIBLE);
		
		/*authenticate with firebase*/
		myFirebaseRef.authWithPassword(uname.getText().toString(), pwd.getText().toString(), new Firebase.AuthResultHandler() {
				
			@Override
				public void onAuthenticated(AuthData authData) {
					//System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
					mAuthData = authData;
					pwd.setText("");
					userRedirect(mAuthData);
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
	
	
	/*method to log off users*/
	public void logoff()
	{
		if(mAuthData != null)
		{
			myFirebaseRef.unauth();
		}
	}
	
	
	/*method to redirect users to user home*/
	private void userRedirect(AuthData a1)
	{
		String uid = a1.getUid();
		Intent i = new Intent(this,UserHomeActivity.class);
		i.putExtra("com.recoverytoolbox.jtrax.uid",uid);
		startActivity(i);
		finish();
	}
	
	
	private void showError(String msg){
		
		new AlertDialog.Builder(this)
			.setTitle("Error")
			.setMessage(msg)
			.setPositiveButton(android.R.string.ok, null)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.show();
	}
	
	private void setupfields()
	{
		errBox = (TextView) findViewById(R.id.tvErrors);
		uname = (EditText) findViewById(R.id.username);
		pwd = (EditText) findViewById(R.id.password);
		spinner1 = (ProgressBar) findViewById(R.id.pbSpin);
		//tb1 = (Toolbar) findViewById(R.id.my_toolbar);
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
	
	public void registerUser(View v)
	{
		Intent i = new Intent(this,RegistrationActivity.class);
		startActivity(i);
		
	}
	
	/*password reset method call*/
	public void sendPasswordReset(View v)
	{
		if(uname.getText()==null || uname.getText().length()==0)
		{
			uname.setError("enter your email address");
		}else{
		
		myFirebaseRef.resetPassword(uname.getText().toString()
		,new Firebase.ResultHandler(){
			@Override
			public void onSuccess()
			{
				String msg = "Email sent with temporary password";
				showError(msg);
			}
			
			@Override
			public void onError(FirebaseError e1)
			{
				String msg = e1.getMessage();
				showError(msg);
			}
				
		});
		
		}
		
	}
	
	
}


