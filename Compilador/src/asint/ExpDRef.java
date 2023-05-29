package asint;

public class ExpDRef extends Exp{
	private Exp e0;
	public ExpDRef() {}
	
	public int prioridad() {return 3;}
	public void procesa(Procesamiento procesamiento) {}

	public Exp getE0() {return e0;}
	public void setE0(Exp e0) {this.e0 = e0;}
	
}
