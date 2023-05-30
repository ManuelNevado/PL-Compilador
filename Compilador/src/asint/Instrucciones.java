package asint;
import java.util.*;

public class Instrucciones {
	private ArrayList<Instruccion_programa> list;
	
	public Instrucciones() {
		this.list = new ArrayList<Instruccion_programa>();
	}
	
	public Instrucciones add(Instruccion_programa i) {
		this.list.add(i);
		return this;
	}
	
	public ArrayList<Instruccion_programa> getElements(){return this.list;}
	
	
}
