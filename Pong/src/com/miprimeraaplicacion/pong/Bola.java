package com.miprimeraaplicacion.pong;

import android.graphics.Rect;

public class Bola extends ElementoPong implements ElementoPongMovil {
	
	public static final int DCHA_ARRIBA = 1;
	public static final int IZDA_ARRIBA = 2;
	public static final int IZDA_ABAJO = 3;
	public static final int DCHA_ABAJO = 4;
	private int direccion;

	public Bola(Coordenada origen, int ancho, int alto) {
		super(origen, ancho, alto);
		direccion = 1;
	}

	public void move(int x, int y) {
		switch(direccion) {
			case DCHA_ARRIBA:
				origen.setX(origen.getX() + x);
				origen.setY(origen.getY() - y);
				break;
			case IZDA_ARRIBA:
				origen.setX(origen.getX() - x);
				origen.setY(origen.getY() - y);
				break;
			case IZDA_ABAJO:
				origen.setX(origen.getX() - x);
				origen.setY(origen.getY() + y);
				break;
			case DCHA_ABAJO:
				origen.setX(origen.getX() + x);
				origen.setY(origen.getY() + y);
				break;
		}
	}
	
	public boolean puedoMover(int x, int y, Rect screen, Rect raquetaIzda, Rect raquetaDcha) {
		if(!puedoMover(x,y,screen))
			return false;
		if(chocaraCon(x,y,raquetaIzda))
			return false;
		if(chocaraCon(x,y,raquetaDcha))
			return false;
		return true;
	}
	
	private boolean chocaraCon(int x, int y, Rect raqueta) {
		if(raqueta.contains(origen.getX()+x, origen.getY()+y))
			return true;
		if(raqueta.contains(origen.getX()+ancho+x, origen.getY()+y))
			return true;
		if(raqueta.contains(origen.getX()+x, origen.getY()+alto+y))
			return true;
		if(raqueta.contains(origen.getX()+ancho+x, origen.getY()+alto+y))
			return true;
		return false;
	}
	
	public void rebota(int x, int y, Rect screen, Rect raquetaIzda, Rect raquetaDcha) {
		if(!puedoMover(x,y,screen)) {
			switch(direccion) {
				case DCHA_ARRIBA:
					direccion = (origen.getY() - y <= screen.top) ? DCHA_ABAJO : IZDA_ARRIBA;
					break;
				case IZDA_ARRIBA:
					direccion = (origen.getY() - y <= screen.top) ? IZDA_ABAJO : DCHA_ARRIBA;
					break;
				case IZDA_ABAJO:
					direccion = (origen.getY() + alto + y >= screen.bottom) ? IZDA_ARRIBA : DCHA_ABAJO;
					break;
				case DCHA_ABAJO:
					direccion = (origen.getY() + alto + y >= screen.bottom) ? DCHA_ARRIBA : IZDA_ABAJO;
					break;
			}
		}
		Rect raqueta = null;
		if(chocaraCon(x, y, raquetaIzda))
			raqueta = raquetaIzda;
		if(chocaraCon(x, y, raquetaDcha))
			raqueta = raquetaDcha;
		if(raqueta != null) {
			switch(direccion) {
				case DCHA_ARRIBA:
					direccion = (origen.getX()+ancho < raqueta.left) ? IZDA_ARRIBA : DCHA_ABAJO;
					break;
				case IZDA_ARRIBA:
					direccion = (origen.getX() > raqueta.right) ? DCHA_ARRIBA : IZDA_ABAJO;
					break;
				case IZDA_ABAJO:
					direccion = (origen.getX() > raqueta.right) ? IZDA_ARRIBA : DCHA_ABAJO;
					break;
				case DCHA_ABAJO:
					direccion = (origen.getX()+ancho < raqueta.left) ? IZDA_ABAJO : DCHA_ARRIBA;
					break;
			}
		}
	}
}
