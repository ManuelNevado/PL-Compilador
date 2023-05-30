package procesamientos;

import asint.*;
import tiny.Programa;

public class ComprobacionTipo {
	
	private Programa programa;
	
	public ComprobacionTipo(Programa p) {
		this.programa = p;
		
		if(comprueba_tipo(this.programa))
			System.out.println("COMPROBACION OK !!");
		else
			System.out.println("COMPROBACION -> ERROR!");
	}
	
	private Boolean comprueba_tipo(Programa p) {
		return comprueba_tipo(p.getDecs()) && comprueba_tipo(p.getIs());
		
	}
	
	private Boolean comprueba_tipo(Decs decs) {
		Boolean res = true;
		for(Dec d : decs.getElements()) {
			switch(d.tipo()) {
			case DEC_TYPE:
				res = res && comprueba_tipo(d.getTipoVinculacion(),d);
				//hacer dec.tipo = tipo para nuestra implementacion es redundante
				break;
			case DEC_VAR:
				res = res && comprueba_tipo(d.getTipoVinculacion(),d);
				break;
			case DEC_PROC:
				res = res && comprueba_tipo(d.getPforms()) && comprueba_tipo(d.getDecs()) && comprueba_tipo(d.getIs());
				break;
			}
			
		}
		
		return res;
	}
	
	private Boolean comprueba_tipo(Tipo t, Dec dec) {
		Boolean res = true;
		switch(t) {
		case REGISTRO:
			res = res && comprueba_tipo(dec.getCampos());
			break;
		case PUNTERO:
			res = res && comprueba_tipo(dec.getTipoVinculacion(),dec);
			break;
		}
		
		return res;
	}
	
	private Boolean comprueba_tipo(Instrucciones Is) {
		Boolean res = true;
		for(Instruccion_programa i :Is.getElements()) {
			switch (i.getTipo()){
			case ASIG:
				res = res && comprueba_tipo(i.getE0()) && comprueba_tipo(i.getE1());
				if (i.getE0().getTipoSol() == Tipo.REF && es_designador(i.getE0())) {
					res = res && true;
				}else
					res = res && false;
				break;
			case IF_THEN:
				res = res && (comprueba_tipo(i.getE0()) &&
						comprueba_tipo(i.getIs0()));
				break;
			case IF_THEN_ELSE:

				res = res && (comprueba_tipo(i.getE0()) &&
						comprueba_tipo(i.getIs0()) &&
						comprueba_tipo(i.getIs1()));
				break;
			case WHILE:
				res = res && (comprueba_tipo(i.getE0()) &&
						comprueba_tipo(i.getIs0()));
				break;
			case READ:
				res = res && comprueba_tipo(i.getE0());
				break;
			case WRITE:
				res = res && comprueba_tipo(i.getE0());
				break;
			case NL:
				res = res && true;
				break;
			case RES_MEM:
				res = res && comprueba_tipo(i.getE0());
				break;
			case DEL_MEM:
				res = res && comprueba_tipo(i.getE0());
				break;
			case CALL:
				//TODO
				res = res && true;
				break;
			case COMP:
				res = res && (comprueba_tipo(i.getDecs()) && 
							  comprueba_tipo(i.getIs0()));
				break;
						
				default:
					res = res && true;
					break;
			}
		}
		
		return res;
	}
	

	
	
	private Boolean comprueba_tipo(Campos cs) {
		for(Campo c : cs.getLista()) {
			comprueba_tipo(c.getTipo());
		}
		return true;
	}
	

	
	private Boolean comprueba_tipo(Parametros params) {
		for(Parametro param : params.getElements()) {
			return comprueba_parametro(param);
		}
		return true;
	}
	
	private Boolean comprueba_tipo(Tipo t) {
		return true;
	}
	
	private Boolean comprueba_tipo(Exp e) {
		Boolean res = true;
		ExpCompuesta ec;
		ExpAcc ea;
		ExpDRef edr;
		TipoExpresion t = e.tipo();
		switch (t) {
		
		case NUM:
			e.setTipoSol(Tipo.INT);
			res = res && true;
			break;
			
		case REAL:
			e.setTipoSol(Tipo.REAL);
			res = res && true;
			break;
			
		case BOOL:
			e.setTipoSol(Tipo.BOOL);
			res = res && true;
			break;
			
		case ARRAY:
			e.setTipoSol(Tipo.PUNTERO);
			res = res && true;
			break;
			
		case ID:
			e.setTipoSol(Tipo.REF);
			res = res && true;
			break;
		case SUMA, RESTA, MUL, DIV:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			
			if(ec.getE0().getTipoSol() == Tipo.INT && ec.getE1().getTipoSol() == Tipo.INT)
				ec.setTipoSol(Tipo.INT);
			else if(ec.getE0().getTipoSol() == Tipo.REAL && ec.getE1().getTipoSol() == Tipo.REAL)
				ec.setTipoSol(Tipo.REAL);
			else
				res = false;
			break;

		case AND, OR:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			
			if(ec.getE0().getTipoSol() == Tipo.BOOL && ec.getE1().getTipoSol() == Tipo.BOOL)
				ec.setTipoSol(Tipo.BOOL);
			else
				res = false;
			break;
			
		case PORC:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			if(ec.getE0().getTipoSol() == Tipo.INT && ec.getE1().getTipoSol() == Tipo.INT)
				ec.setTipoSol(Tipo.INT);
			else if(ec.getE0().getTipoSol() == Tipo.REAL && ec.getE1().getTipoSol() == Tipo.REAL)
				ec.setTipoSol(Tipo.INT);
			else
				res = false;
			
		case NOT, NOT2:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()));
			if(ec.getE0().getTipoSol() == Tipo.BOOL)
				ec.setTipoSol(Tipo.BOOL);
			else
				res = false;
			break;
			
