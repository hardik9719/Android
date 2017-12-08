package com.example.hardik.testrun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView= (TextView) findViewById(R.id.tvTxt);
        Button btn= (Button) findViewById(R.id.btnget);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());  // this = context
                String url = "http://www.liftplease.codeadventure.in/www/html/rideFinder.php";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                                    textView.setText(response);


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", String.valueOf(error));
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        //    SharedPreferences sharedPreferences=get().getSharedPreferences("Info", Context.MODE_PRIVATE);
                        //    String email=sharedPreferences.getString("email",null);
                        //    Log.d("EMAIL",email);
                        params.put("name", "hardiktanna@hotmail.com");


                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

    }
}
