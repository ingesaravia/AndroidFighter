package com.example.androidfighter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class GpsFighter implements LocationListener {
	//si la Clase es Non-Activity requiere que la Activity le envie el contexto
	Context miContexto;
   	
	public GpsFighter (Context miContexto){
		super();
		this.miContexto = miContexto;
	}
	
    public Location getLocation(){
        /*if (ContextCompat.checkSelfPermission(miContexto, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            Log.e("fist","error");
            return null;
        }*/
       	LocationManager locManager = (LocationManager)miContexto.getSystemService(miContexto.LOCATION_SERVICE);
        try {
 
        	boolean isGPSEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled){
                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10,this);
                Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return loc;
            }else{
                Log.e("sec","errpr");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	

}
