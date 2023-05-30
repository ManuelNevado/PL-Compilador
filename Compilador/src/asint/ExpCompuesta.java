package asint;

public class ExpCompuesta extends Exp{
	private Exp e0;
	private Exp e1;
	private int prioridad;
	
	public ExpCompuesta(Exp e0, Exp e1, TipoExpresion t) {
		this.e0 = e0;
		this.e1 = e1;
		this.setTipo(t);
		//Construir la prioridad aqui
		switch (t){
			case EQUAL, UNEQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL:
				this.prioridad = 0;
			break;
			case SUMA,RESTA:
				this.prioridad = 1;
			break;
			case AND,OR :
				this.prioridad = 2;
			break;
			case MUL, DIV, PORC:
				this.prioridad = 3;
			break;
			case NOT, NOT2:
				this.prioridad = 4;
			break;
			case INDEX, ACC, DREF:
				this.prioridad = 4;
			break;
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
