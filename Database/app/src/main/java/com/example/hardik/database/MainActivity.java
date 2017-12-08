package com.example.hardik.database;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt,PasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt= (EditText) findViewById(R.id.etUsername);
        PasswordEt= (EditText) findViewById(R.id.etPassword);
    }
    public void onLogin(View v){
        String usernname=UsernameEt.getText().toString();
        String password=PasswordEt.getText().toString();
        String type="login";
     BackgroundWorker backgroundWorker=new BackgroundWorker(this);
       backgroundWorker.execute(type,usernname,password);
        //insertToDatabase(usernname,password);
    }
//    public void insertToDatabase(String username, String password){
//        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//                String paramuserid=params[0];
//                String parampassword=params[1];
//            }
//        }
//    }



}
