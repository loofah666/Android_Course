package com.asus.peggy_lin.materialtheme;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Peggy_Lin on 2015/10/1.
 */
public class DummySection3Fragment extends Fragment {
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_SECTION_COLOR = "section_color";
    static String tagFrag = "TOOLBAR_FRAG_3";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(tagFrag, "onAttach()............");
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(tagFrag, "onCreate()............");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();

        View view = inflater.inflate(R.layout.fragment_section_dummy, container, false);
        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frag_frame_layout);
        frameLayout.setBackgroundColor(args.getInt(ARG_SECTION_COLOR));

        ((TextView) view.findViewById(android.R.id.text1)).setText(
                getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));

        Log.i(tagFrag, args.getInt(ARG_SECTION_NUMBER) + "--onCreateView()............");

        return view;

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
