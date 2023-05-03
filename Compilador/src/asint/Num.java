package asint;

public class Num extends Exp {
    private StringLocalizado num;
    public Num(StringLocalizado num) {
        super();
        this.num = num;
    }
    public StringLocalizado num() {return num;}
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
    public final int prioridad() {
        return 2;
    }
}