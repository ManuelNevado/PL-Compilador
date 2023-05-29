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
	
	private void vincula_decs1(Decs decs, HashMap<Object, Object> nivel) throws Exception {
		for(Dec dec : decs.getElements()) {
			vincula_decs1(dec, nivel);
		}
		
	}
	
	private void vincula_decs2(Decs decs,HashMap<Object,Object> nivel) {
		
	}
	
	private void vincula_instrucciones(Instrucciones is) {
		
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
	
	private void vincula_decs1(Parametros pforms, HashMap<Object,Object> nivel) throws Exception {
		for(Parametro pform : pforms.getElements()) {
			Dec dec = new Dec(pform.getId(), null, TipoDeclaracion.DEC_PFORM);
			dec.setTipoVinculacion(pform.getTipo());
			vincula_decs1(pform.getTipo(), dec, nivel);
			recolecta(pform.getId(), dec ,nivel);
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
			case CAMPO:
			case PUNTERO: 
		}

	}
	
	private void vincula_decs1(Campos campos){
		//TODO
	}
	
	private void vincula_decs2(Parametros pform, HashMap<Object,Object> nivel) {
		
	}
	
	
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
	
}
