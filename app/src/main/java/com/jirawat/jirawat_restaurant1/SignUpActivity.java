package com.jirawat.jirawat_restaurant1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private EditText user,pass,name;
    private String userString, passString, nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bindWidget
        bindWidget();
    }//onCreate
    public void ClickSignUp(View view){
        userString = user.getText().toString().trim();
        passString = pass.getText().toString().trim();
        nameString = name.getText().toString().trim();
        //check space
        if (userString.equals("") ||  passString.equals("") || nameString.equals("")){

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this , "มีช่องว่าง", "กรุณากรอกให้ครบ");
        }else{
            updateValueToServer();
        }
    }//ClickSignUp
    private void updateValueToServer(){
        String strURL = "http://www.csclub.ssru.ac.th/s56122201009/php_add_user.php";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd","true")
                .add("User",userString)
                .add("Password",passString)
                .add("Name",nameString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(strURL).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    finish();
                }catch (Exception e){
                    Log.d("Restaurant", "error " + e.toString());
                }

            }
        });
    } // updateValueToServer

    private void bindWidget(){
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        name = (EditText) findViewById(R.id.namez);
    }//bindWidget

}
