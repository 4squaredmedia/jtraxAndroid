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

//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.widget.*;
import android.text.style.*;

import butterknife.Bind;
import butterknife.ButterKnife;

/*Fragment used for each Tab of the Journey Plan Section
 of the app.  Each tab will have a list of activites to complete
 for each day.  These are activites from the daily inventory list*/

public class JourneyPlanFragment extends Fragment
{
	@Bind(R.id.rv)
	public static RecyclerView rv;
	
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
		setupRecyclerView(rv);
	}

    private void setupRecyclerView(RecyclerView recyclerView)
	{

		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		ArrayList mArraylist = new ArrayList();
		mArraylist.add("test 1");
		mArraylist.add("test 2");

		//TODO: add adapter for recycler view
		recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(), mArraylist));
    }

    public static class SimpleStringRecyclerViewAdapter
	extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>
	{

		private static final int TYPE_FOOTER = 1;
		private static final int TYPE_ITEM = 0;

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder
		{
            public String mBoundString;
            public final View mView;
            public final CheckBox mCheckBox;
            public final TextView mTextView;
			public final EditText mTaskName;
			
            public ViewHolder(View view)
			{
                super(view);
				mView = view;
                mCheckBox = (CheckBox) view.findViewById(R.id.jPlanListChkBox);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
				mTaskName = (EditText) view.findViewById(R.id.footerEditText);        
			}

            @Override
            public String toString()
			{
                return super.toString() + " '" + mTextView.getText();
            }
        }
		

        public String getValueAt(int position)
		{
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items)
		{
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
		{
            View rtnView;
			int layoutRes=0;
			
			
				switch(viewType) {
				
				case TYPE_ITEM:
					layoutRes = R.layout.list_item;
					break;
					
				case TYPE_FOOTER:
					layoutRes = R.layout.list_footer;
				break;
				
				default:
				break;
			}
			
			//set the view layout and return the viewholder
			//using value set above for layoutRes
			rtnView = LayoutInflater.from(parent.getContext())
				.inflate(layoutRes,parent,false);
			
			return new ViewHolder(rtnView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position)
		{

			if(holder.getItemViewType() != TYPE_FOOTER){
			//holding value to pass to the detail activity
			//using the mBoundstring - same value as string shown in textview
			holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));
            holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v)
					{
						if (((CheckBox)v).isChecked())
						{
							TextView tv = holder.mTextView;
							holder.mTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
						}

						/*Context context = v.getContext();
						 Intent intent = new Intent(context, PlanDetailActivity.class);
						 intent.putExtra(PlanDetailActivity.EXTRA_NAME, holder.mBoundString);
						 context.startActivity(intent);*/
					}
				});
			}
        }

		@Override
		public int getItemViewType(int position)
		{
			if (isPositionFooter(position)){
				return TYPE_FOOTER;
			}
			return TYPE_ITEM;
		}

		private boolean isPositionFooter(int position)
		{
			//footer position is equal to the item count
			//given that getItemCount increments the array 
			//size by 1
			return position == getItemCount();
		}

        @Override
        public int getItemCount()
		{
			//returning item count+1 to account for the footer
            return mValues.size();
        }
    }

}
