package com.jirawat.jirawat_restaurant1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nook on 19/10/2559.
 */
public class UserTABLE {
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;
    public static final String USER_TABLE="userTABlE";
    public static final String COLUMN_ID_USER="_id";
    public static final String COLUMN_USER="User";
    public static final String COLUMN_PASSWORD="Password";
    public static final String COLUMN_NAME="Name";

    public UserTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }//Constructor
    public long addNewUser(String strUser, String strPassword,String strName) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_NAME, strName);
        return readSqLiteDatabase.insert(USER_TABLE, null, objContentValues);
    }//addNewUser
}//Main Class

