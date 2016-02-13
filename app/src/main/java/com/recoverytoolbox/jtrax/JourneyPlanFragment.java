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

import com.firebase.ui.FirebaseRecyclerAdapter;
import com.recoverytoolbox.jtrax.Model.JourneyChallenge;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.*;
import android.text.style.*;
import com.firebase.client.Firebase;



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
	
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
	    FirebaseRecyclerAdapter<JourneyChallenge,JourneyChallengeViewHolder> mAdapter;
		Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));

		mAdapter= new FirebaseRecyclerAdapter<JourneyChallenge,JourneyChallengeViewHolder>
		(JourneyChallenge.class, R.layout.list_item,JourneyChallengeViewHolder.class,ref){
			public void populateViewHolder(JourneyChallengeViewHolder vh, JourneyChallenge challenge, int pos)
			{
				vh.mTitle.setText(challenge.getChallengeTitle());
				vh.mContent.setText(challenge.getChallengeText());
			}
			
		};

		rv.setAdapter(mAdapter);
	}
	
	
	
	
}
    
