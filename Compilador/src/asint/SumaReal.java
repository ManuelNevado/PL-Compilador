package asint;

import java.util.ArrayList;
import java.util.List;

public class SumaReal extends ExpAditiva{
	public SumaReal(Exp arg1, Exp arg2) {
		super(arg1, arg2);
	}
	
	public void procesa(Procesamiento p) {
		p.procesa(this);
	}
	
	@Override
	public TipoExpresion tipo(){return TipoExpresion.SUMAREAL;}
	@Override
	public List<TipoDeclaracion> tipoSol(){
    	ArrayList<TipoDeclaracion> l = new ArrayList<TipoDeclaracion>();
    	l.add(TipoDeclaracion.REAL);
    	return l;
    }

}
