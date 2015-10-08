package com.asus.peggy_lin.fragmentarticle;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Peggy_Lin on 2015/10/5.
 */
public class FragmentTitle extends ListFragment {
    boolean isLand;
    int cursorPosition = 0;
    static String tagFrag = "FRAG_frag";

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(tagFrag, "onActivityCreated()............");

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, Literature.Titles));

        //check if landscape mode
        View contentFrame = getActivity().findViewById(R.id.fr_content);
        isLand = contentFrame != null && contentFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState!=null){
            cursorPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if(isLand) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(cursorPosition);
        }
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", cursorPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    void showDetails(int position){

        if(isLand){
            getListView().setItemChecked(position, true);
            FragmentContent fr_content = (FragmentContent) getFragmentManager().findFragmentById(R.id.fr_content);

            if(fr_content == null || fr_content.getShownIndex() != position){
                fr_content = FragmentContent.newInstance(position);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fr_content, fr_content);
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                //parameter name for transaction, inifinite back action, not often used!
                //ft.addToBackStack(null);

                ft.commit();
            }
        }
        else{
            Intent intent = new Intent(getActivity(), FragmentContentActivity.class);
            intent.putExtra("Position", position);
            startActivity(intent);
        }
    }
}
