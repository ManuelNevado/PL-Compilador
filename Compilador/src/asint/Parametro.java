package asint;

public class Parametro {
	private StringLocalizado id;
	private Tipo tipo;
	
	public Parametro(StringLocalizado id, Tipo tipo) {
		this.setId(id);
		this.setTipo(tipo);
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
	
	
}
