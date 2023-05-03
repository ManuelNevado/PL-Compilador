package procesamientos;

import java.util.*;

import asint.*;
import tiny.Programa;


public class Vinculacion {
	//Sin subprogramas
	private Map<Object, Object> vinculo;
	private Programa programa;
	
	public Vinculacion(Programa p) {
		this.programa =p;
		this.vinculo = new HashMap<Object,Object>();
		vincula(vinculo,this.programa);
	}
	
	private boolean vincula(Map<Object, Object> map, Programa p) {
		
		if(vinculaVariables(map, p.getDeclaraciones())) {
			if(vinculaExpresiones(map, p.getExpresiones())) {
				if(compruebaTipos(map, p)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean compruebaTipos(Map<Object, Object> map, Programa p) {
		return true;
	}

	private boolean vinculaExpresiones(Map<Object, Object> map, List<Exp> expresiones) {
		for (Exp e: expresiones) {
			switch (e.tipo()){
			case ASIG:
				continue;
			case INT:
				continue;
			default:
				continue;
			}
		}
		
		
		return true;
	}

	private boolean vinculaVariables(Map<Object,Object> map, List<Decs> l) {
		for(Decs d : l) {
			Decs_una decVariable = (Decs_una) d;
			StringLocalizado id = decVariable.dec().id();
			TipoDeclaracion tipo = decVariable.dec().tipo();
			if(!map.containsKey(id))
				map.put(id, tipo);
			else
				return false;
		}
		return true;
	}
}
