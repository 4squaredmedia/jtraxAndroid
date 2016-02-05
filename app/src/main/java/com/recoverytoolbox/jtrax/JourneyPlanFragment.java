package com.recoverytoolbox.jtrax;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Paint;

import com.firebase.ui.FirebaseRecyclerViewAdapter;
import com.recoverytoolbox.jtrax.Model.JourneyChallenge;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.*;
import android.text.style.*;
import com.firebase.client.Firebase;
import com.firebase.ui.*;
import com.firebase.client.*;



/*Fragment used for each Tab of the Journey Plan Section
 of the app.  Each tab will have a list of activites to complete
 for each day.  These are activites from the daily inventory list*/

public class JourneyPlanFragment extends Fragment
{
	
	public static RecyclerView rv;
	
	private static class JourneyChallengeViewHolder extends RecyclerView.ViewHolder
	{
		TextView mTitle;
		TextView mContent;

		public JourneyChallengeViewHolder(View itemView)
		{
			super(itemView);
			mTitle = (TextView) itemView.findViewById(android.R.id.text1);
			mContent = (TextView) itemView.findViewById(android.R.id.text2);
		}
	}
	
	
	@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        View view =  inflater.inflate(
			R.layout.fragment_inventory_list, container, false);
        //ButterKnife.bind(this, view);		
        return view;
    }
	
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		rv = (RecyclerView) view.findViewById(R.id.rv);
		//setupRecyclerView(rv);
		
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
	
		Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));

		mAdapter = new FirebaseRecyclerViewAdapter<JourneyChallenge,JourneyChallengeViewHolder>(){
			
		};

		//TODO: add adapter for recycler view
		recyclerView.setAdapter(mAdapter);
	}

    private void setupRecyclerView(RecyclerView recyclerView)
	{
		
    }
	
	public class myFBRVAdapter<JourneyChallenge,JourneyChallengeViewHolder> extends FirebaseRecyclerViewAdapter
	{
		
		public void populateViewHolder(JourneyChallengeViewHolder jcViewHolder, JourneyChallenge mJChallenge)
		{
			jcViewHolder.mTitle.setText(mJChallenge.getChallengeTitle());
			jcViewHolder.mContent.setText(mJChallenge.getChallengeText());
		}
	}
	
	
}
    
