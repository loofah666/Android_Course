package com.asus.peggy_lin.materialtheme;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbartabs);

        initToolbar();
        initViewPager();
        initTabLayout();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_tabs);
        setSupportActionBar(toolbar);
    }
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
    }
    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummySectionFragment(), "CAT");
        adapter.addFrag(new DummySectionFragment(), "DOG");
        adapter.addFrag(new DummySectionFragment(), "MOUSE");
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

    public static class DummySectionFragment extends Fragment {
        public static final String ARG_SECTION_NUMBER = "section_number";
        public static final String ARG_SECTION_COLOR = "section_color";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle args = getArguments();

            View view = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frag_frame_layout);
            frameLayout.setBackgroundColor(args.getInt(ARG_SECTION_COLOR));

            ((TextView) view.findViewById(android.R.id.text1)).setText(
                    getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));

            return view;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
