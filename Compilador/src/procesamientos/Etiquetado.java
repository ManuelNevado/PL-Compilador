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
			etq++;
			etiqueta_params(dec.getPforms(), dec.getPreales());
			etq+=2;
			dec.setDir_sig(etq);
		}else if(dec.tipo() == TipoDeclaracion.DEC_PROC) {
			dec.setDir_ini(etq);
			etiqueta(dec.getIs());
			etq+=2;
			dec.setDir_sig(etq);
			pila.recolecta_procs(dec.getDecs());
			while(!pila.getPila().empty()) {
				etiqueta(pila.getPila().pop());
			}
		}else if(dec.tipo() == TipoDeclaracion.DEC_VAR) {
			etiqueta(dec.getIs());
		}
	}
	public void etiqueta(Instrucciones Is) {
		for (Instruccion_programa i: Is.getElements()) {
			Tipo i_tipo = i.getTipo();
			if(i_tipo == Tipo.ASIG) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				etiqueta(i.getE1());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.WRITE) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.READ) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.NL) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.DEL_MEM) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.RES_MEM) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.IF_THEN) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.IF_THEN_ELSE) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_medio(etq);
				etiqueta(i.getIs1());
				i.setDir_sig(etq);
			}else if( i_tipo == Tipo.WHILE) {
				i.setDir_ini(etq);
				etiqueta(i.getE0());
				etiqueta(i.getIs0());
				i.setDir_sig(etq);
			}
		}
	}
	
	public void etiqueta(Exp e) {
		ExpCompuesta ec = (ExpCompuesta) e;
		
		switch(e.tipo()) {
		case NUM:
			this.etq++;
			e.setEtiqueta(etq);
			break;
		case REAL:
			this.etq++;
			e.setEtiqueta(etq);
			break;
		case BOOL:
			this.etq++;
			e.setEtiqueta(etq);
			break;
		case ARRAY:
			this.etq++;
			e.setEtiqueta(etq);
			break;
		case ID:
			etq++;
			e.setEtiqueta(etq);
			break;
		case SUMA:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case SUMAREAL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case RESTA:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			e.setEtiqueta(etq);
			etq+=2;
			break;
		case MUL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case DIV:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case PORC:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case AND:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case OR:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case NOT:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case NOT2:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case EQUAL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case UNEQUAL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case LESS:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case LESS_EQUAL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			break;
		case GREATER:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		case GREATER_EQUAL:
			etiqueta(ec.getE0());
			etiqueta(ec.getE1());
			etq+=2;
			e.setEtiqueta(etq);
			break;
		}
		
	}
	
	public void etiqueta_params(Parametros preales, Parametros pforms) {
		//TODO corregir en memoria
	}
	
	//------------------------- MAIN ---------------------------------------
	
	public static void main(String args[]) {
		//Etiqueta el siguiente programa
		/*
		 * int a = 1
		 * int b = 2
		 * int c = a+b
		 */
		StringLocalizado a = new StringLocalizado("a",0,0);
		StringLocalizado val_a = new StringLocalizado("0",0,1);
		
		Instruccion_programa asigna = new Instruccion_programa(Tipo.ASIG);
		
		ExpAcc e0 = new ExpAcc();
		e0.setE0(e0);
		e0.setId(a);
		e0.setTipo(TipoExpresion.NUM);
		asigna.setE0(e0);
		
		Programa p = new Programa();
		
		Dec declaracion_a = new Dec(a, val_a, TipoDeclaracion.DEC_VAR);
		declaracion_a.setInstrucciones(declaracion_a.getIs().add(asigna));
		p.add(declaracion_a);
		
		Etiquetado etiqueta = new Etiquetado(p);
		System.out.println("DONE!");
	}
}
