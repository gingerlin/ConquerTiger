package com.ginger.conquertiger;

import com.ginger.conquertiger.R;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pager);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });
    }
    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        public MainPagerAdapter(android.support.v4.app.FragmentManager fm) {
  	       super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return MainFragment.create(position);
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}