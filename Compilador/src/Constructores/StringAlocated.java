package Constructores;

public class StringAlocated {
	private String s;
	private int fila;
	private int columna;
	
	public StringAlocated(String s, int fila, int columna) {
		this.s=s;
		this.fila = fila;
		this.columna = columna;
	}
	
	public String toString() {
		return this.s;
	}
	
	public int getFIla() {return this.fila;}
	public int getColumna() {return this.columna;}
	
}
