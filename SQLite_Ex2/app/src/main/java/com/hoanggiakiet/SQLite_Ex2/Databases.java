package com.hoanggiakiet.SQLite_Ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Databases extends SQLiteOpenHelper {
    public static final String DB_NAME = "products.sqlite";

    public static final int DB_VERSION = 1;

    public static final String TBL_NAME = "Product";
    public static final String COL_CODE = "ProductCode";
    public static final String COL_NAME = "ProductName";
    public static final String COL_PRICE = "ProductPrice";

    public Databases(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_PRICE + " REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    // SELECT.....
    public Cursor queryData(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    // INSERT, UPDATE, DELETE
    public void execSql(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int getNumbOfRows()
    {
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME);
        int numberOfRows = cursor.getCount();
        cursor.close();

        return numberOfRows;
    }

    public void createSampleData() {
        if (getNumbOfRows() == 0)
        {
            try
            {
                execSql("INSERT INTO " + TBL_NAME + " VALUES (null, 'Heineken', 19000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES (null, 'Tiger', 23000)");
                execSql("INSERT INTO " + TBL_NAME + " VALUES (null, 'Sai Gon', 20000)");
            }
            catch (Exception e){
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
        }

    }

}
