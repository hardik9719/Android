package com.example.hardik.buttons;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int count=0;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn=(Button)findViewById(R.id.green);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ll=(LinearLayout)findViewById(R.id.linearlayout);
               // ll.setBackgroundColor(Color.parseColor("#ff0000"));
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                ll.setBackgroundColor(color);
                Date currdate=new Date();
                btn.setText(""+currdate);

            }
        });

        final Button countbutton=(Button)findViewById(R.id.count);
        countbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                TextView textView=(TextView)findViewById(R.id.outvalue);
                textView.setText(""+count);
            }
        });
        Button decrcount=(Button)findViewById(R.id.decrCounts);
        decrcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                TextView textView=(TextView)findViewById(R.id.outvalue);
                textView.setText(""+count);
            }
        });

    }



}

