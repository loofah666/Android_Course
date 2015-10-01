package com.asus.peggy_lin.materialtheme;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peggy_Lin on 2015/9/30.
 */
public class ToolBarTabs extends ActionBarActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    static String tagMain = "TOOLBAR_MAIN";
    static String tagFrag = "TOOLBAR_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(tagMain, "onCreate()............");
        setContentView(R.layout.toolbartabs);

        initToolbar();
        initViewPager();
        initTabLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tagMain, "onResume()............");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tagMain, "onStart()............");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tagMain, "onPause()............");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tagMain, "onStop()............");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tagMain, "onRestart()............");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tagMain, "onDestroy()............");
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_tabs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        //below not working...
        //tabLayout.getTabAt(1).setCustomView(R.layout.tabview);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummySectionFragment(), "CAT");
        adapter.addFrag(new DummySection2Fragment(), "RAT");
        adapter.addFrag(new DummySection3Fragment(), "I LOVE DOGS");
        adapter.addFrag(new DummySectionFragment(), "DISNEY MICKEY_MOUSE LOVES EVERYONE");
        adapter.addFrag(new DummySection2Fragment(), "ASUS LAUNCHER YEAH");
        adapter.addFrag(new DummySection3Fragment(), "ASUS LAUNCHER YEAH");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);

            switch(position){
                case 0:
                    args.putInt(DummySectionFragment.ARG_SECTION_COLOR, getResources().getColor(R.color.md_brown_300));
                    fragment.setArguments(args);
                    return fragment;
                case 1:
                    args.putInt(DummySectionFragment.ARG_SECTION_COLOR, getResources().getColor(R.color.md_light_green_400));
                    fragment.setArguments(args);
                    return fragment;
                case 2:
                    args.putInt(DummySectionFragment.ARG_SECTION_COLOR, getResources().getColor(R.color.md_orange_500));
                    fragment.setArguments(args);
                    return fragment;
                default:
                    args.putInt(DummySectionFragment.ARG_SECTION_COLOR, getResources().getColor(R.color.md_indigo_400));
                    fragment.setArguments(args);
                    return fragment;
            }
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
}