		case EQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			if(son_compatibles(ec.getE0(), ec.getE1()))
				ec.setTipoSol(Tipo.BOOL);
			else
				res = false;
			break;
			
		case UNEQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			if(son_compatibles(ec.getE0(), ec.getE1()))
				ec.setTipoSol(Tipo.BOOL);
			else
				res = false;
			break;
			
		case LESS, LESS_EQUAL, GREATER, GREATER_EQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			if(ec.getE0().getTipoSol() == Tipo.INT && ec.getE1().getTipoSol() == Tipo.INT)
				ec.setTipoSol(Tipo.BOOL);
			else if(ec.getE0().getTipoSol() == Tipo.REAL && ec.getE1().getTipoSol() == Tipo.REAL)
				ec.setTipoSol(Tipo.BOOL);
			else
				res = false;
			break;
			
		case INDEX:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			if(ec.getE0().getTipoSol() == Tipo.ARRAY && ec.getE1().getTipoSol() == Tipo.INT)
				ec.setTipoSol(ec.getE0().getTipoArray());
			else
				res = false;
			break;
			
		case ACC:
			ea = (ExpAcc) e;
			res = res && comprueba_tipo(ea.getE0());
			ea.setTipoSol(ea.getCampo().getTipo());
			break;
			
		case DREF:
			edr = (ExpDRef) e;
			res = res && comprueba_tipo(edr.getE0());
			edr.setTipoSol(edr.getE0().getTipoSol());
			break;
		}
		return res;
	}
	
	private Boolean comprueba_parametro(Parametro param) {
		Boolean res = true;
		switch(param.getT_parametro()) {
		
		//Parametros Formales
		case P_VAR:
			res = res && comprueba_tipo(param.getTipo());
			break;
		case P_VAL:
			res = res && comprueba_tipo(param.getTipo()) && comprueba_tipo(param.getE0());
			if(param.getE0().getTipoSol() ==  param.getTipo()) {
				res = res && true;
			}else
				res = false;
			break;
			
		//Parametros Reales
		case P_REAL:
			res = res && comprueba_tipo(param.getTipo());
			break;
		}
		
		return res;
	}
	
	//------------------------------- FUNCIONES AUXILIARES ------------------------------------------
	
	
	private Boolean son_compatibles(Exp e0, Exp e1) {
		
		if(e0.getTipoSol() == e1.getTipoSol())
			return true;
		else
			return false;
	}
	
	private Boolean es_designador(Exp e) {
		return true;
	}
	
	
	//----------------------------------- MAIN -----------------------------------------------------
	public static void main(String args[]) {
		//Programa A
		// int a
		// a = 5
		
		//Programa B
		// a = 5 + 2
		
		
		TinyASint tiny = new TinyASint();
		
		StringLocalizado int_a = new StringLocalizado("a",0,0);
		StringLocalizado valor_a = new StringLocalizado("5",1,1);
		StringLocalizado valor_b = new StringLocalizado("2",1,1);
		
		Dec decA = tiny.dec_var(int_a,valor_a,TipoDeclaracion.DEC_VAR);
		Instruccion_programa asig_a =  tiny.asig(new ExpBasica(int_a,null,Tipo.INT, TipoExpresion.ID), new ExpBasica(null,valor_a,Tipo.INT, TipoExpresion.NUM));
		
		
		ExpCompuesta suma_b = new ExpCompuesta(new ExpBasica(null,valor_a,Tipo.INT, TipoExpresion.NUM),new ExpBasica(null,valor_b,Tipo.INT, TipoExpresion.NUM), TipoExpresion.SUMA);	
		Instruccion_programa asig_b =  tiny.asig(new ExpBasica(int_a,null,Tipo.INT, TipoExpresion.ID), suma_b);

		
	
		
		Instrucciones isA = new Instrucciones();
		Decs decsA = new Decs();
		
		Instrucciones isB = new Instrucciones();
		
		isA = isA.add(asig_a);
		decsA = decsA.add(decA);
		Programa pA = new Programa(decsA, isA);
		
		isB = isB.add(asig_b);
		Programa pB = new Programa(decsA, isB);
		
		ComprobacionTipo ct = new ComprobacionTipo(pA);
		
	}
	
	
	
	
	
	
	
}
