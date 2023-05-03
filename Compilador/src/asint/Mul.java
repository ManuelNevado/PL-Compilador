package asint;

public class Mul extends ExpMultiplicativa {
    public Mul(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}