package asint;

public class ExpAcc extends Exp{
	
	private Exp e0;
	private StringLocalizado name;
	private Campo campo;


	public ExpAcc() {
		this.e0 = this;
		this.name = null;
	}

	public int prioridad() {return 3;}
	public void procesa(Procesamiento procesamiento) {}
	
	public Exp getE0() {return this.e0;}
	public void setE0(Exp e) {this.e0=e;}
	
	public StringLocalizado getName() {return name;}
	public void setName(StringLocalizado name) {this.name = name;}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}


}
