package com.example.hardik.buttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView listView= (ListView) findViewById(R.id.mainList);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.activity_list_item,getResources().getStringArray(R.id.mainList)));
    }
}
