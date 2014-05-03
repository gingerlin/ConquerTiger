package com.ginger.conquertiger;

import com.ginger.conquertiger.R;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.content.Intent;

public class ExamActivity extends FragmentActivity {
    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int mFirstWordSeq;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_pager);
        Intent intent = getIntent();
        mFirstWordSeq = intent.getIntExtra("firstWordSeq", -1);
        
        ImageView title1 = (ImageView) findViewById(R.id.title1);
        if(mFirstWordSeq == 1) {
        	title1.setImageResource(R.drawable.title1);
        } else if(mFirstWordSeq == 101) {
        	title1.setImageResource(R.drawable.title2);
        }else if(mFirstWordSeq == 201) {
        	title1.setImageResource(R.drawable.title3);
        }else if(mFirstWordSeq == 301) {
        	title1.setImageResource(R.drawable.title4);
        }else if(mFirstWordSeq == 401) {
        	title1.setImageResource(R.drawable.title5);
        }else if(mFirstWordSeq == 501) {
        	title1.setImageResource(R.drawable.title6);
        }else if(mFirstWordSeq == 601) {
        	title1.setImageResource(R.drawable.title7);
        }else if(mFirstWordSeq == 701) {
        	title1.setImageResource(R.drawable.title8);
        }else if(mFirstWordSeq == 801) {
        	title1.setImageResource(R.drawable.title9);
        }else if(mFirstWordSeq == 901) {
        	title1.setImageResource(R.drawable.title10);
        }else if(mFirstWordSeq == 1001) {
        	title1.setImageResource(R.drawable.title11);
        }else if(mFirstWordSeq == 1101) {
        	title1.setImageResource(R.drawable.title12);
        }else if(mFirstWordSeq == 1201) {
          	title1.setImageResource(R.drawable.title13);
        }else if(mFirstWordSeq == 1301) {
        	title1.setImageResource(R.drawable.title14);
        }
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ExamPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });
    }
    
    private class ExamPagerAdapter extends FragmentStatePagerAdapter {
        public ExamPagerAdapter(android.support.v4.app.FragmentManager fm) {
  	       super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return ExamFragment.create(mFirstWordSeq, position);
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}