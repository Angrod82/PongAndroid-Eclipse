package com.miprimeraaplicacion.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class PongGameView extends SurfaceView implements Callback {
	
	private PongThread paintThread;
	private BolaMoveThread bolaThread; 
	private ElementoPong raquetaIzda;
	private ElementoPong raquetaDcha;
	private ElementoPong bola;
	private ElementoPong elementoActivo = null;
	private int origenY;

	public PongGameView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {	}

	public void surfaceCreated(SurfaceHolder holder) {
		raquetaIzda = new Raqueta(new Coordenada(50, getHeight()/2-50), 20, 100);
		raquetaDcha = new Raqueta(new Coordenada(getWidth()-70, getHeight()/2-50), 20, 100);
		bola = new Bola(new Coordenada(getWidth()/2-5, getHeight()/2-5), 10, 10);
		
		paintThread = new PongThread(getHolder(), this);
		paintThread.setRunning(true);
		paintThread.start();
		
		bolaThread = new BolaMoveThread((Bola)bola, raquetaDcha, raquetaIzda, new Rect(0, 0, getWidth(), getHeight()), getContext());
		bolaThread.setRunning(true);
		bolaThread.start();
	}
		 
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		
		paintThread.setRunning(false);
		bolaThread.setRunning(false);
		while (retry) {
			try {
				paintThread.join();
				bolaThread.join();
				retry = false;
			} 
			catch (InterruptedException e) { }
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		
		paint.setColor(Color.WHITE); 
		canvas.drawColor(Color.BLACK);
		canvas.drawRect(raquetaIzda.getRectElemento(), paint);
		canvas.drawRect(raquetaDcha.getRectElemento(), paint);
		canvas.drawRect(bola.getRectElemento(), paint);	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//hemos pulsado
				if(raquetaIzda.getRectElemento().contains(x, y)) {
					elementoActivo = raquetaIzda;
					origenY = y;
					break;
				}
				if(raquetaDcha.getRectElemento().contains(x, y)) {
					elementoActivo = raquetaDcha;
					origenY = y;
					break;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				//hemos arrastrado
				if(elementoActivo != null) {
					Raqueta r = (Raqueta) elementoActivo;
					if(r.puedoMover(0, y - origenY, new Rect(0, 0, getWidth(), getHeight())))
						r.move(0, y - origenY);
				}
				origenY = y;
				break;
			case MotionEvent.ACTION_UP:
				//hemos levantado
				elementoActivo = null;
				break;
		}
		return true;
	}

}
