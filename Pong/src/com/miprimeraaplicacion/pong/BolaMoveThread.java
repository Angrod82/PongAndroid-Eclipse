package com.miprimeraaplicacion.pong;

import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class BolaMoveThread extends Thread {
	private Bola bola;
	private Rect screen;
	private ElementoPong raquetaDcha;
	private ElementoPong raquetaIzda;
	private boolean run;
	private int speed;
	private Vibrator v = null;
	private MediaPlayer mp = null;
	
	public BolaMoveThread(Bola bola, ElementoPong raquetaDcha, ElementoPong raquetaIzda, Rect screen, Context context) {
		this.bola = bola;
		this.raquetaDcha = raquetaDcha;
		this.raquetaIzda = raquetaIzda;
		this.screen = screen;
		this.run = false;
		this.speed = 1;
		v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		mp = MediaPlayer.create(context, R.raw.pong);
	}
	
	public void setRunning(boolean run) {
		this.run = run;
	}
	
	@Override
	public void run() {
		while(run) {
			try {
				Thread.sleep(10);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!bola.puedoMover(speed, speed, screen, raquetaIzda.getRectElemento(), raquetaDcha.getRectElemento())) {
				mp.start();
				bola.rebota(speed, speed, screen, raquetaIzda.getRectElemento(), raquetaDcha.getRectElemento());
				if(bola.puedoMover(speed, speed, screen))
					v.vibrate(50);
			}
			bola.move(speed, speed);
		}
	}
}
