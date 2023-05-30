package asint;

public class ExpBasica extends Exp{
	
	public ExpBasica(StringLocalizado id, StringLocalizado valor, Tipo t, TipoExpresion tExp) {
		this.setId(id);
		this.setValor(valor);
		this.setTipo(tExp);
		this.setTipoSol(t);
	}
	
	public int prioridad() {
		return 6;
	}

	public void procesa(Procesamiento procesamiento) {
	}

	
	

}
