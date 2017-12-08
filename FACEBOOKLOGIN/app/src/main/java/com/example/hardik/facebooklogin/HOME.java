package com.example.hardik.facebooklogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HOME extends AppCompatActivity {
    Button btn,imgbtn;
    String imgurl=null;
    ImageView userpicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userpicture=(ImageView)findViewById(R.id.profile);
        imgbtn= (Button) findViewById(R.id.btnAddIMG);
        btn= (Button) findViewById(R.id.btnlogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intent=new Intent(HOME.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequest ir=new ImageRequest(imgurl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                            userpicture.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error","error");
                    }
                });
                MySingleTon.getInstance(HOME.this).addToRequestQue(ir);
                String url ="https://www.thecrazyprogrammer.com/wp-content/uploads/demo.txt";
                RequestQueue rQueue = Volley.newRequestQueue(HOME.this);
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        Log.d("RESPONSE",s);
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });


                rQueue.add(request);
            }
        });

 /*       RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://graph.facebook.com/10212972001767641/?fields=picture&type=large?&access_token=EAADTOyWYwLQBALzbgJfKsgqKLR5P6s6SZAHNfa6ABanDpkLJdD7UQqR6eWsH07mXRZCdSls3UcFxuEm3iVZAgZCOBb1QjQHoqnuU5jU33wJWROhFrnVSTCbJH4uBF9aUt8Rvv1eDnmgeWLDmchaWCBFEVTQ54VLheZBoniyQsHF1wZAfB8R7kTpFLn2D9uo4IzqWRxfNUjjZCiKpXjmMbcu4lwpoJVBZAqQZD";

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            JSONObject data=response.getJSONObject("picture").getJSONObject("data");
                            imgurl=data.getString("url");
                            Log.d("data", String.valueOf(data));
                            Log.d("url", imgurl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String response=null;
                        Log.d("Error.Response", response);
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);*/

    }

}
