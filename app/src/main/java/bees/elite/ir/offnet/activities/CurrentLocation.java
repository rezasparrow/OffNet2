package bees.elite.ir.offnet.activities;

/**
 * Created by AZ on 2/9/2017.
 */

import android.Manifest;
import android.app.Activity;


import android.content.pm.PackageManager;
import android.os.Bundle;
        import android.app.Activity;
        import android.content.Context;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

        import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;

import bees.elite.ir.offnet.config.PrefManager;

public class CurrentLocation extends Activity implements LocationListener{
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    private PrefManager pref;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new PrefManager(this);
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        //txtLat = (TextView) findViewById(R.id.textview1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(CurrentLocation.this, Double.toString(location.getLatitude()), Toast.LENGTH_LONG).show();

        Toast.makeText(CurrentLocation.this, Double.toString(location.getLongitude()), Toast.LENGTH_LONG).show();


        pref.setLatitude(Double.toString(location.getLatitude()));
        pref.setLongitude(Double.toString(location.getLongitude()));


    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}