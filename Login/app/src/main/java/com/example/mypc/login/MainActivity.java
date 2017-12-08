package com.example.mypc.login;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(googlePlayServicesAvailable()){
            Toast.makeText(this,"PERFECT!!!",Toast.LENGTH_SHORT).show();
        }
        initMap();
    }

    private void initMap() {
        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public  boolean googlePlayServicesAvailable(){
         GoogleApiAvailability apiAvailability=GoogleApiAvailability.getInstance();
         int isAvailable = apiAvailability.isGooglePlayServicesAvailable(this);
         if(isAvailable== ConnectionResult.SUCCESS){
             return  true;
         }else if(apiAvailability.isUserResolvableError(isAvailable)){
             Dialog dialog=apiAvailability.getErrorDialog(this,isAvailable,0);
             dialog.show();
             }else{
             Toast.makeText(this,"Cant Connect to Google play services",Toast.LENGTH_SHORT).show();
         }
         return false;
     }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
    }
}
