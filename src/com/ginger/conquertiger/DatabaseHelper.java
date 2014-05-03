package com.ginger.conquertiger;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Toast;
import android.database.sqlite.SQLiteException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String DB_FNAME = "database.sqlite";

    public DatabaseHelper(Context context) {
        super(context, DB_FNAME, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase getDatabase() {
        SQLiteDatabase db = grabDatabase();

        if(db == null) {
            getWritableDatabase();
            try {
                copyDatabase();
                db = getWritableDatabase();
            } catch(IOException e) {
                Toast.makeText(mContext, "Query done", Toast.LENGTH_LONG).show();
            }
        }

        return db;
    }

    private SQLiteDatabase grabDatabase() {
        SQLiteDatabase db = null;

        try {
            String path = mContext.getDatabasePath(DB_FNAME).getPath();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            if(db != null && db.isOpen())
                return db;
        } catch(SQLiteException e) {
        }

        return null;
    }

    private void copyDatabase() throws IOException {
        InputStream input = mContext.getAssets().open(DB_FNAME);
        OutputStream output = new FileOutputStream(mContext.getDatabasePath(DB_FNAME));

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0){
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }
}



