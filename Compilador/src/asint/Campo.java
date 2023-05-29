package asint;

public class Campo {
	private StringLocalizado id;
	private Tipo tipo;
	
	private int dir;
	private int nivel;
	private int tam_datos;
	
	public Campo(StringLocalizado id, Tipo tipo) {
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getTam_datos() {
		return tam_datos;
	}

	public void setTam_datos(int tam_datos) {
		this.tam_datos = tam_datos;
	}
	
	
	
}
