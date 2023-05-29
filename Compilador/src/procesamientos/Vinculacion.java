package procesamientos;

import java.util.*;

import asint.*;
import tiny.Programa;


public class Vinculacion {
	//Sin subprogramas
	private HashMap<Object, Object> tabla;
	private Programa programa;
	
	public Vinculacion(Programa p) throws Exception {
		this.programa =p;
		this.tabla = new HashMap<Object,Object>();
		vincula(p.getDecs(), p.getIs(), this.tabla);
	}
	
	
	public void vincula(Decs decs, Instrucciones is, HashMap<Object,Object> nivel) throws Exception {
		vincula_decs1(decs, nivel);
		vincula_decs2(decs, nivel);
		vincula_instrucciones(is);
	}
	
	
	//--------------------------------------------- VINCULA 1 ---------------------------------------------------
	
	
	private void vincula_decs1(Decs decs, HashMap<Object, Object> nivel) throws Exception {
		for(Dec dec : decs.getElements()) {
			vincula_decs1(dec, nivel);
		}
		
	}
	
	private void vincula_decs1(Dec dec, HashMap<Object,Object> nivel) throws Exception {
		switch (dec.tipo()) {
			case DEC_TYPE:
				recolecta(dec.id(),dec, nivel);
				vincula_decs1(dec.getTipoVinculacion(), dec, nivel);
			case DEC_VAR:
				recolecta(dec.id(),dec, nivel);
				vincula_decs1(dec.getTipoVinculacion(), dec, nivel);
			case DEC_PFORM:
				vincula_decs1(dec.getPforms(), nivel);
			case DEC_PROC:
				//abre nivel
				HashMap<Object, Object> nivel_nuevo = abreNivel(dec.id(), nivel);
				vincula_decs1(dec.getPforms(), nivel_nuevo);
				vincula_decs1(dec.getDecs(), nivel_nuevo);
				vincula_decs2(dec.getPforms(), nivel_nuevo);
				vincula_decs2(dec.getDecs(), nivel_nuevo);
				vincula_instrucciones(dec.getIs());
				//cierra nivel
		}
	}
	
	private void vincula_decs1(Tipo t, Dec dec, HashMap<Object,Object> nivel) throws Exception{
		
		switch (t) {
			case INT:
				//skip
				return;
			case REAL:
				//skip
				return;
			case BOOL:
				//skip
				return;
			case REF:
				if (nivel.containsKey(dec.getReferencia())) {
					Dec referencia = (Dec) nivel.get(dec.getReferencia());
					dec.setVal(referencia.getVal());
				}
				else {
					throw new Exception("ID no declarado");
				}
			case ARRAY:
				vincula_decs1(dec.getTipoArray(), dec, nivel);
			case REGISTRO:
				vincula_decs1(dec.getCampos());
			case PUNTERO:
				if(dec.getTipoVinculacion() != Tipo.REF) {
					vincula_decs1(dec.getTipoPuntero(),dec,nivel);
				}
		}

	}
	
	private void vincula_decs1(Parametros pforms, HashMap<Object,Object> nivel) throws Exception {
		for(Parametro pform : pforms.getElements()) {
			Dec dec = new Dec(pform.getId(), null, TipoDeclaracion.DEC_PFORM);
			dec.setTipoVinculacion(pform.getTipo());
			vincula_decs1(pform.getTipo(), dec, nivel);
			recolecta(pform.getId(), dec ,nivel);
		}
	}
	
	private void vincula_decs1(Campos campos){
		//TODO
	}
	
	//--------------------------------------------------  VINCULA 2 --------------------------------------------------
	
	private void vincula_decs2(Decs decs,HashMap<Object,Object> nivel) throws Exception{
		for (Dec dec : decs.getElements()) {
			vincula_decs2(dec, nivel);
		}
	}
	
	private void vincula_decs2(Dec dec, HashMap<Object,Object> nivel) throws Exception{
		switch (dec.tipo()) {
			case DEC_TYPE:
				//ya esta recolectado
				vincula_decs2(dec.getTipoVinculacion(),dec, nivel);
				return;			
			case DEC_VAR:
				//ya esta recolectado
				vincula_decs2(dec.getTipoVinculacion(),dec,nivel);
			case DEC_PFORM:
				vincula_decs2(dec.getPforms(),nivel);
			case DEC_PROC:
				//SKIP
				return;
		}
	}
	
private void vincula_decs2(Tipo t, Dec dec, HashMap<Object,Object> nivel) throws Exception{
		
		switch (t) {
			case INT:
				//skip
				return;
			case REAL:
				//skip
				return;
			case BOOL:
				//skip
				return;
			case REF:
				//skip
				return;
			case ARRAY:
				vincula_decs2(dec.getTipoArray(),dec, nivel);
			case REGISTRO:
				vincula_decs2(dec.getCampos());
			case PUNTERO:
				if(dec.getTipoPuntero() == Tipo.REF) {
					if (nivel.containsKey(dec.getReferencia())) {
						Dec referencia = (Dec) nivel.get(dec.getReferencia());
						dec.setVal(referencia.getVal());
					}
					else {
						throw new Exception("ID no declarado");
					}
				}else {
					//Creo que esto se pdoria dejar en un skip pero no estoy seguro
					vincula_decs2(dec.getTipoPuntero(),dec,nivel);
				}
		}

	}


