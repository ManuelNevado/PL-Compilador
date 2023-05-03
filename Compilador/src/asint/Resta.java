package asint;

public class Resta extends ExpAditiva {
    public Resta(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}