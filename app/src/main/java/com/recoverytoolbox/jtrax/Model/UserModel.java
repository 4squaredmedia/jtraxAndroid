package com.recoverytoolbox.jtrax.Model;

public class UserModel
{
	private String username;
	private String email;
	
	public UserModel(){};
	public UserModel(String uname, String txtEmail)
	{
		this.username=uname;
		this.email=txtEmail;
	}
	
	public String getUsername(String uname)
	{
		return this.username=uname;
	}
	
	public String getEmail(String txtEmail)
	{
		return this.email=txtEmail;
		
	}
	
}
