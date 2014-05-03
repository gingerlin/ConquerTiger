package com.ginger.conquertiger;

import com.ginger.conquertiger.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.graphics.Typeface;
import android.content.Intent;

public class MainFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    private int mPageNumber;

    public static MainFragment create(int pageNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_page, container, false);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Comic_Sans_MS_Bold.ttf");

        Button b1 = new Button(getActivity());
        Button b2 = new Button(getActivity());
        Button b3 = new Button(getActivity());
        Button b4 = new Button(getActivity());
        Button levelMax = (Button) rootView.getChildAt(0);
        
        setButton1(rootView, font, b1);        
        setButton2(rootView, font, b1, b2);
        setButton3(rootView, font, b3);
        setButton4(rootView, font, b3, b4);
        setLevelMax(rootView, font, levelMax);
        
        return rootView;
    }

	private void setButton4(ViewGroup rootView, Typeface font, Button b3,
			Button b4) {
		b4.setBackgroundResource(R.drawable.smallbutton);
        
        String from4 = Integer.toString(mPageNumber * 400 + 301);
        String to4 = Integer.toString(mPageNumber * 400 + 400);
        b4.setText(from4 + "~" + to4);
        b4.setTypeface(font);
        b4.setTextSize(25);

        RelativeLayout v4 = (RelativeLayout) rootView;

        LayoutParams p4 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        p4.addRule(RelativeLayout.BELOW, b3.getId());

        v4.addView(b4, p4);

        b4.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent();
                    intent4.setClass(getActivity(), ExamActivity.class);
                    intent4.putExtra("firstWordSeq", mPageNumber * 400 + 301);
                    startActivity(intent4); 
                }
            });
	}

	private void setButton3(ViewGroup rootView, Typeface font, Button b3) {
		b3.setBackgroundResource(R.drawable.smallbutton);

        String from3 = Integer.toString(mPageNumber * 400 + 201);
        String to3 = Integer.toString(mPageNumber * 400 + 300);
        b3.setText(from3 + "~" + to3);
        b3.setTypeface(font);
        b3.setTextSize(25);        
        b3.setId(3);

        RelativeLayout v3 = (RelativeLayout) rootView;

        LayoutParams p3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        
        v3.addView(b3, p3);


        b3.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent3 = new Intent();
                    intent3.setClass(getActivity(), ExamActivity.class);
                    intent3.putExtra("firstWordSeq", mPageNumber * 400 + 201);                    
                    startActivity(intent3); 
                }
            });
	}

	private void setButton2(ViewGroup rootView, Typeface font, Button b1,
			Button b2) {
		b2.setBackgroundResource(R.drawable.smallbutton);
        
        String from2 = Integer.toString(mPageNumber * 400 + 101);
        String to2 = Integer.toString(mPageNumber * 400 + 200);
        b2.setText(from2 + "~" + to2);
        b2.setTypeface(font);
        b2.setTextSize(25);

        RelativeLayout v2 = (RelativeLayout) rootView;

        LayoutParams p2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        p2.addRule(RelativeLayout.BELOW, b1.getId());
        
        v2.addView(b2, p2);


        b2.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), ExamActivity.class);
                    intent2.putExtra("firstWordSeq", mPageNumber * 400 + 101);
                    startActivity(intent2); 
                }
            });
	}

	private void setButton1(ViewGroup rootView, Typeface font, Button b1) {
		b1.setBackgroundResource(R.drawable.smallbutton);
    
        String from1 = Integer.toString(mPageNumber * 400 + 1);
        String to1 = Integer.toString(mPageNumber * 400 + 100);
        b1.setText(from1 + "~" + to1);

        b1.setTypeface(font);
        b1.setTextSize(25);
        
        b1.setId(1);

        RelativeLayout v1 = (RelativeLayout) rootView;

        LayoutParams p1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        
        v1.addView(b1, p1);

        b1.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(getActivity(), ExamActivity.class);
                    intent1.putExtra("firstWordSeq", mPageNumber * 400 + 1);
                    startActivity(intent1); 
                }
            });
	}
	
	private void setLevelMax(ViewGroup rootView, Typeface font, Button levelMax) {
		
        levelMax.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ProblemActivity.class);
                intent.putExtra("levelMax", true);
                startActivity(intent); 
            }
        });
     }
}