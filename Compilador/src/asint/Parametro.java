package asint;

public class Parametro {
	private StringLocalizado id;
	private Tipo tipo;
	private TipoParametro t_parametro;
	
	public Parametro(StringLocalizado id, Tipo tipo, TipoParametro t_parametro) {
		this.setId(id);
		this.setTipo(tipo);
		this.setTparametro(t_parametro);
	}

	public StringLocalizado getId() {
		return id;
	}

	public void setId(StringLocalizado id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public TipoParametro getTparametro() {
		return t_parametro;
	}

	public void setTparametro(TipoParametro t_parametro) {
		this.t_parametro = t_parametro;
	}
	
	
}
