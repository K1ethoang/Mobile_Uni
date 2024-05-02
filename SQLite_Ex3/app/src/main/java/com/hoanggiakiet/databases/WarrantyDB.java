package com.hoanggiakiet.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class WarrantyDB extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Warranty.db";
    public static final String TBL_NAME = "Warranty";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_DESC = "Description";
    public static final String COL_PHOTO = "Photo";
    public WarrantyDB(@Nullable Context context) {
        super(context, DB_NAME,null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_DESC + " VARCHAR(150), " + COL_PHOTO + " BLOB)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    // SELECT
    public Cursor getData(String sql)
    {
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery(sql, null);
        } catch(Exception e)
        {
            return null;
        }
    }

    // UPDATE, DELETE...
    public boolean execSql(String sql)
    {
       try
       {
           SQLiteDatabase db = getWritableDatabase();
           db.execSQL(sql);
           return true;
       } catch (Exception e)
       {
           return false;
       }
    }

    // INSERT
    public boolean insertData(String name, String desc, byte[] photo)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + " VALUES (null,?,?,?)";

            SQLiteStatement statement = db.compileStatement(sql);

            statement.clearBindings();
            statement.bindString(1, name);
            statement.bindString(2, desc);
            statement.bindBlob(3, photo);

            statement.executeInsert();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
