package asint;

import java.util.ArrayList;

public class Decs {
private ArrayList<Dec> lista;
	
	public Decs() {
		this.lista = new ArrayList<Dec>();
	}
	
	public Decs add(Dec d) {
		this.lista.add(d);
		return this;
	}
	
	public ArrayList<Dec> getElements(){return this.lista;}
	
	public void procesa(Procesamiento p) {}
}