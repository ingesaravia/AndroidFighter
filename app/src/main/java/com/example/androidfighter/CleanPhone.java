package com.example.androidfighter;

import java.io.File;

import android.content.Context;
import android.widget.Toast;

public class CleanPhone {
	//si la Clase es Non-Activity requiere que la Activity le envie el contexto
	//LIMPIA LAS COPIAS DE SEGURIDAD REPETIDAS DE WHATSAPP
	//LIMPIA LA CACHE DEL CELU
	//LIMPIA ARCHIVOS INNECESARIOS
	Context miContexto;
   	
	public CleanPhone (Context miContexto){
		super();
		this.miContexto = miContexto;
	}
	
	public void borrarDirectorio(String directorio){
		File dir = new File(directorio);
		File[] listaArchivos = dir.listFiles();
		for (File archivo: listaArchivos)
			archivo.delete();
	}
	
	public void limpiarThumbs(){
		String path = "/storage/emulated/0/DCIM/.thumbnails";
		File directorioThumbs = new File(path);
		if (directorioThumbs.exists())
		{
			File[] listaArchivos = directorioThumbs.listFiles();
			for (File archivo : listaArchivos)
			{
				archivo.delete();
			}
		}
		else
		{
			Toast.makeText(miContexto, "No existe el directorio",Toast.LENGTH_SHORT).show();
		}
	}
	
	public void limpiarCache(){
		
	}
	
	public void limpiarWhatsapp(){
		//SE INDICAN LAS CARPETAS ESPECIFICAS PARA DENTRO DE WHATSAPP;
		//VN databases
		String pathDB = "/storage/emulated/0/Whatsapp/Databases";
		File dirWappDB = new File(pathDB);
		//VN voicenotes
		String pathVN = "/storage/emulated/0/Whatsapp/Media/WhatsApp Voice Notes";
		File dirWappVN = new File(pathVN);
		//VN audios
		String pathAu = "/storage/emulated/0/Whatsapp/Media/WhatsApp Audios";
		File dirWappAu = new File(pathAu);
		
		File[] listaArchivos; 
		
		//SE ELIMINAN LAS DATABASES REDUNDANTES DE WAPP
		if (dirWappDB.exists())	{
			listaArchivos = dirWappDB.listFiles();
			if (listaArchivos.length > 1) {
				File maxFile = new File(listaArchivos[0].getPath());
				long maxTamanio = listaArchivos[0].length();
			
				for (File archivo : listaArchivos) 	{
					if (archivo.length() >= maxTamanio) {
						maxFile.delete();
						maxFile = archivo;
						maxTamanio = archivo.length();
					} else	archivo.delete();
				}
			}
		} else 	{
			Toast.makeText(miContexto, "No existen DataBases",Toast.LENGTH_SHORT).show();
		}
		
		//SE ELIMINAN LOS VOICE NOTES
		if (dirWappVN.exists())	{
			listaArchivos = dirWappVN.listFiles();
			int cont = 0;
			for (File archivo : listaArchivos)	{
				if (archivo.isDirectory())
					borrarDirectorio(archivo.getPath());
				else
					archivo.delete();
				cont++;
			}
		}
		else	{
			Toast.makeText(miContexto, "No existen Voice Notes",Toast.LENGTH_SHORT).show();
		}
		
		//SE ELIMINAN LOS AUDIOS
				if (dirWappAu.exists())	{
					listaArchivos = dirWappAu.listFiles();
					int cont = 0;
					for (File archivo : listaArchivos)	{
						if (archivo.isDirectory())
							borrarDirectorio(archivo.getPath());
						else
							archivo.delete();
						cont++;
					}
				}
				else	{
					Toast.makeText(miContexto, "No existen Audios",Toast.LENGTH_SHORT).show();
				}
		
	}
	
}
