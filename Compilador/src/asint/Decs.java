package asint;

import java.util.ArrayList;

public abstract class Decs {
private ArrayList<Dec> lista;
	
	public Decs() {
		this.lista = new ArrayList<Dec>();
	}
	
	public Decs add(Dec d) {
		this.lista.add(d);
		return this;
	}
	
	public abstract void procesa(Procesamiento p);
}