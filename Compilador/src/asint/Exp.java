package asint;
import java.util.*;

public abstract class Exp  {
	private StringLocalizado id;
	private TipoExpresion t;
	private StringLocalizado valor;
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
}