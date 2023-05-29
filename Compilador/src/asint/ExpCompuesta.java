package asint;

public class ExpCompuesta extends Exp{
	private Exp e0;
	private Exp e1;
	private TipoExpresion tipo;
	private int prioridad;
	
	public ExpCompuesta(Exp e0, Exp e1, TipoExpresion t) {
		this.e0 = e0;
		this.e1 = e1;
		this.tipo = t;
		//Construir la prioridad aqui
		switch (t){
			case SUMA:
				this.prioridad = 0;
			case RESTA:
				//TODO
		}
				
	}
	
	public int prioridad() {
		return 0;
	}
	
	public void procesa(Procesamiento procesamiento) {
	}
	
	
}
