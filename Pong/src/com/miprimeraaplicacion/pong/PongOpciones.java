package com.miprimeraaplicacion.pong;

public class PongOpciones {
	private static PongOpciones opciones = null;
	private boolean sonido;
	private boolean vibracion;
	
	private PongOpciones() {
		sonido = true;
		vibracion = true;
	}

	public static synchronized PongOpciones getInstance() {
		if(opciones == null)
			opciones = new PongOpciones();
		return opciones;
	}

	public void toggleSound() {
		sonido = !sonido;
	}
	
	public void toggleVibration() {
		vibracion = !vibracion;
	}

	public boolean soundEnabled() {
		return sonido;
	}

	public boolean vibrationEnabled() {
		return vibracion;
	}
}
