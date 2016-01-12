package com.recoverytoolbox.jtrax;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

//import com.bumptech.glide.Glide;

import java.util.Random;
import android.os.*;


public class PlanDetailActivity extends AppCompatActivity
{
	public static final String EXTRA_NAME = "task_name";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	}
	
	
}
