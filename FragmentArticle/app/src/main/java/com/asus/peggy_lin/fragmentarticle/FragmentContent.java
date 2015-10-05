package com.asus.peggy_lin.fragmentarticle;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Peggy_Lin on 2015/10/5.
 */
public class FragmentContent extends Fragment {
    public static FragmentContent newInstance(int position){
        FragmentContent fc = new FragmentContent();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("Position", position);
        fc.setArguments(args);

        return fc;
    }

    public int getShownIndex() {
        return getArguments().getInt("Position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container == null) {
//            return null;
//        }
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(Literature.Contents[getShownIndex()]);
        return scroller;
    }

}
