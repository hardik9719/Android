package com.example.hardik.connection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

//import static com.example.hardik.myapplication.R.id.tvans;
//import static com.example.hardik.myapplication.R.id.tvloc;

public class GoogleMap extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    com.google.android.gms.maps.GoogleMap mgoogleMap;
    GoogleApiClient googleApiClient;
    EditText tvloc;
    TextView tvans;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.terrain:
                mgoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.satellite:
                mgoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
    }

    public void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        mgoogleMap = googleMap;

        if (mgoogleMap != null) {
            Toast.makeText(getBaseContext(),"map loaded",Toast.LENGTH_LONG).show();
     /*       mgoogleMap.setOnMarkerDragListener(new com.google.android.gms.maps.GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    Geocoder gc = new Geocoder(MainActivity.this);
                    LatLng ll = marker.getPosition();
                    double lat = ll.latitude;
                    double lng = ll.longitude;
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocation(lat, lng, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = list.get(0);
                    marker.setTitle(address.getLocality());
                    marker.showInfoWindow();

                }
            });*/
           /* mgoogleMap.setInfoWindowAdapter(new com.google.android.gms.maps.GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);
                    TextView tvLocality = v.findViewById(R.id.tvlocality);
                    TextView tvLatitude = v.findViewById(R.id.tvLatitude);
                    TextView tvLongitude = v.findViewById(R.id.tvLongitude);
                    TextView tvDistance = v.findViewById(R.id.tvDistance);
                    LatLng ll = marker.getPosition();
                    tvLocality.setText(marker.getTitle());
                    tvLatitude.setText("Latitude" + ll.latitude);
                    tvLongitude.setText("Longitude" + ll.longitude);

                    LatLng latLngA = new LatLng(12.3456789, 98.7654321);
                    LatLng latLngB = new LatLng(98.7654321, 12.3456789);
                    Location locationA = new Location("point A");
                    locationA.setLatitude(latLngA.latitude);
                    locationA.setLongitude(latLngA.longitude);
                    Location locationB = new Location("point B");

                    locationB.setLatitude(latLngB.latitude);
                    locationB.setLongitude(latLngB.longitude);


                    double distance = locationA.distanceTo(locationB);
                    tvDistance.setText("Distance" + (distance / 1000) + "Km");
                    return v;
                }
            });*/
           /* MarkerOptions markerOptions = new MarkerOptions()
                    .title("My Location")
                    .position(new LatLng(lat, lng));

            mgoogleMap.addMarker(markerOptions);*/

        }

//        goToLocation(19.234933, 72.844963, 15);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mgoogleMap.setMyLocationEnabled(true);
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this).build();
//        googleApiClient.connect();
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate cameraupdate = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mgoogleMap.moveCamera(cameraupdate);
    }

    Marker marker;

/*    public void geolocate(View view) throws IOException {
        tvloc = (EditText) findViewById(R.id.tvloc);
        String location = tvloc.getText().toString();
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address address = list.get(0);
        String locality = address.getLocality();
        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocation(lat, lng, 16);
//        if(marker!=null)
//            marker.remove();
        MarkerOptions markerOptions = new MarkerOptions()
                .title(locality)
                .draggable(true)
                .position(new LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));

        mgoogleMap.addMarker(markerOptions);

    }*/

    private void createMarker(double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .title("Your Location")
                .draggable(true)
                .position(new LatLng(lat, lng))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));

        mgoogleMap.addMarker(markerOptions);
    }

    LocationRequest locationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Cannot get location", Toast.LENGTH_LONG).show();
        } else {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll, 15);
        }
    }

    private LocationManager locationManager;
    private LocationListener locationListener;

   /* public void checkLocation(View v) throws IOException {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                    double lat=location.getLatitude();
                    double lng=location.getLongitude();
                    tvans= (TextView) findViewById(R.id.tvans);
                tvans.setText("latitude:"+lat+"    Longitide:"+lng);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(getBaseContext(), "enabled", Toast.LENGTH_LONG).show();
                      }else {
            Toast.makeText(getBaseContext(), "disabled", Toast.LENGTH_SHORT).show();
            // Check if enabled and if not send user to the GPS settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        }


    }*/


}
