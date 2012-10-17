package com.miprimeraaplicacion.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class PongActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pong);
        
        TextView play = (TextView)findViewById(R.id.play_button);
        play.setOnTouchListener(
        	new OnTouchListener() {
        		public boolean onTouch(View v, MotionEvent event) {
        			empiezaJuego();
        			return false;
        		}
        	}
        );
        
        TextView options = (TextView)findViewById(R.id.options_button);
        options.setOnTouchListener(
        	new OnTouchListener() {
        		public boolean onTouch(View v, MotionEvent event) {
        			muestraOpciones();
        			return false;
        		}
        	}
        );
        
        TextView exit = (TextView)findViewById(R.id.exit_button);
        exit.setOnTouchListener(
        	new OnTouchListener() {
        		public boolean onTouch(View v, MotionEvent event) {
        			Toast.makeText(getApplicationContext(),
        			R.string.menu_exit, Toast.LENGTH_SHORT).show();
        			return false;
        		}
        	}
        );
    }
    
    private void empiezaJuego() {
    	Intent juego = new Intent(this, PongJuego.class);
    	this.startActivity(juego);
    }
    
    private void muestraOpciones() {
    	Intent opciones = new Intent(this, PongOpcionesActivity.class);
    	this.startActivity(opciones);
    }
    
}
