package com.example.hardik.findride;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hardik on 9/9/17.
 */

public class MainMapFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener {
    GoogleMap mGoogleMap;
    MapView mapView;
    MainMapFragmentToMyRidesFragment mainMapFragmentToMyRidesFragment;
    AutoCompleteTextView atvStartPlace;
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootview=inflater.inflate(R.layout.fragment_map,container,false);


        return rootview;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootview=view;
        mapView=(MapView)rootview.findViewById(R.id.mymap);

        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
       /* mainMapFragmentToMyRidesFragment= (MainMapFragmentToMyRidesFragment) getActivity();*/
        Button btn=view.findViewById(R.id.btnAdd);
        btn.setOnClickListener(this);
     /*   SupportPlaceAutocompleteFragment startPlace = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.startAdd);
        startPlace.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(final Place place) {

//                Toast.makeText(.class,place.getAddress(),Toast.LENGTH_SHORT).show();
//                    tvPlaceId.setText(place.getAddress());
                startAdd= (String) place.getAddress();
         //       startAdd=startAdd.replaceAll("\\s","");
         //       Log.d("STARTaddr",startAdd);


            }

            @Override
            public void onError(Status status) {
                //Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        startPlace.setHint("Enter Start Location");


        SupportPlaceAutocompleteFragment endPlace = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.endAdd);
        endPlace.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

//                Toast.makeText(.class,place.getAddress(),Toast.LENGTH_SHORT).show();
//                    tvPlaceId.setText(place.getAddress());
                endAdd= (String) place.getAddress();
//                endAdd=startAdd.replaceAll("\\s","");
//                Log.d("ENDaddr",endAdd);


            }

            @Override
            public void onError(Status status) {
                //Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

*/




        atvStartPlace = (AutoCompleteTextView) rootview.findViewById(R.id.atvStartPlace);
        atvStartPlace.setThreshold(1);

        atvStartPlace.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlaceCompleteResults placeCompleteResults = new PlaceCompleteResults();
                placeCompleteResults.execute(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });




    }
    private String DownloadJSONData(String URL){
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(URL);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){

        }finally{
            try {
                iStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
        Log.d("JSON DATA",data);
        return data;
    }

    @Override
    public void onClick(View view) {
       mainMapFragmentToMyRidesFragment.createRideList("Hello");
        Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
    }

    class PlaceCompleteResults extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
         //   "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=bori&key=AIzaSyDP918XH297Y5hHwbeRm0q9GmyTjgWXx1M"
        String url="https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+strings[0]+"&key=AIzaSyDP918XH297Y5hHwbeRm0q9GmyTjgWXx1M";
        String jsonData=DownloadJSONData(url);
            return  jsonData;
        }


        @Override
        protected void onPostExecute(String s) {
           Log.d("response",s);
            // Creating ParserTask
             ParserTask parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(s);
        }
    }
    class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;

            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

         List<String> places =new ArrayList<String>();
            for (HashMap<String, String> entry : result) {
                String  s=entry.get("description").toString();
                places.add(s);
                Log.d("Places ",s);

//                for (String key : entry.keySet()) {
//                    String value = entry.get(key);
//                   Log.d("key = ", key);
//                    Log.d("value = ", value);
//
//                }
                Log.d("END OF A ENTRY","------------------------------------------------");
            }

            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,places);
           atvStartPlace.setAdapter(arrayAdapter);
        }
    }
        class PlaceJSONParser {

            /** Receives a JSONObject and returns a list */
            public List<HashMap<String,String>> parse(JSONObject jObject){

                JSONArray jPlaces = null;
                try {
                    /** Retrieves all the elements in the 'places' array */
                    jPlaces = jObject.getJSONArray("predictions");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /** Invoking getPlaces with the array of json object
                 * where each json object represent a place
                 */
                return getPlaces(jPlaces);
            }

            private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
                int placesCount = jPlaces.length();
                List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
                HashMap<String, String> place = null;

                /** Taking each place, parses and adds to list object */
                for(int i=0; i<placesCount;i++){
                    try {
                        /** Call getPlace with place JSON object to parse the place */
                        place = getPlace((JSONObject)jPlaces.get(i));
                        placesList.add(place);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("PLACESCOUNT", String.valueOf(placesCount));
                return placesList;
            }

            /** Parsing the Place JSON object */
            private HashMap<String, String> getPlace(JSONObject jPlace){

                HashMap<String, String> place = new HashMap<String, String>();

                String id="";
                String reference="";
                String description="";

                try {

                    description = jPlace.getString("description");
                    id = jPlace.getString("id");
                    reference = jPlace.getString("reference");

                    place.put("description", description);
                    place.put("_id",id);
                    place.put("reference",reference);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return place;
            }
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        goToLocation(20.5937,78.9629,4);


    }
    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate cameraupdate = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.animateCamera(cameraupdate);


    }
}
