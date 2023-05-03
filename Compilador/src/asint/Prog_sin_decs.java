package asint;

public class Prog_sin_decs extends Prog {
  private Exp exp;
   public Prog_sin_decs(Exp exp) {
      super();
      this.exp = exp;
   }   
   public Exp exp() {return exp;}
   public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}