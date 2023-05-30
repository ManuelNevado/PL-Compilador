package procesamientos;

import asint.*;
import tiny.Programa;

public class ComprobacionTipo {
	
	private Programa programa;
	
	public ComprobacionTipo(Programa p) {
		this.programa = p;
		
		comprueba_tipo(this.programa);
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
				if (son_compatibles(i.getE0(), i.getE1()) && es_designador(i.getE0())) {
					res = true && true;
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
	

	
	private Boolean comprueba_tipo(Parametros pforms) {
		for(Parametro pform : pforms.getElements()) {
			return comprueba_parametro(pform);
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
		switch (e.tipo()) {
		case NUM:
			res = res && true;
			break;
		case REAL:
			res = res && true;
			break;
		case BOOL:
			res = res && true;
			break;
		case ARRAY:
			res = res && true;
			break;
		case ID:
			res = res && true;
			break;
		case SUMA:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.INT);
			break;
		case RESTA:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.INT);
			break;
		case AND:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case OR:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case MUL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.INT);
			break;
		case DIV:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.INT);
			break;
		case PORC:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.INT);
		case NOT:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case NOT2:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case EQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
		case UNEQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case LESS:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case LESS_EQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
		case GREATER:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case GREATER_EQUAL:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.BOOL);
			break;
		case INDEX:
			ec = (ExpCompuesta) e;
			res = res && (comprueba_tipo(ec.getE0()) && comprueba_tipo(ec.getE1()));
			ec.setTipoSol(Tipo.NULL);
			break;
		case ACC:
			ec = (ExpCompuesta) e;
			res = res && comprueba_tipo(ec.getE0());
			ec.setTipoSol(Tipo.NULL);
			break;
		case DREF:
			ec = (ExpCompuesta) e;
			res = res && comprueba_tipo(ec.getE0());
			break;
		}
		return res;
	}
	
	private Boolean comprueba_parametro(Parametro pform) {
		//TODO Parametro real
		
		
		return true;
	}
	
	//------------------------------- FUNCIONES AUXILIARES ------------------------------------------
	
	
	private Boolean son_compatibles(Exp e0, Exp e1) {
		if(e0.tipo() == e1.tipo())
			return true;
		else
			return false;
	}
	
	private Boolean es_designador(Exp e) {
		//sin definir
		return true;
	}
	
	
	//----------------------------------- MAIN -----------------------------------------------------
	public static void main(String args[]) {
		int a = 1;
		switch (a) {
		case 0:
			System.out.println("0");
			return;
		case 2:
			System.out.println("2");
			return;
		case 1:
			System.out.println("1");
			return;
		case 3:
			System.out.println("3");
			return;
			
		}
	}
	
}
