package com.example.androidfighter;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private Camera miCamara;
	
	private void activarLinterna(){
		//ATRIBUTOS PARA LINTERNA
        String TAG="Error en Linterna";
        Camera.Parameters parametrosCamara;
        int cantCam = Camera.getNumberOfCameras();

        if (cantCam > 0)
        {
        	Log.e(TAG, "Hay camaras");
        	try{
        	CameraInfo miCamaraInfo = null;
        	Camera.getCameraInfo(0, miCamaraInfo);
        	int facing = miCamaraInfo.facing;
        	String cameraFacing;
        	if (facing == 0)
        		cameraFacing = "BACK";
        	else
        		cameraFacing = "FRONT";
        	Toast.makeText(MainActivity.this, "Camera "+cameraFacing, Toast.LENGTH_SHORT).show();
        	
        	if (Camera.open() != null)
        	{    	
        		miCamara = Camera.open();
        		miCamara.unlock();
        		parametrosCamara = miCamara.getParameters();
        		List<String> modosFlash = parametrosCamara.getSupportedFlashModes();
        		if (modosFlash.contains(Camera.Parameters.FLASH_MODE_TORCH))
        		{
        			parametrosCamara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        			parametrosCamara.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        			try
        			{
        				miCamara.setParameters(parametrosCamara);
        				miCamara.startPreview();
        			}
        			catch (Exception e)
        			{
        				Toast.makeText(MainActivity.this, "Error grave en Camara",Toast.LENGTH_SHORT).show();
        			}
        		}
        		else
        		{
        			Toast.makeText(MainActivity.this, "La Camara no tiene el modo Torch",Toast.LENGTH_SHORT).show();
        		}
        	}
        	else
        	{
        		Toast.makeText(MainActivity.this, "Camara Facing front (selfie) o No se reconoce la camara",Toast.LENGTH_LONG).show();
        	}
        	
        	}
        	catch(Exception e)
        	{
				Toast.makeText(MainActivity.this, "Error desconocido",Toast.LENGTH_SHORT).show();
			    //en lugar de MainActivity se puede usar getApplicationContext()
        	}
        }
        else
        {
        	Log.e(TAG, "No hay camaras");
			Toast.makeText(MainActivity.this, "No hay Camaras disponibles",Toast.LENGTH_SHORT).show();
		    //en lugar de MainActivity se puede usar getApplicationContext()
        }
        
	}
    private void onClickMostrarNotif(String nombre)
    {
    	//DEFINICION DE NOTIFICACION Y NOTIFICATION MANAGER
    	NotificationCompat.Builder miNotif = new NotificationCompat.Builder(MainActivity.this).setSmallIcon(R.drawable.ic_launcher).setContentTitle("BIENVENIDO").setContentText("Hola "+nombre);
    	NotificationManager miNotifMgr = (NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
    	Intent miintent=new Intent(MainActivity.this, ActivityDos.class);
    	//Nueva Actividad
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, miintent, 0);
        miNotif.setContentIntent(pendingIntent);
    	miNotifMgr.notify(1,miNotif.build());
    }
    
		//METODO ONCREATE ---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	
    	final EditText mitxtMensaje = (EditText) findViewById(R.id.editText1);
        final EditText mitxtNotificaciones = (EditText) findViewById(R.id.txtNotificaciones);
        final Button mibtnMensaje = (Button) findViewById(R.id.button1);
        final Button mibtnLinterna = (Button) findViewById(R.id.btnLinterna);
        final Button mibtnGPS = (Button) findViewById(R.id.btnGPS);
        final Button mibtnNotificaciones = (Button) findViewById(R.id.btnNotificaciones);
        final Button mibtnSdkVersion = (Button) findViewById(R.id.btnSdkVersion);
        final Button mibtnLimpiarCelu = (Button) findViewById(R.id.btnLimpiarCelu);
        final Button mibtnGaleria = (Button) findViewById(R.id.btnGaleria);
        
        mibtnGaleria.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent miIntent = new Intent(MainActivity.this, ActivityGaleria.class);
				startActivity(miIntent);
			}
		});
        
        mibtnLimpiarCelu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				CleanPhone miLimpiarCelu = new CleanPhone(MainActivity.this);
				miLimpiarCelu.limpiarCache();
				miLimpiarCelu.limpiarThumbs();
				miLimpiarCelu.limpiarWhatsapp();
			}
		});
        
        mibtnSdkVersion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    try{
			    	Context miContexto;
			    	double release=Double.parseDouble(Build.VERSION.RELEASE);
			    	String apiLevel = String.valueOf(Build.VERSION.SDK_INT);
				    String codeName="Unsupported";//below Jelly bean OR above nougat
				    if (release>=4.1 && release<4.4) codeName="Jelly Bean";
				    else if(release<5) codeName="Kit Kat";
				    else if(release<6) codeName="Lollipop";
				    else if(release<7) codeName="Marshmallow";
				    else if(release<8) codeName="Nougat";
				    Toast.makeText(MainActivity.this, codeName+" v"+release+", API Level: "+ apiLevel,Toast.LENGTH_LONG).show();
				    //en lugar de MainActivity se puede usar getApplicationContext()
	                	
			    }
				catch(Exception e)
			    {
					Toast.makeText(MainActivity.this, "No se puede acceder",Toast.LENGTH_SHORT).show();
				    //en lugar de MainActivity se puede usar getApplicationContext()
			    }
				}
		});

        mitxtNotificaciones.setText("Ingrese su nombre");
        
        mibtnGPS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent miIntent = new Intent(MainActivity.this, ActivityGps.class);
				startActivity(miIntent);
			}
		});
            
        mibtnNotificaciones.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nombre = mitxtNotificaciones.getText().toString();
				onClickMostrarNotif(nombre);
			}
		});
        
        
        mibtnMensaje.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mitxtMensaje.setText("Has presionado un boton");
				Intent miintent = new Intent(MainActivity.this, ActivityDos.class);
				startActivity(miintent);
				
			}
		});
        
        mibtnLinterna.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					activarLinterna();
							}
		});
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    //METODO onResume
    @Override
    protected void onResume()
    {
    	super.onResume();
    	try{
    		
    	}
    	catch (Exception e)
    	{
            Toast.makeText(getApplicationContext(), "onResume Falla", Toast.LENGTH_SHORT).show();
    	}
    }
    
    //METODO onPause
    @Override 
    protected void onPause() 
    {        
        super.onPause(); 
        try 
        { 
            //Si hay otra actividad la camara se debe detener
        	miCamara.release();
        	miCamara = null;
        } 
        catch( Exception e ) 
        { 
           Toast.makeText(getApplicationContext(),"onPause falla", Toast.LENGTH_SHORT).show();
        }        
    }
    
    
}
