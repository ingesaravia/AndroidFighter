package com.example.androidfighter;

import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityGps extends Activity {

	public Location loc;
	public LocationManager locManager;
	public LocationListener locListener;
	//private LocationProvider locProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        final Button btnLoc = (Button) findViewById(R.id.btnGpsPosicion);
        //ActivityCompat.requestPermissions(ActivityGps.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        
        btnLoc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//GpsFighter gt = new GpsFighter(getApplicationContext());
				GpsFighter gt = new GpsFighter(ActivityGps.this);
                Location loc = gt.getLocation();
                if( loc == null){
                    Toast mensaje = Toast.makeText(getApplicationContext(),"GPS No encuentra el valor",Toast.LENGTH_SHORT);
                    mensaje.show();
                }else {
                    double lat = loc.getLatitude();
                    double lon = loc.getLongitude();
                    Toast.makeText(getApplicationContext(),"GPS Latitudo = "+lat+"\n longitud = "+lon,Toast.LENGTH_SHORT).show();
                }
				
			}
		});
        
		}

}
