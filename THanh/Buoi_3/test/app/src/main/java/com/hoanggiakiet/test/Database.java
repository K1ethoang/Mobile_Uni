package com.hoanggiakiet.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hoanggiakiet.test.models.Dish;

import java.util.Objects;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "restaurant.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME_DISH = "Dish";
    public static final String TBL_NAME_CATEGORY = "Category";
    public static final String DISH_CODE = "DishId";
    public static final String DISH_NAME = "DishName";
    public static final String DISH_DESC = "DishDesc";
    public static final String DISH_IMAGE = "DishImage";
    public static final String DISH_PRICE = "DishPrice";
    public static final String DISH_CATEGORY_CODE = "CategoryId";
    public static final String CATEGORY_CODE = "CategoryId";
    public static final String CATEGORY_NAME = "CategoryName";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_dish = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_DISH + " (" + DISH_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DISH_NAME + " VARCHAR(100), " + DISH_DESC + " VARCHAR(255), " + DISH_IMAGE + " BLOB, " + DISH_PRICE + " DOUBLE, " +DISH_CATEGORY_CODE + " INTEGER, FOREIGN KEY(" + DISH_CATEGORY_CODE + ") REFERENCES " + TBL_NAME_CATEGORY + "(" + CATEGORY_CODE + ") )";

        String sql_create_category = "CREATE TABLE IF NOT EXISTS " + TBL_NAME_CATEGORY + " (" + CATEGORY_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " VARCHAR(100) )";

        db.execSQL(sql_create_category);
        db.execSQL(sql_create_dish);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_DISH);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME_CATEGORY);
        onCreate(db);
    }

    // SELECT...
    public Cursor queryData(String sql) {
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery(sql, null);
        } catch(Exception e)
        {
            return null;
        }
    }

    // INSERT, UPDATE, DELETE...
    public boolean execSql(String sql) {
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

    public int getNumbOfRowsOfDish() {
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME_DISH + " d, " + TBL_NAME_CATEGORY + " c WHERE d." + DISH_CODE + " = c." + CATEGORY_CODE);
        int numberOfRows = cursor.getCount();
        cursor.close();

        return numberOfRows;
    }

    public int getNumbOfRowsOfCategory() {
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME_CATEGORY);
        int numberOfRows = cursor.getCount();
        cursor.close();

        return numberOfRows;
    }

    // INSERT
    public boolean insertDishData(String name, String desc, double price, byte[] photo, int cateId)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME_DISH + " VALUES (null,?,?,?,?,?)";

            SQLiteStatement statement = db.compileStatement(sql);

            statement.clearBindings();
            statement.bindString(1, name);
            statement.bindString(2, desc);
            statement.bindBlob(3, photo);
            statement.bindDouble(4, price);
            statement.bindLong(5, cateId);

            statement.executeInsert();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void createSampleData() {
        if (getNumbOfRowsOfCategory() == 0)
        {
            try
            {
                execSql("INSERT INTO " + TBL_NAME_CATEGORY + " VALUES (null, 'Khai vị')");
                execSql("INSERT INTO " + TBL_NAME_CATEGORY + " VALUES (null, 'Món chính')");
                execSql("INSERT INTO " + TBL_NAME_CATEGORY + " VALUES (null, 'Tráng miệng')");
            }
            catch (Exception e){
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
        }

        if (getNumbOfRowsOfDish() == 0)
        {
            try
            {
                execSql("INSERT INTO " + TBL_NAME_DISH + " VALUES (null, 'cơm chiên', 'mô tả 1', 'null',70000,1)");
                execSql("INSERT INTO " + TBL_NAME_DISH + " VALUES (null, 'mì xào', 'mô tả 2', 'null',30000,2)");
                execSql("INSERT INTO " + TBL_NAME_DISH + " VALUES (null, 'Bún đậu', 'mô tả 3', 'null',60000,3)");
            }
            catch (Exception e){
                Log.e("Error", Objects.requireNonNull(e.getMessage()));
            }
        }
    }

}
