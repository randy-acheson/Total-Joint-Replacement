package edu.wpi.total_joint_replacement.activities;

import java.util.Locale;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import edu.wpi.total_joint_replacement.OnFragmentInteractionListener;
import edu.wpi.total_joint_replacement.R;
import edu.wpi.total_joint_replacement.fragments.PainProgressFragment;
import edu.wpi.total_joint_replacement.fragments.PainValueFragment;

public class RecordPainActivity extends BaseActivity implements ActionBar.TabListener, OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    PainValueFragment painFragment = null;
    PainProgressFragment reportFragment = null;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        requiresConfirmationForExit = true;

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                default:
                case 0:
                    if(painFragment == null){
                        painFragment = new PainValueFragment();
                    }
                    return painFragment;
                case 1:
                    if(reportFragment == null){
                        reportFragment = new PainProgressFragment();
                    }
                    return reportFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.pain_tab_title).toUpperCase(l);
                case 1:
                    return getString(R.string.progress_tab_title).toUpperCase(l);
            }
            return null;
        }
    }


    public void onFragmentInteraction(Uri uri){

    }


    //control all the button so that only one choice can be chosen
    public void face1(View view) {
        painFragment.enableUnclickedLayouts(1);
        //resetAllRadio();
        //RB1.setChecked(true);
    }

    //control all the button so that only one choice can be chosen
    public void face2(View view) {
        painFragment.enableUnclickedLayouts(2);
        //resetAllRadio();
        //RB2.setChecked(true);
    }

    //control all the button so that only one choice can be chosen
    public void face3(View view) {
        painFragment.enableUnclickedLayouts(3);
        //resetAllRadio();
        //RB3.setChecked(true);
    }

    //control all the button so that only one choice can be chosen
    public void face4(View view) {
        painFragment.enableUnclickedLayouts(4);
        //resetAllRadio();
        //RB4.setChecked(true);
    }

    //control all the button so that only one choice can be chosen
    public void face5(View view) {
        painFragment.enableUnclickedLayouts(5);
        //resetAllRadio();
        //RB5.setChecked(true);
    }

    //control all the button so that only one choice can be chosen
    public void face6(View view) {
        painFragment.enableUnclickedLayouts(6);
        //resetAllRadio();
        //RB6.setChecked(true);
    }

    public void onJointClicked(View view){
        painFragment.buttonSelected(view);
    }


}
