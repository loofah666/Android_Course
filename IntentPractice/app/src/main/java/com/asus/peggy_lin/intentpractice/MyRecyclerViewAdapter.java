package com.asus.peggy_lin.intentpractice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Peggy_Lin on 2015/10/23.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .PostObjectHolder> {

    private static final String TAG_INTENT = "INTENT_PRAC_ADAPTER";
    private List<PostObject> mDataset;
    private Context mContext;

    public static class PostObjectHolder extends RecyclerView.ViewHolder{
        public TextView name, post;
        public CardView cardView;
        public View mView;
        public String mSharedPreferenceName = "";
        public RelativeLayout mRelativeLayout;
        public LinearLayout mContainer;

        public PostObjectHolder(View itemView) {
            super(itemView);

            this.mView = itemView;
            this.name = (TextView) itemView.findViewById(R.id.card_tv_name);
            this. post = (TextView) itemView.findViewById(R.id.card_tv_post);
            this.cardView = (CardView) itemView.findViewById(R.id.cardview_post);
            this.mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.cardview_parentlayout);
//            this.mContainer = (LinearLayout) itemView.findViewById(R.id.cardview_container);

        }
    }

    public MyRecyclerViewAdapter(Context context, List<PostObject> myDataset) {
        this.mContext = context;
        this.mDataset = myDataset;
    }

    @Override
    public MyRecyclerViewAdapter.PostObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.default_cardview, parent, false);

        // Return a new holder instance
        PostObjectHolder postObjectHolder = new PostObjectHolder(contactView);
        return postObjectHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewAdapter.PostObjectHolder holder, final int position)  {
        final PostObject postObject = mDataset.get(position);

        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "";
        holder.mSharedPreferenceName = sharedPref.getString(mContext.getResources().getString(R.string.saved_name_str), defaultValue);

        if(postObject.getmName() == holder.mSharedPreferenceName){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.name.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.name.setLayoutParams(params);

            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.cardView.getLayoutParams();
            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params2.addRule(RelativeLayout.BELOW, R.id.card_tv_name);
            holder.cardView.setLayoutParams(params2);


            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.color_primary));
            holder.post.setTextColor(mContext.getResources().getColor(R.color.color_mypost_text));
            holder.name.setTextColor(mContext.getResources().getColor(R.color.color_mypost_name));
        }

        holder.name.setText(postObject.getmName());
        holder.post.setText(postObject.getmPost());
    }

    public void addItem(final PostObject dataObj) {
        mDataset.add(dataObj);
        notifyItemInserted(getItemCount() - 1);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
