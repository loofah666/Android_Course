package com.asus.peggy_lin.savingdata;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Peggy_Lin on 2015/10/14.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    private List<Friend> mFriends;
    private Context mContext;
    private final static String TAG_ADAPTER="FRIEND_ADAPTER";

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvName, tvPhone, tvFav, tvGender;
        public ImageView ivGender;
        public View mView;

        public ViewHolder(View convertView) {
            super(convertView);

            this.mView = convertView;

            tvName = (TextView) convertView.findViewById(R.id.list_name);
            tvPhone = (TextView) convertView.findViewById(R.id.list_phone);
            tvFav = (TextView) convertView.findViewById(R.id.list_fav);
            tvGender = (TextView) convertView.findViewById(R.id.list_gender_tv);
            ivGender = (ImageView) convertView.findViewById(R.id.list_gender);
        }
    }

    // Pass in the contact array into the constructor
    public FriendListAdapter(Context context, List<Friend> friends) {
        this.mContext = context;
        this.mFriends = friends;
    }
    @Override
    public FriendListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.friend_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final FriendListAdapter.ViewHolder viewHolder, final int position)  {
        // Get the data model based on position
        final Friend friend = mFriends.get(position);



        // Set item views based on the data model
        TextView tv_name = viewHolder.tvName;
            tv_name.setText(friend.name);
        TextView tv_phone = viewHolder.tvPhone;
            tv_phone.setText(friend.phone);
        TextView tv_fav = viewHolder.tvFav;
            tv_fav.setText(friend.fav);
        TextView tv_gender = viewHolder.tvGender;
        ImageView iv_gender = viewHolder.ivGender;

        Drawable d = mContext.getResources().getDrawable(R.drawable.ic_person_white_24dp);

        if(friend.gender>0){
            //setTint is for v21
            Log.d(TAG_ADAPTER, "SDK_INT: "+Build.VERSION.SDK_INT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                d.setTint(mContext.getResources().getColor(R.color.md_pink_200));
                iv_gender.setBackground(d);
            } else {
                iv_gender.setVisibility(View.INVISIBLE);
                tv_gender.setVisibility(View.VISIBLE);
                tv_gender.setText("Girl");
            }

        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                d.setTint(mContext.getResources().getColor(R.color.md_blue_200));
                iv_gender.setBackground(d);
            } else {
                iv_gender.setVisibility(View.INVISIBLE);
                tv_gender.setVisibility(View.VISIBLE);
                tv_gender.setText("Boy");
            }
        }

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FriendEdit.class);
                intent.putExtra("ID", friend.id);
                intent.putExtra("Name", friend.name);
                intent.putExtra("Gender", friend.gender);
                intent.putExtra("Phone", friend.phone);
                intent.putExtra("Fav", friend.fav);
                mContext.startActivity(intent);
            }
        });
    }

    public void add(final Friend object) {
        mFriends.add(object);
        notifyItemInserted(getItemCount() - 1);
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mFriends.size();
    }

}
