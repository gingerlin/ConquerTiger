package com.ginger.conquertiger;

import com.ginger.conquertiger.R;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Typeface;
import 	android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ExamAdapter extends BaseAdapter {
	private int NUM_WORDS_IN_PAGE = 10;
    private Context mContext;
    private String[] englishes;
    private int mBaseWordSeq;

    public ExamAdapter(Context c, int firstWordSeq, int pageNumber) {
        mContext = c;
        englishes = new String[NUM_WORDS_IN_PAGE];
        mBaseWordSeq = firstWordSeq + pageNumber * NUM_WORDS_IN_PAGE;
        this.loadDatabaseEnglishes(mBaseWordSeq, NUM_WORDS_IN_PAGE);
    }

    public int getCount() {
        return NUM_WORDS_IN_PAGE;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
        	int index = position;
        	String english = englishes[index];
            button = new Button(mContext);
            button.setText(english);
            button.setBackgroundResource(R.drawable.button_background);
            Typeface font = Typeface.createFromAsset(mContext.getAssets(),"Comic_Sans_MS_Bold.ttf");
            button.setTypeface(font);
            button.setTextSize(20);

           button.setOnClickListener(new Button.OnClickListener(){ 
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ProblemActivity.class);
                    intent.putExtra("wordSeq", mBaseWordSeq + position);
                    intent.putExtra("levelMax", false);
                    mContext.startActivity(intent); 
                }
           });   
           
        } else {
            button = (Button) convertView;
        }
        return button;
    }
    
    private void loadDatabaseEnglishes(int fromSeq, int num) {
		DatabaseHelper helper = new DatabaseHelper(mContext);
        SQLiteDatabase database = helper.getDatabase();

        String[] projection = {
            "English",
        };

        Cursor cursor = database.query(
            "EnglishWord",
            projection,
            "seq >= " + fromSeq + " AND seq < " + (fromSeq + num),
            null, null, null, null);

        int index = 0;
        if(cursor.moveToFirst()) {
        	do {
        		englishes[index] = cursor.getString(0);
        		++ index;
        	} while (cursor.moveToNext());
            database.close();
        }
	}
    
}