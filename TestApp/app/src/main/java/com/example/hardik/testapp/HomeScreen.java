package com.example.hardik.testapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class
HomeScreen extends AppCompatActivity implements  {
    LinearLayout background;
    Button btnGreen,btnBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        background=(LinearLayout)findViewById(R.id.background);
        btnGreen=(Button)findViewById(R.id.btnGreen );
        btnblue=(Button)findViewById(R.id.btnblue);


        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                background.setBackgroundColor(Color.parseColor("#00ff00"));
            }
        });

        btnblue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                background.setBackgroundColor(Color.parseColor("0000ff"));
            }
        });
    }
}
