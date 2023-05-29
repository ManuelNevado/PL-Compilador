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
		return prioridad;
	}
	
	public void procesa(Procesamiento procesamiento) {
	}
	
	public Exp getE0() {return this.e0;}
	public Exp getE1() {return this.e1;}
	
	
}
