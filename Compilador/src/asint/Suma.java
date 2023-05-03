package asint;

public class Suma extends ExpAditiva {
    public Suma(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}