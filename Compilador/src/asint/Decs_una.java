package asint;

public class Decs_una extends Decs {
   private Dec dec; 
   public Decs_una(Dec dec) {
      super();
      this.dec = dec;
   }   
   public Dec dec() {return dec;}
   public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
}