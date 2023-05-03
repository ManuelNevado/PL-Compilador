package asint;

public class ProcesamientoPorDefecto implements Procesamiento {
   public void procesa(Suma exp) {}
   public void procesa(Resta exp) {}
   public void procesa(Mul exp) {}
   public void procesa(Div exp) {}
   public void procesa(Id exp) {}
   public void procesa(Num exp) {}
   public void procesa(Dec dec) {}
   public void procesa(Decs_muchas decs) {}
   public void procesa(Decs_una decs) {}
   public void procesa(Prog_sin_decs prog) {}    
   public void procesa(Prog_con_decs prog) {}    
}
