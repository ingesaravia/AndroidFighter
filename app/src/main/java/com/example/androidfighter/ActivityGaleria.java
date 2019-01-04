package com.example.androidfighter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class ActivityGaleria extends Activity{

	//	METODO ONCREATE ---------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        
        final ImageView miImgView = (ImageView) findViewById(R.id.imgView);
        final Button mibtnImgAnterior = (Button) findViewById(R.id.btnImgAnterior);
        final Button mibtnImgSiguiente = (Button) findViewById(R.id.btnImgSiguiente);
        final Spinner miSpinner = (Spinner) findViewById(R.id.spinnerImg);
        
        String path="/c/";
        miImgView.setImageBitmap(BitmapFactory.decodeFile(path));
        
        
        mibtnImgAnterior.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        mibtnImgSiguiente.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	
    }
}
