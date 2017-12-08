package com.example.hardik.connection;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvCode,tvId,tvPassword;
    String url="http://192.168.0.106:80/testconnection.php";
    EditText etId,etPwd;
    RequestQueue requestQueue;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCode= (TextView) findViewById(R.id.tvCode);
        tvId= (TextView) findViewById(R.id.tvId);
        tvPassword= (TextView) findViewById(R.id.tvPassword);
        etId= (EditText) findViewById(R.id.etId);
        etPwd= (EditText) findViewById(R.id.etPwd);
        Cache cache=new DiskBasedCache(getCacheDir(),2048*1024);
        Network  network=new BasicNetwork(new HurlStack());
        requestQueue=new RequestQueue(cache,network);
        requestQueue.start();

    }

    public void onClick(View view) {

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
      /*  StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              tvId.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvCode.setText("Something wrong!!!");
            }
        });*/
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("users");
                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        tvId.append(obj.getString("id"));
                        tvPassword.append(obj.getString("password"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvCode.setText("Error!!!"+error);
            }
        });
        MySingleTon.getInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
     /*   checkConnection cc=new checkConnection();
        cc.execute()*/;


    }

    public void addToDatabase(View view) {
        final String id=etId.getText().toString();
         final String pwd=etPwd.getText().toString();

      StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
             Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                 Intent i=new Intent(MainActivity.this,SecondActivity.class);
                 startActivity(i);


          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(getBaseContext(), error.toString(),Toast.LENGTH_SHORT).show();
          }
      })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("id",id);
                params.put("pass",pwd);
                return params;
            }
        };
        MySingleTon.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    public void newActivity(View view) {
        Intent i=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(i);
    }

    public void userlocation(View view) {
        Intent i=new Intent(MainActivity.this,UserLocation.class);
        startActivity(i);
    }

    class checkConnection extends AsyncTask<String,Void,String>{
        HttpURLConnection conn;
        String n;
        int s;
        @Override
        protected String doInBackground(String... strings) {
            try{
                URL url = new URL("http://192.168.0.106:80/testconnection.php");
             conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.connect();
                s=conn.getResponseCode();


            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ( s== 200) {

                // Read data sent from server
                InputStream input = null;
                try {
                    input = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                try {
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Pass data to onPostExecute method
                return (result.toString());

            } else {

                return ("unsuccessful");
            }

        }

        @Override
        protected void onPostExecute(String s) {
            tvCode.setText(s);
        }
    }

}
