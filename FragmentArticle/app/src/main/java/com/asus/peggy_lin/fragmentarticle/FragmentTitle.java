package com.asus.peggy_lin.fragmentarticle;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by Peggy_Lin on 2015/10/5.
 */
public class FragmentTitle extends ListFragment {
    boolean isLand;
    int cursorPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                //transaction.addToBackStack(null);
                ft.commit();
            }
        }
        else{
            Intent intent = new Intent(getActivity(), FragmentContentActivity.class);
            intent.putExtra("Postion", position);
            startActivity(intent);
        }
    }
}