	private void vincula_decs2(Parametros pforms, HashMap<Object,Object> nivel) throws Exception {
		for(Parametro pform : pforms.getElements()) {
			Dec dec = new Dec(pform.getId(), null, TipoDeclaracion.DEC_PFORM);
			dec.setTipoVinculacion(pform.getTipo());
			vincula_decs2(pform.getTipo(), dec, nivel);
			//No se recolecta porque ya esta recolectado
		}
	}
	
	private void vincula_decs2(Campos campos) {
		//TODO
	}
	
	//------------------------------------------------- VINCULACION INSTRUCCIONES -------------------------------------------
	
	
	private void vincula_instrucciones(Instrucciones is) throws Exception {
		for(Instruccion i : is.getElements()) {
			vincula_is(i);
		}
	}
	
	private void vincula_is(Instruccion i) throws Exception {
		switch(i.getTipo()) {
			case ASIG:
				vincula_is(i.getE0());
				vincula_is(i.getE1());
			case IF_THEN:
				vincula_is(i.getE0());
				vincula_instrucciones(i.getIs0());
			case IF_THEN_ELSE:
				vincula_is(i.getE0());
				vincula_instrucciones(i.getIs0());
				vincula_instrucciones(i.getIs1());
			case WHILE:
				vincula_is(i.getE0());
				vincula_instrucciones(i.getIs0());
			case READ:
				vincula_is(i.getE0());
			case WRITE:
				vincula_is(i.getE0());
			case RES_MEM:
				vincula_is(i.getE0());
			case DEL_MEM:
				vincula_is(i.getE0());
			case CALL:
				vincula_is(i.getE0());
				vincula_is(i.getPreal());
			case COMP:
				//?????
		}
	}
	
	private void vincula_is(Exp e) throws Exception {
		ExpCompuesta exp_compuesta;
		ExpAcc exp_acc;
		ExpDRef exp_dref;
		switch(e.tipo()) {
			case ID:
				if(this.tabla.containsKey(e.ID())) {
					Dec dec = (Dec) this.tabla.get(e.ID());
					e.setValor(dec.getVal());
				}else
					throw new Exception("Error en vincula is");
			case SUMA:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case RESTA:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case AND:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case OR:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case MUL:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case DIV:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case PORC:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case NOT:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case NOT2:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case EQUAL:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case UNEQUAL:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case LESS:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case LESS_EQUAL:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case GREATER:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case GREATER_EQUAL:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case INDEX:
				exp_compuesta = (ExpCompuesta)e;
				vincula_is(exp_compuesta.getE0());
				vincula_is(exp_compuesta.getE1());
			case ACC:
				exp_acc = (ExpAcc)e;
				vincula_is(exp_acc.getE0());
			case DREF:
				exp_dref = (ExpDRef)e;
				vincula_is(exp_dref.getE0());
				
		default:
			return;
				
		}
	}
	
	private void vincula_is(Parametros preals) {
		//Creo que esto esta mal, aqui no hay que hacer nada
	}
	
	
	
	//------------------------------------------------- FUNCIONES DE APOYO ------------------------------------------------
	
	
	private void recolecta(StringLocalizado id, Dec dec, HashMap<Object,Object> nivel) throws Exception {
		
		if(nivel.containsKey(id)) {
			throw new Exception("ID duplicado");
		}else {
			nivel.put(id, dec);
		}
	}
	
	private HashMap<Object,Object> abreNivel(StringLocalizado id, HashMap<Object,Object> nivel) throws Exception {
		if(nivel.containsKey(id)) {
			throw new Exception("Pocreso con id duplicado");
		}else {
			HashMap<Object,Object> nivel_nuevo = new HashMap<Object,Object>();
			nivel.put(id, nivel_nuevo);
			return nivel_nuevo;
		}
	}
	
	
	//------------------------------------------------ MAIN --------------------------------------------------
	
	
	public static void main(String[] args) {
		
		Programa programa = new Programa();
		programa.add(new Dec(null,null,null));
		Vinculacion vincula;
		try {
			vincula = new Vinculacion(programa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
