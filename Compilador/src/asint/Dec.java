package asint;

public class Dec  {
    private StringLocalizado id;
    private StringLocalizado val;
    private TipoDeclaracion tipo;
    private Tipo tipo_para_vinculacion;
    private Decs dec_proc;
    private Instrucciones is_proc;
    private Parametros pforms;
    private StringLocalizado referencia;
    private Tipo tipo_array;
    private Campos campos;
    public Dec(StringLocalizado id, StringLocalizado val, TipoDeclaracion t) {
        this.id = id;
        this.val = val;
        this.tipo=t;
        this.tipo_para_vinculacion = Tipo.NULL;
        this.tipo_array = Tipo.NULL;
    }
    public StringLocalizado id() {return id;}
    public StringLocalizado val() {return val;}
    
    public TipoDeclaracion tipo() {return this.tipo;}
    
    public void setTipoVinculacion(Tipo t) {this.tipo_para_vinculacion=t;}
    public Tipo getTipoVinculacion() {return this.tipo_para_vinculacion;}
    
    public Decs getDecs() {return this.dec_proc;}
    public void setDecs(Decs decs) {this.dec_proc = decs;}
    
    public Instrucciones getIs() {return this.is_proc;}
    public void setInstrucciones(Instrucciones is) {this.is_proc = is;}
    
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

}