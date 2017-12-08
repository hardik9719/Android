package com.example.hardik.newfragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle=new Bundle();

        SecondFragment secondFragment=new SecondFragment();
        bundle.putString("text","hello word");
        secondFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FirstFragment firstFragment=new FirstFragment();
        fragmentTransaction.add(R.id.fragA,firstFragment);
        fragmentTransaction.add(R.id.fragB,secondFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void respond(String s) {
        Log.e("Resnsefrom:firstfrag",s);
    }
}
