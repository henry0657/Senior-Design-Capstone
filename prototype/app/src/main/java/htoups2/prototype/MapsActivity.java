package htoups2.prototype;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationSource,LocationListener {

    protected GoogleMap mMap;
    private Marker myMarker;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
               == PackageManager.PERMISSION_GRANTED)
            mMap.setMyLocationEnabled(true);

           // Toast.makeText(MapsActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

                @Override
                public void onLocationChanged(Location location) {
                    // TODO Auto-generated method stub

                    CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

                    mMap.moveCamera(center);
                    mMap.animateCamera(zoom);
                }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String timestamp = "" ;
        try{timestamp=getIntent().getExtras().getString("timestamp");}
        catch(NullPointerException e){}



        if(timestamp != "") {
            double lat = getIntent().getExtras().getDouble("latitude");
            double lon = getIntent().getExtras().getDouble("longitude");
            double height = getIntent().getExtras().getDouble("height");
            String street = getIntent().getExtras().getString("street");
            LatLng location = new LatLng(lat,lon);


            MarkerOptions new_mark = new MarkerOptions().title("ID: " + street).position(location).snippet("Flood level is " + height + " inches on " + timestamp.substring(5,10) + " at " + timestamp.substring(11,16) );
            if( height > 8.0){
                new_mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            }
            else if(height > 3.0){
                new_mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

            }
            else new_mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            float v = (float)14.0;
            myMarker = mMap.addMarker(new_mark);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,v));
        }
        else {
            double x = 2.;
            LatLng batrouge = new LatLng(30.44072, -91.160742);
            String street = "dummy";
            double time = 12.00;
            // Add a marker in baton rouge and move the cam
            MarkerOptions initial_mark = new MarkerOptions().position(batrouge).title("DUMMY Flood level is " + x + " feet ");

            myMarker = mMap.addMarker(initial_mark);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(batrouge));
        }
    }
   /* @Override
    public boolean onMarkerClick(final Marker marker){
        if(marker.equals(myMarker)){

        }

    }*/

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
