package asint;
import java.util.*;

public class Campos{
	private ArrayList<Campo> lista;
	
	public Campos() {this.lista = new ArrayList<Campo>();}
	public Campos add(Campo c) {this.lista.add(c);return this;}
	public void setLista(ArrayList<Campo> list) {this.lista = list;}
	public ArrayList<Campo> getLista() {
		return lista;
	}
	
	
}
