package com.example.hardik.findride;

import android.os.AsyncTask;

/**
 * Created by hardik on 20/9/17.
 */

public class FBUserInfo extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        String fbgraphapi = "https://graph.facebook.com/me?fields=first_name,last_name,gender&access_token=EAACEdEose0cBAF2murZC0yD2xS0vAb0gVeQ8ZB6gJxsqLZBQXydhfyfEAWg4MLk5s4n61nCXwrGbnPZATmVcqwkpTPcA6nOCLL2FSeHE7bDXu5j7ysNz9Wl2NpzgV1eKJo0w5QHLqLZCKiDDcnZC1RiZCa0vZAjNFfp7QkLUpxYGq3tF1DwBTCUfgCOX0eBqFItVZAHWa3tEIZCQZDZD";

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
