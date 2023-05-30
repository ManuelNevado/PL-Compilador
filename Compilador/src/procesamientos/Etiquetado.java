package procesamientos;

import asint.*;
import tiny.Programa;

public class Etiquetado {
	private int etq;
	private Programa programa;
	private RecolectaProcs pila;
	
	public Etiquetado(Programa p) {
		this.etq = 0;
		this.programa = p;
		pila = new RecolectaProcs();
		Etiqueta(p);
		System.out.println("ETIQUETADO OK !!!!!");
	}
	
	private void Etiqueta(Programa p) {
		etiqueta(p.getIs());
		pila.recolecta_procs(p.getDecs());
		while(!pila.getPila().empty()) {
			etiqueta(pila.getPila().pop());
		}
	}
	
	public void etiqueta(Dec dec) {
		if(dec.tipo() == TipoDeclaracion.DEC_TYPE) {
			
			dec.setDir_ini(this.etq);
			this.etq++;
			etiqueta(dec.getIs());
			dec.setDir_sig(this.etq);
			
		}else if(dec.tipo() == TipoDeclaracion.DEC_PROC) {
			dec.setDir_ini(this.etq);
			etiqueta(dec.getIs());
			this.etq+=2;
			dec.setDir_sig(this.etq);
			pila.recolecta_procs(dec.getDecs());
			while(!pila.getPila().empty()) {
				etiqueta(pila.getPila().pop());
			}
		}else if(dec.tipo() == TipoDeclaracion.DEC_VAR) {
			dec.setDir_ini(this.etq);
			this.etq++;
			etiqueta(dec.getIs());
			dec.setDir_sig(this.etq);
		}
	}
	public void etiqueta(Instrucciones Is) {
		for (Instruccion_programa i: Is.getElements()) {
			Tipo i_tipo = i.getTipo();
			if(i_tipo == Tipo.ASIG) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				etiqueta(i.getE1());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.WRITE) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.READ) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.NL) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.DEL_MEM) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.RES_MEM) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.IF_THEN) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.IF_THEN_ELSE) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_medio(this.etq);
				etiqueta(i.getIs1());
				i.setDir_sig(this.etq);
			}else if( i_tipo == Tipo.WHILE) {
				i.setDir_ini(this.etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_sig(this.etq);
			}
		}
	}
	
	public void etiqueta(Exp e) {
		ExpCompuesta ec;
		
		switch(e.tipo()) {
		case NUM:
			this.etq++;
			e.setEtiqueta(this.etq);
			break;
		case REAL:
			this.etq++;
			e.setEtiqueta(this.etq);
			break;
		case BOOL:
			this.etq++;
			e.setEtiqueta(this.etq);
			break;
		case ARRAY:
			this.etq++;
			e.setEtiqueta(this.etq);
			break;
		case ID:
			this.etq++;
			e.setEtiqueta(this.etq);
			break;
		case SUMA:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case SUMAREAL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case RESTA:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			e.setEtiqueta(this.etq);
			this.etq+=2;
			break;
		case MUL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case DIV:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case PORC:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case AND:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case OR:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case NOT:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case NOT2:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case EQUAL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case UNEQUAL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case LESS:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case LESS_EQUAL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			break;
		case GREATER:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		case GREATER_EQUAL:
			ec = (ExpCompuesta) e;
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			this.etq+=2;
			e.setEtiqueta(this.etq);
			break;
		}
		
	}
	
	public void etiqueta_params(Parametros preales, Parametros pforms) {
		for (Parametro preal : preales.getElements()) {
			etiqueta(preal.getE0());
			this.etq +=1;
		}
		for(Parametro pform : pforms.getElements()) {
			etiqueta(pform.getE0());
			this.etq +=1;
		}
	}
	
	//------------------------- MAIN ---------------------------------------
	
	public static void main(String args[]) {
		// Programa A
		// int a
		// int b
		// a = 5
		// b = 2

		// Programa B
		// int a
		// a = 5 + 2

		TinyASint tiny = new TinyASint();

		StringLocalizado int_a = new StringLocalizado("a", 0, 0);
		StringLocalizado int_b = new StringLocalizado("b", 0, 0);
		StringLocalizado valor_a = new StringLocalizado("5", 1, 1);
		StringLocalizado valor_b = new StringLocalizado("2", 1, 1);

		Dec decA = tiny.dec_var(int_a, valor_a, TipoDeclaracion.DEC_VAR);
		Dec decB = tiny.dec_var(int_b, valor_b, TipoDeclaracion.DEC_VAR);
		
		Instruccion_programa asig_a = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID),
				new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM));
		
		Instruccion_programa asig_a2 = tiny.asig(new ExpBasica(int_b, null, Tipo.INT, TipoExpresion.ID),
				new ExpBasica(null, valor_b, Tipo.INT, TipoExpresion.NUM));

		ExpCompuesta suma_b = new ExpCompuesta(new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM),
				new ExpBasica(null, valor_b, Tipo.INT, TipoExpresion.NUM), TipoExpresion.SUMA);
		Instruccion_programa asig_b = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID), suma_b);

		Instrucciones isA = new Instrucciones();
		Decs decsA = new Decs();

		Instrucciones isB = new Instrucciones();

		isA = isA.add(asig_a);
		isA = isA.add(asig_a2);
		decsA = decsA.add(decA);
		decsA = decsA.add(decB);
		Programa pA = new Programa(decsA, isA);

		isB = isB.add(asig_b);
		Programa pB = new Programa(decsA, isB);
	
		ComprobacionTipo ct = new ComprobacionTipo(pA);
		Vinculacion v = new Vinculacion(pA);
		AsignacionEspacios asigna = new AsignacionEspacios(pA);
		Etiquetado etiqueta = new Etiquetado(pA);

		
	}
}
