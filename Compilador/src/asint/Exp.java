package asint;
import java.util.*;

public abstract class Exp  {
	private StringLocalizado id;
	private TipoExpresion t;
	private StringLocalizado valor;
	private Tipo tipoSol;
	private int etiqueta;
	private Tipo tipoArray;
	
	public Exp() {}
	
	public abstract int prioridad();
	public abstract void procesa(Procesamiento procesamiento);
	public TipoExpresion tipo() {return t;}
	public List<TipoDeclaracion> tipoSol(){return new ArrayList<TipoDeclaracion>();}
	public void setId(StringLocalizado id) {this.id = id;}
	public void setTipo(TipoExpresion t) {this.t = t;}
	public StringLocalizado ID() {return this.id;}
	public StringLocalizado getValor() {return this.valor;}
	public void setValor(StringLocalizado s) {this.valor = s;}
	public Tipo getTipoSol() {return tipoSol;}
	public void setTipoSol(Tipo tipoSol) {this.tipoSol = tipoSol;}

	public int getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(int etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Tipo getTipoArray() {
		return tipoArray;
	}

	public void setTipoArray(Tipo tipoArray) {
		this.tipoArray = tipoArray;
	}

}