package asint;

public class Instruccion_programa {
	private Tipo tipo;
	private Exp e0;
	private Exp e1;
	private ExpCompuesta exp_compuesta;
	private Instrucciones is0;
	private Instrucciones is1;
	private Parametros preal;
	private Parametros pforms;
	private Decs decs;
	
	private int dir_ini;
	private int dir_sig;
	private int dir_medio;
	
	private int nivel;
	
	public Instruccion_programa(Tipo tipo) {
		this.tipo = tipo;
		is0 = new Instrucciones();
		is1 = new Instrucciones();
	}
	
	public Tipo getTipo() {return this.tipo;}
	public void setE0(Exp e0) {this.e0 = e0;}
	public void setE1(Exp e1) {this.e1 = e1;}
	public void setIs0(Instrucciones is) {this.is0 = is;}
	public void setIs1(Instrucciones is) {this.is1 = is;}
	public void setPreal(Parametros p) {this.preal = p;}
	public void setDecs(Decs decs) {this.decs = decs;}
	public void setExp_compuesta(ExpCompuesta exp_compuesta) {this.exp_compuesta = exp_compuesta;}

	public Exp getE0() {return e0;}
	public Exp getE1() {return e1;}
	public Instrucciones getIs0() {return is0;}
	public Instrucciones getIs1() {return is1;}
	public Parametros getPreal() {return preal;}
	public Decs getDecs() {return decs;}
	public void setTipo(Tipo tipo) {this.tipo = tipo;}
	public ExpCompuesta getExp_compuesta() {return exp_compuesta;}

	public int getDir_ini() {return dir_ini;}
	public void setDir_ini(int dir_ini) {this.dir_ini = dir_ini;}
	public int getDir_sig() {return dir_sig;}
	public void setDir_sig(int dir_sig) {this.dir_sig = dir_sig;}

	public int getNivel() {return nivel;}
	public void setNivel(int nivel) {this.nivel = nivel;}

	public Parametros getPforms() {return pforms;}
	public void setPforms(Parametros pforms) {this.pforms = pforms;}

	public int getDir_medio() {
		return dir_medio;
	}

	public void setDir_medio(int dir_medio) {
		this.dir_medio = dir_medio;
	}

}
