package com.example.dani.currentlocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    static final int Request_Location=1;
    LocationManager locationManager;



    TextView lat,log;
    Button getlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        getlocation=(Button)findViewById(R.id.getlocationbtn);
        lat=(TextView)findViewById(R.id.latitude);
        log=(TextView)findViewById(R.id.longitude);


        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
            }
        });

    }

    private void getlocation() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Request_Location);
        }else
        {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                    double latti = location.getLatitude();
                    double longi = location.getLongitude();
                    lat.setText("Lattitude: "+latti);
                    log.setText("Longitude: "+longi);

            }else{
                lat.setText("Lattitude: Unable to find location ");
                log.setText("Longitude: Unable to find location ");
                 }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case Request_Location:
                getlocation();
                break;
        }
    }
}
