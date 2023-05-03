package asint;
import java.util.*;

public abstract class Exp  {
   public Exp() {
   }   
   public abstract int prioridad();
   public abstract void procesa(Procesamiento procesamiento);
   public TipoExpresion tipo() {return TipoExpresion.NULL;}
   public List<TipoDeclaracion> tipoSol(){return new ArrayList<TipoDeclaracion>();}
}