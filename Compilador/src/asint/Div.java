package asint;

public class Div extends ExpMultiplicativa {
    public Div(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}