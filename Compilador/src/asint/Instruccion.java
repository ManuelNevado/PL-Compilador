package asint;

public class Instruccion {
	private Tipo tipo;
	private Exp e0;
	private Exp e1;
	private Instrucciones is0;
	private Instrucciones is1;
	private Parametros preal;
	private Decs decs;
	
	public Instruccion(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Tipo getTipo() {return this.tipo;}
	public void setE0(Exp e0) {this.e0 = e0;}
	public void setE1(Exp e1) {this.e1 = e1;}
	public void setIs0(Instrucciones is) {this.is0 = is;}
	public void setIs1(Instrucciones is) {this.is1 = is;}
	public void setPreal(Parametros p) {this.preal = p;}
	public void setDecs(Decs decs) {this.decs = decs;}

}
