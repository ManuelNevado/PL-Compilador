package asint;
import java.util.*;

public class Parametros {
	private ArrayList<Parametro> lista;
	private TipoParametro tipo;
	
	public Parametros() {
		this.lista = new ArrayList<Parametro>();
	}
	
	public Parametros add(Parametro p) {
		lista.add(p);
		return this;
	}
	
	public ArrayList<Parametro> getElements(){
		return this.lista;
	}

	public TipoParametro getTipo() {
		return tipo;
	}

	public void setTipo(TipoParametro tipo) {
		this.tipo = tipo;
	}
}
