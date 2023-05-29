package asint;

public class Procedimiento extends Dec{
	
	private Parametros pforms;
	private Instrucciones is;
	private Decs decs;

	public Procedimiento(StringLocalizado id, Parametros pf, Decs decs,Instrucciones Is) {
		super(id, null, TipoDeclaracion.PROCEDIMIENTO);
		this.pforms = pf;
		this.is = Is;
		this.decs = decs;
	}
	
	public Parametros getPforms() {return this.pforms;}
	public Instrucciones getIs() {return this.is;}
	public Decs getDecs() {return this.decs;}
	
	
}
