package com.example.hardik.tempprojectdeleteafterdone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    JSONObject o;
    ListView l;
    List<String> startADD,endADD,type;
    List<Ride> list;
    JSONArray rides;
base b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = (ListView) findViewById(R.id.listview);
        Log.d("before", "before");
        l = (ListView) findViewById(R.id.listview);
        list=new ArrayList<Ride>();

        Log.d("before", "before");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());  // this = context
        String url = "http://192.168.0.106/findRider.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            o = new JSONObject(response);
                            rides = o.getJSONArray("rides");
                            for (int i = 0; i < rides.length(); i++) {
                                JSONObject tmp = rides.getJSONObject(i);
                                String starAddress = tmp.getString("startAddress");
                                String endAddress = tmp.getString("endAddress");
                                String RideType = tmp.getString("RideType");
                                Log.d("StartADDRESS", starAddress);
                                Ride r=new Ride(starAddress,endAddress,RideType);
                                list.add(r);
                                Log.d("SIZEOFLIST", String.valueOf(list.size()));
                               /* startADD.add(starAddress);
                                endADD.add(endAddress);
                                type.add(RideType);*/
                            }
                            b.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
        Log.d("before", "after");
        b=new base(this,list);
        l.setAdapter(b);
        /*String[] item={"1","2","3"};
        ArrayAdapter<String> listview=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,item);
        l.setAdapter(listview);*/
//        return  view;
    }

    class Ride {
        String startAdd, endAdd, RidteType;

        public Ride(String startAdd, String endAdd, String ridteType) {
            this.startAdd = startAdd;
            this.endAdd = endAdd;
            RidteType = ridteType;
        }
    }

    class base extends BaseAdapter {
        Context context;
        List<Ride> rides;

        public base(Context c, List<Ride> list) {
            this.context = c;
            this.rides=list;
         //   Log.d("STARTADDSIZE", String.valueOf(startADD.size()));
          /*  list=new ArrayList<Ride>();
            for(int i=0;i<rides.length();i++){
                Ride r=new Ride(startADD.get(i),endADD.get(i),type.get(i));
                list.add(r);
            }*/
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.ride_row, viewGroup, false);

            TextView startAdd = row.findViewById(R.id.startAdd);
            TextView endAdd = row.findViewById(R.id.endAdd);
            TextView rideType = row.findViewById(R.id.rideType);
            Ride temp = list.get(i);
            startAdd.setText(temp.startAdd);
            endAdd.setText(temp.endAdd);
            rideType.setText(temp.RidteType);

            return row;
        }



    }
}
