package com.example.geolocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button button_location;
    TextView textView;
    LocationManager locationmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_location=findViewById(R.id.button_location);
        textView = findViewById(R.id.textview);

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100 );
        }
        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationmanager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity.this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude=location.getLongitude();
        double lattitude=location.getLatitude();
        textView.setText("Longitude : "+longitude+"\nLattitude : "+lattitude);
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
}
