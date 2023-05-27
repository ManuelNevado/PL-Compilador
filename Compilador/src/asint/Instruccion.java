package asint;

public class Instruccion {
	private StringLocalizado id;
	private Tipo tipo;
	
	public Instruccion(StringLocalizado id, Tipo tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public StringLocalizado getId() {return this.id;}
	public Tipo getTipo() {return this.tipo;}
}
