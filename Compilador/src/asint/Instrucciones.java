package asint;
import java.util.*;

public class Instrucciones {
	private ArrayList<Instruccion> list;
	
	public Instrucciones() {
		this.list = new ArrayList<Instruccion>();
	}
	
	public Instrucciones add(Instruccion i) {
		this.list.add(i);
		return this;
	}
	
	
}
