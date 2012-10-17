package com.miprimeraaplicacion.pong;

import android.graphics.Rect;

public abstract class ElementoPong {
	protected Coordenada origen;
	protected int ancho;
	protected int alto;
	
	public ElementoPong(Coordenada origen, int ancho, int alto) {
		this.origen = origen;
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public boolean puedoMover(int x, int y, Rect screen) {
		return screen.contains(origen.getX() + x, origen.getY() + y, origen.getX() + ancho + x, origen.getY() + alto + y);
	}
	
	public int getOrigenX() {
		return origen.getX();
	}
	
	public int getOrigenY() {
		return origen.getY();
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Rect getRectElemento() {
		return new Rect(getOrigenX(), getOrigenY(), getOrigenX()+ancho, getOrigenY()+alto);
	}
	
	public void setOrigenX(int newX) {
		origen.setX(newX);
	}
	
	public void setOrigenY(int newY) {
		origen.setY(newY);
	}
}
