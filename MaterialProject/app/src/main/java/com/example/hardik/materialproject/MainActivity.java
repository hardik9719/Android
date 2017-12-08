package com.example.hardik.materialproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usrname= (EditText) findViewById( R.id.usr);
   // EditText usrpassword= (EditText) findViewById(R.id.password);
  //  String s=usrname.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        registerForContextMenu(findViewById(R.id.usr));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

 @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.usr){
            menu.add(100,1,0,"CUT");
            menu.add(100,2,1,"COPY");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1){
            Toast.makeText(this,"CUT",Toast.LENGTH_SHORT).show();
          //  usrpassword.setText(s);

        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.actionProfile) {
            Toast.makeText(this,"Hadik Profile",Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.image) {
            Toast.makeText(this,"Image",Toast.LENGTH_SHORT).show();

        }

        return  true;
        //return super.onOptionsItemSelected(item);
    }
}
