package asint;

/**
 * @author manuelnevado
 *
 */
public class Parametro {
	private StringLocalizado id;
	private Tipo tipo;
	private TipoParametro t_parametro;
	private Campos campo;
	private Decs decs;
	
	private int dir;
	private int nivel;
	private int tam_datos;
	
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

	public TipoParametro getT_parametro() {
		return t_parametro;
	}

	public void setT_parametro(TipoParametro t_parametro) {
		this.t_parametro = t_parametro;
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

	public Campos getCampo() {
		return campo;
	}

	public void setCampo(Campos campo) {
		this.campo = campo;
	}

	public int getTam_datos() {
		return tam_datos;
	}

	public void setTam_datos(int tam_datos) {
		this.tam_datos = tam_datos;
	}

	public Decs getDecs() {
		return decs;
	}

	public void setDecs(Decs decs) {
		this.decs = decs;
	}
	
	
}
