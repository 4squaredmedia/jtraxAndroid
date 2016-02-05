package com.recoverytoolbox.jtrax.Model;

public class JourneyChallenge
{
	private String challengeTitle;
	private String challengeText;
	
	public JourneyChallenge(){};
	
	public String getChallengeTitle()
	{
		return this.challengeTitle;
	}
	
	public String getChallengeText(){
		return this.challengeText;
	}
	
	public void setChallengeTitle(String mChallengeTitle)
	{
		this.challengeTitle=mChallengeTitle;
	}
	
	public void setChallengeText(String mChallengeText)
	{
		this.challengeText = mChallengeText;
	}
}
