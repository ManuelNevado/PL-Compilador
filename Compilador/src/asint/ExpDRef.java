package asint;

public class ExpDRef extends Exp{
	private Exp e0;
	private Exp referencia;
	public ExpDRef() {}
	
	public int prioridad() {return 3;} //por poner algo XDDDD
	public void procesa(Procesamiento procesamiento) {}

	public Exp getE0() {return e0;}
	public void setE0(Exp e0) {this.e0 = e0;}

	public Exp getReferencia() {return referencia;}
	public void setReferencia(Exp referencia) {this.referencia = referencia;}
	
}
