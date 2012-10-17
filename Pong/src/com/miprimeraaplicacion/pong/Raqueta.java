package com.miprimeraaplicacion.pong;

public class Raqueta extends ElementoPong implements ElementoPongMovil {

	public Raqueta(Coordenada origen, int ancho, int alto) {
		super(origen, ancho, alto);
	}

	public void move(int x, int y) {
		origen.setY(origen.getY() + y);
	}

}
