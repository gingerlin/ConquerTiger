package com.ginger.conquertiger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.text.TextPaint;
import android.widget.Toast;
import android.content.ContentValues;
import java.lang.Math;

import com.ginger.conquertiger.R;

public class ProblemActivity extends Activity {
    private boolean isResultPage;
    private int mWordSeq;
    private String mEnglish;
    private String mCorrectChinese;
    private String mWrongChinese1, mWrongChinese2, mWrongChinese3;
    private int mWhichSelect;
    private boolean isLevelMax;
    private int count;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 從levelMax進來 會從這裡開始跑
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        // 分辨是從Exam來的還是封印的單字來的
        if(intent.getBooleanExtra("levelMax", true) == true){
            isLevelMax = true;
        } else {
            isLevelMax = false;
        }

        // 把封印的單字來的或是Exam來的各自帶到他們該去的地方
        if(isLevelMax == true){
            findLockWordSeq();
        } else {
            mWordSeq = intent.getIntExtra("wordSeq", -1);
        }
        
        if(isLevelMax == true && count == 0) {
            setContentView(R.layout.activity_empty);
        }
        // 佈好Problem該有的樣子
        if(isLevelMax == true && count != 0) {
        putProblemContent();
        }

        if(isLevelMax == false) {
            putProblemContent();
        }

    }
    
    @Override
    public void onBackPressed() {
        if(isResultPage) {
        	putProblemContent();
        } else {
        	super.onBackPressed();
        }
    }
    
    private void putProblemContent() {
    	if(isLevelMax == false) {
            setContentView(R.layout.activity_problem);
    	}
    	if(isLevelMax == true) {
    		setContentView(R.layout.activity_inmaxproblem);
    	}
        setWordSeqText();
        isResultPage = false;
        
        mWhichSelect = (int)(Math.random()*4);

        findDatabaseWord();

        setProblemText();

        setSelect1();
        setSelect2();
        setSelect3();
        setSelect4();
        if(isLevelMax == false) {
        setback();
        setnext();
        }
    }

	private void setback() {
		Button back;
        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
                mWordSeq = mWordSeq - 1;
                putProblemContent();
            }
        });
	}

	private void setnext() {
		Button next;
        next = (Button)findViewById(R.id.next);

        next.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
                mWordSeq = mWordSeq + 1;
                putProblemContent();            
            }
        });
	}

	private void setSelect4() {
		Button select4;
        select4 = (Button)findViewById(R.id.select4);
        if(mWhichSelect == 3) {
        	select4.setText(mCorrectChinese);
        } else {
        	select4.setText(mWrongChinese3);
        }       
        select4.setTextSize(25);

        select4.setOnClickListener(new Button.OnClickListener(){ 
                @Override
                public void onClick(View v) {
                	if(mWhichSelect == 3) {
                		putCorrectContent();
                		
                		if (isLevelMax == true) {
                		    deleteMaxWord();
                		}

                	} else {
                           putWrongContent();
                           putMaxWord();
                	}
                }
           });
	}

	private void setSelect3() {
		Button select3;
        select3 = (Button)findViewById(R.id.select3);
        if(mWhichSelect == 2) {
        	select3.setText(mCorrectChinese);
        } else if(mWhichSelect == 3) {
        	select3.setText(mWrongChinese3);
        } else if(mWhichSelect == 0){
        	select3.setText(mWrongChinese2);
        } else if(mWhichSelect == 1){
        	select3.setText(mWrongChinese2);
        }
        select3.setTextSize(25);

        select3.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	if(mWhichSelect == 2) {
            		putCorrectContent();
            		
            		if (isLevelMax == true) {
            		    deleteMaxWord();
            		}

            	} else {
            	    putWrongContent();
                    putMaxWord();
            	}
            }
        });
	}

	private void setSelect2() {
		Button select2;
        select2 = (Button)findViewById(R.id.select2);
        if(mWhichSelect == 1) {
        	select2.setText(mCorrectChinese);
        } else if(mWhichSelect == 0){
        	select2.setText(mWrongChinese1);
        } else if(mWhichSelect == 2){
        	select2.setText(mWrongChinese2);
        } else if(mWhichSelect == 3){
        	select2.setText(mWrongChinese2);
        }
        select2.setTextSize(25);

        select2.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	if(mWhichSelect == 1) {
            		putCorrectContent();
            		
            		if (isLevelMax == true) {
            		    deleteMaxWord();
            		}

            	} else {
            	    putWrongContent();
                    putMaxWord();
            	}
            }
        });
	}

	private void setSelect1() {
		Button select1;
        select1 = (Button)findViewById(R.id.select1);
        if(mWhichSelect == 0) {
        	select1.setText(mCorrectChinese);

        	if (isLevelMax == true) {
        	    deleteMaxWord();
        	}

        } else {
        	select1.setText(mWrongChinese1);
        }
        select1.setTextSize(25);

        select1.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	if(mWhichSelect == 0) {
            		putCorrectContent();
            	} else {
            	    putWrongContent();
                    putMaxWord();
            	}
            }
        });
	}

	private void setProblemText() {
		TextView problem;
        problem = (TextView)this.findViewById(R.id.problem);
        problem.setText(mEnglish);
        TextView textView = (TextView)findViewById(R.id.problem);
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
        problem.setTextSize(43);
	}

	private void setWordSeqText() {
		TextView wordSeq;
	    wordSeq = (TextView)this.findViewById(R.id.wrodSeq);
	    String stringValue = Integer.toString(mWordSeq);
	    wordSeq.setText(stringValue);
        wordSeq.setTextSize(30);
	}
	
	private void findDatabaseWord() {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase database = helper.getDatabase();

        String[] columns = {
            "English",
            "Chinese",
        };

        Cursor cursor = database.query(
            "EnglishWord",
            columns,
            "seq == " + mWordSeq,
            null, null, null, null);
        
        cursor.moveToFirst();
        if(isLevelMax == true && count != 0) {
        mEnglish = cursor.getString(0);
        mCorrectChinese = cursor.getString(1);
        }
        if(isLevelMax == false) {
        mEnglish = cursor.getString(0);
        mCorrectChinese = cursor.getString(1);
        }

        if(mWordSeq <= 1300) {
            cursor = database.query(
                "EnglishWord",
                columns,
                "seq == " + (mWordSeq + 100),
                null, null, null, null);
        }


        if(mWordSeq > 1300) {
            cursor = database.query(
                "EnglishWord",
                columns,
                "seq == " + (mWordSeq - 200),
                null, null, null, null);
        }

        cursor.moveToNext();
        if(isLevelMax == true && count != 0) {
        mWrongChinese1 = cursor.getString(1);
        }
        if(isLevelMax == false) {
        mWrongChinese1 = cursor.getString(1);
        }

        if(mWordSeq <= 1300) {
            cursor = database.query(
                "EnglishWord",
                columns,
                "seq == " + (mWordSeq + 200),
                null, null, null, null);
        }
        
        if(mWordSeq > 1300) {
            cursor = database.query(
                "EnglishWord",
                columns,
                "seq == " + (mWordSeq - 400),
                null, null, null, null);
        }

        cursor.moveToFirst();
        if(isLevelMax == true && count != 0) {
        mWrongChinese2 = cursor.getString(1);
        }
        if(isLevelMax == false) {
        mWrongChinese2 = cursor.getString(1);
        }

        if(mWordSeq <= 1300) {
        cursor = database.query(
            "EnglishWord",
            columns,
            "seq == " + (mWordSeq + 300),
            null, null, null, null);
        }
        
        if(mWordSeq > 1300) {
            cursor = database.query(
                "EnglishWord",
                columns,
                "seq == " + (mWordSeq - 600),
                null, null, null, null);
            }
        
        cursor.moveToFirst();
        if(isLevelMax == true && count != 0) {
        mWrongChinese3 = cursor.getString(1);
        }
        if(isLevelMax == false) {
        mWrongChinese3 = cursor.getString(1);
        }

        database.close();
	}

	private void findLockWordSeq() {
        // 載入一下資料庫小幫手
        DatabaseHelper helper = new DatabaseHelper(this);
        // 載入我的database
        SQLiteDatabase database = helper.getDatabase();

        // 現在Wrong裡面我們要用到的有"whichWord"
        String[] columns = {
            "whichWord"
        };

        // 宣告cusor
        // 外加從database裡Wrong這個table裡搜尋
        // 然後挑columns出來
        Cursor cursor = database.query(
            "Wrong",
            columns,
            null,
            null, null, null, null);
        
        count = cursor.getCount();
        if(isLevelMax == true && count != 0) {
            Toast.makeText(this, "被封印的單字現在有" + count + "個", Toast.LENGTH_LONG).show();
        }
        if(isLevelMax == false) {
            Toast.makeText(this, "被封印的單字現在有" + count + "個", Toast.LENGTH_LONG).show();
        }
        
        // 移到第一個
        cursor.moveToFirst();
        // 把Wrong裡面的東西放到某個變數去
        if(isLevelMax == true && count != 0) {
            mWordSeq = cursor.getInt(0);
        }  
        if(isLevelMax == false) {
            mWordSeq = cursor.getInt(0);
        }  

        database.close();        
	}

    void putWrongContent() {
    	  isResultPage = true;

       if(isLevelMax == false) {
    	      setContentView(R.layout.activity_wrong);
            TextView answer;
            answer = (TextView)this.findViewById(R.id.answer);
            answer.setText(mEnglish + "\n" + mCorrectChinese);
            answer.setTextSize(40);

            Button ok;
            ok = (Button)findViewById(R.id.ok);

            ok.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mWordSeq = mWordSeq + 1;
                        putProblemContent();
                    }
               });   
        }  
        if(isLevelMax == true) {
    	      setContentView(R.layout.activity_inmaxwrong);
            TextView answer;
            answer = (TextView)this.findViewById(R.id.answer);
            answer.setText(mEnglish + "\n" + mCorrectChinese);
            answer.setTextSize(40);

            Button ok;
            ok = (Button)findViewById(R.id.ok);

            ok.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        findLockWordSeq();
                        putProblemContent();
                    }
               });   
        }
    }
    
    private void putMaxWord(){
        // 載入一下資料庫小幫手
        DatabaseHelper helper = new DatabaseHelper(this);
        // 載入我的database
        SQLiteDatabase database = helper.getDatabase();
        // 創一個新的row
        ContentValues row = new ContentValues();
        // 設定這個row某個column的值
        row.put("whichWord", mWordSeq);
        // 把這一筆row存進database裡
        database.insert("Wrong", null, row);
        Toast.makeText(this, "已加入封印庫", Toast.LENGTH_LONG).show();
        database.close();
    }

    private void deleteMaxWord(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase database = helper.getDatabase();
        
        database.delete ("Wrong", "whichWord = " + mWordSeq, null);
        database.close();
    }

    void putCorrectContent() {
    	
     isResultPage = true;
    	
     if(isLevelMax == false) {
    	setContentView(R.layout.activity_correct);
        TextView answer;
        answer = (TextView)this.findViewById(R.id.answer);
        answer.setText(mEnglish + "\n" + mCorrectChinese);
        answer.setTextSize(40);

        Button add;
        add = (Button)findViewById(R.id.add);
        add.setText("加入封印庫");
        add.setTextSize(35);

        add.setOnClickListener(new Button.OnClickListener(){ 

                @Override
                public void onClick(View v) {
                	putMaxWord();
                	mWordSeq = mWordSeq + 1;
                	putProblemContent();
                }
           });     
        
        Button ok;
        ok = (Button)findViewById(R.id.ok);

        ok.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                	mWordSeq = mWordSeq + 1;
                	putProblemContent();
                }
           });  
        }
     if(isLevelMax == true) {
    	setContentView(R.layout.activity_inmaxcorrect);
        TextView answer;
        answer = (TextView)this.findViewById(R.id.answer);
        answer.setText(mEnglish + "\n" + mCorrectChinese);
        answer.setTextSize(40);

        Button add;
        add = (Button)findViewById(R.id.add);
        add.setText("我還不熟");
        add.setTextSize(35);

        add.setOnClickListener(new Button.OnClickListener(){ 

                @Override
                public void onClick(View v) {
                	putMaxWord();
                	findLockWordSeq();
                	putProblemContent();
                }
           });     

        Button ok;
        ok = (Button)findViewById(R.id.ok);

        ok.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                	findLockWordSeq();
                	putProblemContent();
                	if(count == 0) {
                		setContentView(R.layout.activity_empty);                		
                	}
                }
           });  

     } 
    }
}