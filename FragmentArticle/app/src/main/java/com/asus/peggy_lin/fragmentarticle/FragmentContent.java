package com.asus.peggy_lin.fragmentarticle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Peggy_Lin on 2015/10/5.
 */
public class FragmentContent extends Fragment {
    static String tagFrag = "FRAG_frag_text";

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(tagFrag, "onAttach()............");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tagFrag, "onCreate()............");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(tagFrag, "onCreateView()............");
//        if (container == null) {
//            return null;
//        }
        ScrollView scroller = new ScrollView(getActivity());
        View view = inflater.inflate(R.layout.article_view, container, false);
        TextView tv = (TextView) view.findViewById(R.id.article);
//        TextView text = new TextView(getActivity());
//        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                4, getActivity().getResources().getDisplayMetrics());
//        text.setPadding(padding, padding, padding, padding);
        scroller.addView(tv);
        tv.setText(Literature.Contents[getShownIndex()]);
        return scroller;
    }

    public void onActivityCreated (Bundle savedInstanceState){
        Log.i(tagFrag, "onActivityCreate()............");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(tagFrag, "onResume()............");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i(tagFrag, "onStart()............");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i(tagFrag, "onPause()............");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i(tagFrag, "onStop()............");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(tagFrag, "onDestroyView()............");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tagFrag, "onDestroy()............");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(tagFrag, "onDetach()............");
    }

}
