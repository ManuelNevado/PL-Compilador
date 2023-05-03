package asint;

public abstract class Exp  {
   public Exp() {
   }   
   public abstract int prioridad();
   public abstract void procesa(Procesamiento procesamiento);
}