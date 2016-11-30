package com.jirawat.jirawat_restaurant1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;

    private MySQLite mySQLite;

    private EditText userEditText,passwordEditText;
    private String userString,passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        userEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        connectedSQLite();

        testAddValue();

        synAndDelete();

        mySQLite = new MySQLite(this);
    }//onCreate

    public void clickSignUpMain(View view){
        startActivity(new Intent(MainActivity.this,
                SignUpActivity.class));
    }//clickSignUpMain
    public void clickSignInMain(View view){
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //
        if (userString.equals("")|| passwordString.equals("")){
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง","กรุณากรอกให้ครบทุกช่อง");

            //
        }else {
            CheckUser();
        }

    }
    private void CheckUser(){
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM userTABLE WHERE User = " +
                    "'" + userString + "'",null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i=0;i<cursor.getColumnCount();i++){
                resultStrings[i] = cursor.getString(i);
            }
            cursor.close();

            //
            if (passwordString.equals(resultStrings[2])){
                Toast.makeText(this,"ยินดีต้อนรับ"+resultStrings[3],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ShopProduct.class);
                intent.putExtra("Result",resultStrings);
                startActivity(intent);
                finish();
            }else {
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this,"Password ผิด","พิมพ์ใหม่ Password ผิด ครับ");
            }
        } catch (Exception e){
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่มี user นี้","ไม่มี"+userString + "ในฐานข้อมูล ของเรา");
        }
    }


    private void synAndDelete(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MySQLiteOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        sqLiteDatabase.delete(MySQLite.user_table,null,null);
        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();
    }

    public class MySynJSON extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids){
            try {
                String strURL ="http://www.csclub.ssru.ac.th/s56122201009/php_get_userTABLE.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                Log.d("jirawat","doInBack ==>" + e.toString());
                return null;
            }
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Log.d("jirawat", "strJSON ==>" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strUser = jsonObject.getString(MySQLite.column_user);
                    String strPassword = jsonObject.getString(mySQLite.column_password);
                    String strName = jsonObject.getString(mySQLite.column_name);
                    mySQLite.addNewUser(strUser, strPassword, strName);
                }
                Toast.makeText(MainActivity.this,"Sunchronize mySQL to SQLite Finsh", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.d("jirawat", "onPost ==>" + e.toString());
            }
        }
    }

    private void testAddValue(){
        objUserTABLE.addNewUser("Yu", "uuy11", "Yupa");
        objFoodTABLE.addNewFood("JA", "GGwp", "Mai");
        objOrderTABLE.addOrder("New", "Newbie", "NewbieT", "Eiei");
    }//testAddValue
    private void connectedSQLite(){
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        objOrderTABLE = new OrderTABLE(this);

    }//connectedSQLite

    public void Signup (View view){
        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }//Signup
}//Main Class
