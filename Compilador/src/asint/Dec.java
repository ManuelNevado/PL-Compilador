package asint;

public class Dec  {
    private StringLocalizado id;
    private StringLocalizado val;
    private TipoDeclaracion tipo;
    public Dec(StringLocalizado id, StringLocalizado val, TipoDeclaracion t) {
        this.id = id;
        this.val = val;
        this.tipo=t;
    }
    public StringLocalizado id() {return id;}
    public StringLocalizado val() {return val;}
    public TipoDeclaracion tipo() {return this.tipo;}
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}