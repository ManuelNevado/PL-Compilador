package asint;

public class Prog_con_decs extends Prog {
  private Exp exp;
  private Decs decs;
   public Prog_con_decs(Exp exp, Decs decs) {
      super();
      this.exp = exp;
      this.decs = decs;
   }   
   public Exp exp() {return exp;}
   public Decs decs() {return decs;}
   public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}