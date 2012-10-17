package com.miprimeraaplicacion.pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

public class PongOpcionesActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_opciones);
		CheckBox sonido = (CheckBox) findViewById(R.id.checkBoxSonido);
		sonido.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View arg0, MotionEvent arg1) {
				PongOpciones.getInstance().toggleSound();
				return false;
			}
		});
		
		CheckBox vibracion = (CheckBox) findViewById(R.id.checkBoxVibracion);
		vibracion.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				PongOpciones.getInstance().toggleVibration();
				return false;
			}
		});
		 
		sonido.setChecked(PongOpciones.getInstance().soundEnabled());
		vibracion.setChecked(PongOpciones.getInstance().vibrationEnabled());
	}
}
