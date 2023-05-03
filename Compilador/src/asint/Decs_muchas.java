package asint;

public class Decs_muchas extends Decs {
   private Dec dec;
   private Decs decs;
   public Decs_muchas(Decs decs, Dec dec) {
      super();
      this.dec = dec;
      this.decs = decs;
   }
   public Dec dec() {return dec;}
   public Decs decs() {return decs;}
   public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}