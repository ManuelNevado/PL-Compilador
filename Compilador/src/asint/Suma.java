package asint;

import java.util.*;

public class Suma extends ExpAditiva {
    public Suma(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public void procesa(Procesamiento p) {
       p.procesa(this); 
    }     
    @Override
    public TipoExpresion tipo() {return TipoExpresion.SUMA;}
    @Override
    public List<TipoDeclaracion> tipoSol(){
    	ArrayList<TipoDeclaracion> l = new ArrayList<TipoDeclaracion>();
    	l.add(TipoDeclaracion.INT);
    	return l;
    }
}