package com.example.hardik.findride;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.Serializable;


public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback , Serializable{
    GoogleMap mgoogleMap;
    ImageView userpicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.homescreen);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new MainMapFragment()).commit();


        View header=navigationView.getHeaderView(0);

Intent i=getIntent();
    String id,name,email,gender,dob,imgurl;
        id = i.getStringExtra("id");
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        gender = i.getStringExtra("gender");
        dob = i.getStringExtra("dob");

            imgurl=i.getStringExtra("imgurl");


       TextView Name = (TextView)header.findViewById(R.id.fbUsername);//get a reference to the textview on the log.xml file.
        TextView Email = (TextView)header.findViewById(R.id.fbEmail);//get a reference to the textview on the log.xml file.

       Name.setText(name);
        Email.setText(email);
       userpicture= (ImageView) findViewById(R.id.profileImage);
      /*  ImageRequest ir=new ImageRequest(imgurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                userpicture.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","error");
            }
        });*/
/*        ImageRequest avatarResquest = new ImageRequest(imgurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                userpicture.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("slfwofihsoafjasd");
            }
        });
        MySingleTon.getInstance(HomeScreen.this).addToRequestQue(avatarResquest);*/

    }




  @Override
  public void onMapReady(GoogleMap googleMap) {
      mgoogleMap=googleMap;
  }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.homescreen);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
  FragmentManager fragmentManager=getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.myRides) {
            setTitle("MyRides");
            fragmentManager.beginTransaction().replace(R.id.content_frame,new MyRidesFragment()).commit();
        } else if (id == R.id.rideRequests) {
            setTitle("RideRequest");


            fragmentManager.beginTransaction().replace(R.id.content_frame,new RideRequestsFragment()).commit();
        } else if (id == R.id.searchride) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new SearchRide()).commit();
        } else if (id == R.id.logout) {
            LoginManager.getInstance().logOut();
            Intent login = new Intent(HomeScreen.this, LoginScreen.class);
            startActivity(login);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.homescreen);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


 /*   @Override
    public void createRideList(String res) {
     MyRidesFragment myRidesFragment=new MyRidesFragment();
        myRidesFragment.create(res);
    }*/
}
