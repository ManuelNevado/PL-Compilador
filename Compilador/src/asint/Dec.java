package asint;

public class Dec  {
    private StringLocalizado id;
    private StringLocalizado val;
    private TipoDeclaracion tipo;
    private Tipo tipo_para_vinculacion;
    private Decs dec_proc;
    private Instrucciones is_proc;
    private Instrucciones is_general;
    private Parametros pforms;
    private Parametros preales;
    private StringLocalizado referencia;
    private Tipo tipo_array;
    private Campos campos;
    private Tipo tipo_puntero;
    
    private int dir;
    private int nivel;
    private int tam_datos;
    
    private int dir_sig;
    private int dir_ini;
    
    
    public Dec(StringLocalizado id, StringLocalizado val, TipoDeclaracion t) {
        this.id = id;
        this.val = val;
        this.tipo=t;
        this.tipo_para_vinculacion = Tipo.NULL;
        this.tipo_array = Tipo.NULL;
        this.is_general = new Instrucciones();
    }
    public StringLocalizado id() {return id;}
    public StringLocalizado val() {return val;}
    
    public TipoDeclaracion tipo() {return this.tipo;}
    
    public void setTipoVinculacion(Tipo t) {this.tipo_para_vinculacion=t;}
    public Tipo getTipoVinculacion() {return this.tipo_para_vinculacion;}
    
    public Decs getDecs() {return this.dec_proc;}
    public void setDecs(Decs decs) {this.dec_proc = decs;}
    
    public Instrucciones getIs() {return this.is_general;}
    public void setInstrucciones(Instrucciones is) {this.is_general = is;}
    
	public Parametros getPforms() {return pforms;}
	public void setPforms(Parametros pforms) {this.pforms = pforms;}
	
	public StringLocalizado getReferencia() {return referencia;}
	public void setReferencia(StringLocalizado ref) {this.referencia = ref;}
	
    public void procesa(Procesamiento p) {p.procesa(this);}
    
    public StringLocalizado getVal() {return this.val;}
    public void setVal(StringLocalizado val) {this.val = val;}
    
    public void setTipoArray(Tipo t) {this.tipo_array = t;}
    public Tipo getTipoArray() {return this.tipo_array;}
    
    public Campos getCampos() {return this.campos;}
    public void setCampos(Campos campos) {this.campos = campos;}
    
    public Tipo getTipoPuntero() {return tipo_puntero;}
    public void setTipoPuntero(Tipo t) {this.tipo_puntero = t;}
    
    
    public int getDir() {return this.dir;}
    public void setDir(int dir) {this.dir=dir;}
    
    public int getNivel() {return this.nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}
	public int getTam_datos() {
		return tam_datos;
	}
	public void setTam_datos(int tam_datos) {
		this.tam_datos = tam_datos;
	}
	public Parametros getPreales() {
		return preales;
	}
	public void setPreales(Parametros preales) {
		this.preales = preales;
	}
	public int getDir_sig() {
		return dir_sig;
	}
	public void setDir_sig(int dir_sig) {
		this.dir_sig = dir_sig;
	}
	public int getDir_ini() {
		return dir_ini;
	}
	public void setDir_ini(int dir_ini) {
		this.dir_ini = dir_ini;
	}
    

}