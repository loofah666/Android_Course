package com.asus.peggy_lin.savingdata;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Peggy_Lin on 2015/10/13.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {
        public FriendAdapter(Context context, ArrayList<Friend> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Friend friend = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_list, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.list_name);
            TextView tvPhone = (TextView) convertView.findViewById(R.id.list_phone);
            TextView tvFav = (TextView) convertView.findViewById(R.id.list_fav);
            ImageView iv_gender = (ImageView) convertView.findViewById(R.id.list_gender);

            // Populate the data into the template view using the data object
            tvName.setText(friend.name);
            tvPhone.setText(friend.phone);
            tvFav.setText(friend.fav);

            Drawable d = getContext().getResources().getDrawable(R.drawable.ic_person_white_24dp);

            if(friend.gender>0){
                //setTint is for v21
                d.setTint(getContext().getResources().getColor(R.color.md_pink_200));
                iv_gender.setBackground(d);
            }
            else{
                d.setTint(getContext().getResources().getColor(R.color.md_blue_200));
                iv_gender.setBackground(d);
            }

            convertView.setOnLongClickListener(new LongClick());

            // Return the completed view to render on screen
            return convertView;
        }

        class LongClick implements View.OnLongClickListener{
            public boolean onLongClick(View view) {

                return true;
            }
        }
}

