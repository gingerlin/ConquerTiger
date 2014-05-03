package com.ginger.conquertiger;

import com.ginger.conquertiger.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ExamFragment extends Fragment {
    private int mPageNumber;
    private int mFirstWordSeq;

    public static ExamFragment create(int firstWordSeq, int pageNumber) {
        ExamFragment fragment = new ExamFragment();
        Bundle args = new Bundle();
        args.putInt("pageNumber", pageNumber);
        args.putInt("firstWordSeq", firstWordSeq);
        fragment.setArguments(args);
        return fragment;
    }

    public ExamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("pageNumber");
        mFirstWordSeq = getArguments().getInt("firstWordSeq");        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	

        GridView rootView = (GridView) inflater.inflate(R.layout.exam_grid, container, false);

        ExamAdapter adapter = new ExamAdapter(getActivity(), mFirstWordSeq, mPageNumber);
        rootView.setAdapter(adapter);

        return rootView;
    }
}
