package asint;
import java.util.*;

public abstract class Exp  {
	private StringLocalizado id;
	private Tipo t;
	public Exp() {
		this.t = Tipo.NULL;
	}   
	public abstract int prioridad();
	public abstract void procesa(Procesamiento procesamiento);
	public TipoExpresion tipo() {return TipoExpresion.NULL;}
	public Tipo tipo_2() {return this.t;}
	public List<TipoDeclaracion> tipoSol(){return new ArrayList<TipoDeclaracion>();}
	public void setId(StringLocalizado id) {this.id = id;}
	public void setTipo(Tipo t) {this.t = t;}
	
}