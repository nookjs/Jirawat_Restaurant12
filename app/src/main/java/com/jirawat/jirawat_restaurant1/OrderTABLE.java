package com.jirawat.jirawat_restaurant1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nook on 19/10/2559.
 */
public class OrderTABLE {
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String ORDER_TABLE="orderTABlE";
    public static final String COLUMN_ID_ORDER="_id";
    public static final String COLUMN_OFFICER="Officer";
    public static final String COLUMN_DESK="Desk";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_ITEM="Item";

    public OrderTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long addOrder (String strOfficer,String strDesk,String strFood,String strItem){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_DESK, strDesk);
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_ITEM, strItem);
        return readSqLiteDatabase.insert(ORDER_TABLE, null, objContentValues);
    }
}