package asint;

public class Id extends Exp {
    private StringLocalizado id;
    public Id(StringLocalizado id) {
        super();
        this.id = id;
    }
    public StringLocalizado id() {return id;}
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
    public final int prioridad() {
        return 2;
    }
}