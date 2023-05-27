package asint;

public class Campo {
	private StringLocalizado id;
	private TipoDeclaracion tipo;
	
	public Campo(StringLocalizado id, TipoDeclaracion tipo) {
		this.setId(id);
		this.setTipo(tipo);
	}

	public StringLocalizado getId() {
		return id;
	}

	public void setId(StringLocalizado id) {
		this.id = id;
	}

	public TipoDeclaracion getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeclaracion tipo) {
		this.tipo = tipo;
	}
	
	
	
}
