package procesamientos;

import java.util.*;

import asint.*;
import tiny.Programa;

public class Vinculacion {
	// Sin subprogramas
	private HashMap<Object, Object> tabla;
	private Programa programa;
	private int nivel_numerico;

	public Vinculacion(Programa p) {
		this.nivel_numerico = 0;
		this.programa = p;
		this.tabla = new HashMap<Object, Object>();
		try {
			vincula(p.getDecs(), p.getIs(), this.tabla);
			System.out.println("VINCULA OK !!!");
		} catch (Exception e) {
			System.out.println("VINCULA ERROR!!!");
		}

	}

	public void vincula(Decs decs, Instrucciones is, HashMap<Object, Object> nivel) throws Exception {
		vincula_decs1(decs, nivel);
		vincula_decs2(decs, nivel);
		vincula_instrucciones(is, nivel);
	}

	// --------------------------------------------- VINCULA 1
	// ---------------------------------------------------

	private void vincula_decs1(Decs decs, HashMap<Object, Object> nivel) throws Exception {
		for (Dec dec : decs.getElements()) {
			vincula_decs1(dec, nivel);
		}

	}

	private void vincula_decs1(Dec dec, HashMap<Object, Object> nivel) throws Exception {
		switch (dec.tipo()) {
		case DEC_TYPE:
			recolecta(dec.id(), dec, nivel);
			vincula_decs1(dec.getTipoVinculacion(), dec, nivel);
			break;
		case DEC_VAR:
			recolecta(dec.id(), dec, nivel);
			vincula_decs1(dec.getTipoVinculacion(), dec, nivel);
			break;
		case DEC_PFORM:
			vincula_decs1(dec.getPforms(), nivel);
			break;
		case DEC_PROC:
			// abre nivel
			this.nivel_numerico++;
			HashMap<Object, Object> nivel_nuevo = abreNivel(dec.id(), nivel);
			vincula_decs1(dec.getPforms(), nivel_nuevo);
			vincula_decs1(dec.getDecs(), nivel_nuevo);
			vincula_decs2(dec.getPforms(), nivel_nuevo);
			vincula_decs2(dec.getDecs(), nivel_nuevo);
			vincula_instrucciones(dec.getIs(), nivel_nuevo);
			// cierra nivel
			this.nivel_numerico--;
			break;
		}
	}

	private void vincula_decs1(Tipo t, Dec dec, HashMap<Object, Object> nivel) throws Exception {

		switch (t) {
		case INT:
			// skip
			break;
		case REAL:
			// skip
			break;
		case BOOL:
			// skip
			break;
		case REF:
			if (nivel.containsKey(dec.getReferencia())) {
				Dec referencia = (Dec) nivel.get(dec.getReferencia());
				dec.setVal(referencia.getVal());
			} else {
				throw new Exception("ID no declarado");
			}
			break;
		case ARRAY:
			vincula_decs1(dec.getTipoArray(), dec, nivel);
			break;
		case REGISTRO:
			vincula_decs1(dec.getCampos());
			break;
		case PUNTERO:
			if (dec.getTipoVinculacion() != Tipo.REF) {
				vincula_decs1(dec.getTipoPuntero(), dec, nivel);
			}
			break;
		}

	}

	private void vincula_decs1(Parametros pforms, HashMap<Object, Object> nivel) throws Exception {
		for (Parametro pform : pforms.getElements()) {
			Dec dec = new Dec(pform.getId(), null, TipoDeclaracion.DEC_PFORM);
			dec.setTipoVinculacion(pform.getTipo());
			vincula_decs1(pform.getTipo(), dec, nivel);
			recolecta(pform.getId(), dec, nivel);
		}
	}

	private void vincula_decs1(Campos campos) {
		// TODO
	}

	// -------------------------------------------------- VINCULA 2
	// --------------------------------------------------

	private void vincula_decs2(Decs decs, HashMap<Object, Object> nivel) throws Exception {
		for (Dec dec : decs.getElements()) {
			vincula_decs2(dec, nivel);
		}
	}

	private void vincula_decs2(Dec dec, HashMap<Object, Object> nivel) throws Exception {
		switch (dec.tipo()) {
		case DEC_TYPE:
			// ya esta recolectado
			vincula_decs2(dec.getTipoVinculacion(), dec, nivel);
			break;
		case DEC_VAR:
			// ya esta recolectado
			vincula_decs2(dec.getTipoVinculacion(), dec, nivel);
			break;
		case DEC_PROC:
			// SKIP
			break;
		}
	}

	private void vincula_decs2(Tipo t, Dec dec, HashMap<Object, Object> nivel) throws Exception {

		switch (t) {
		case INT:
			// skip
			return;
		case REAL:
			// skip
			return;
		case BOOL:
			// skip
			return;
		case REF:
			// skip
			return;
		case ARRAY:
			vincula_decs2(dec.getTipoArray(), dec, nivel);
		case REGISTRO:
			vincula_decs2(dec.getCampos());
		case PUNTERO:
			if (dec.getTipoPuntero() == Tipo.REF) {
				if (nivel.containsKey(dec.getReferencia())) {
					Dec referencia = (Dec) nivel.get(dec.getReferencia());
					dec.setVal(referencia.getVal());
				} else {
					throw new Exception("ID no declarado");
				}
			} else {
				// Creo que esto se pdoria dejar en un skip pero no estoy seguro
				vincula_decs2(dec.getTipoPuntero(), dec, nivel);
			}
		}

	}

	private void vincula_decs2(Parametros pforms, HashMap<Object, Object> nivel) throws Exception {
		for (Parametro pform : pforms.getElements()) {
			Dec dec = new Dec(pform.getId(), null, TipoDeclaracion.DEC_PFORM);
			dec.setTipoVinculacion(pform.getTipo());
			vincula_decs2(pform.getTipo(), dec, nivel);
			// No se recolecta porque ya esta recolectado
		}
	}

	private void vincula_decs2(Campos campos) {
		// TODO
	}

	// ------------------------------------------------- VINCULACION INSTRUCCIONES
	// -------------------------------------------

	private void vincula_instrucciones(Instrucciones is, HashMap<Object, Object> nivel) throws Exception {
		for (Instruccion_programa i : is.getElements()) {
			vincula_is(i, nivel);
		}
	}

	private void vincula_is(Instruccion_programa i, HashMap<Object, Object> nivel) throws Exception {
		i.setNivel(nivel_numerico);
		switch (i.getTipo()) {
		case ASIG:
			vincula_is(i.getE0(), nivel);
			vincula_is(i.getE1(), nivel);
			break;
		case IF_THEN:
			vincula_is(i.getE0(), nivel);
			vincula_instrucciones(i.getIs0(), nivel);
			break;
		case IF_THEN_ELSE:
			vincula_is(i.getE0(), nivel);
			vincula_instrucciones(i.getIs0(), nivel);
			vincula_instrucciones(i.getIs1(), nivel);
			break;
		case WHILE:
			vincula_is(i.getE0(), nivel);
			vincula_instrucciones(i.getIs0(), nivel);
			break;
		case READ:
			vincula_is(i.getE0(), nivel);
			break;
		case WRITE:
			vincula_is(i.getE0(), nivel);
			break;
		case RES_MEM:
			vincula_is(i.getE0(), nivel);
			break;
		case DEL_MEM:
			vincula_is(i.getE0(), nivel);
			break;
		case CALL:
			vincula_is(i.getE0(), nivel);
			vincula_is(i.getPreal());
			break;
		case COMP:
			// abre nivel
			this.nivel_numerico++;
			vincula_decs1(i.getDecs(), nivel);
			vincula_decs2(i.getDecs(), nivel);
			vincula_instrucciones(i.getIs0(), nivel);
			this.nivel_numerico--;
			break;

		}
	}

	private void vincula_is(Exp e, HashMap<Object, Object> nivel) throws Exception {
		ExpCompuesta exp_compuesta;
		ExpAcc exp_acc;
		ExpDRef exp_dref;
		switch (e.tipo()) {
		case ID:
			if (nivel.containsKey(e.ID())) {
				Dec dec = (Dec) nivel.get(e.ID());
				e.setValor(dec.getVal());
			} else
				throw new Exception("Error en vincula is");
			break;
		case SUMA:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case RESTA:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case AND:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case OR:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case MUL:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case DIV:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case PORC:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case NOT:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case NOT2:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case EQUAL:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case UNEQUAL:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case LESS:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case LESS_EQUAL:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case GREATER:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case GREATER_EQUAL:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case INDEX:
			exp_compuesta = (ExpCompuesta) e;
			vincula_is(exp_compuesta.getE0(), nivel);
			vincula_is(exp_compuesta.getE1(), nivel);
			break;
		case ACC:
			exp_acc = (ExpAcc) e;
			vincula_is(exp_acc.getE0(), nivel);
			break;
		case DREF:
			exp_dref = (ExpDRef) e;
			vincula_is(exp_dref.getE0(), nivel);
			break;

		default:
			return;

		}
	}

	private void vincula_is(Parametros preals) {
		// Creo que esto esta mal, aqui no hay que hacer nada
	}

	// ------------------------------------------------- FUNCIONES DE APOYO
	// ------------------------------------------------

	private void recolecta(StringLocalizado id, Dec dec, HashMap<Object, Object> nivel) throws Exception {

		if (nivel.containsKey(id)) {
			throw new Exception("ID duplicado");
		} else {
			nivel.put(id, dec);
		}
	}

	private HashMap<Object, Object> abreNivel(StringLocalizado id, HashMap<Object, Object> nivel) throws Exception {
		if (nivel.containsKey(id)) {
			throw new Exception("Pocreso con id duplicado");
		} else {
			HashMap<Object, Object> nivel_nuevo = new HashMap<Object, Object>();
			nivel.put(id, nivel_nuevo);
			return nivel_nuevo;
		}
	}

	// ------------------------------------------------ MAIN
	// --------------------------------------------------

	public static void main(String[] args) {
		// Programa A
		// int a
		// a = 5

		// Programa B
		// int a
		// a = 5 + 2

		TinyASint tiny = new TinyASint();

		StringLocalizado int_a = new StringLocalizado("a", 0, 0);
		StringLocalizado valor_a = new StringLocalizado("5", 1, 1);
		StringLocalizado valor_b = new StringLocalizado("2", 1, 1);

		Dec decA = tiny.dec_var(int_a, valor_a, TipoDeclaracion.DEC_VAR);
		Instruccion_programa asig_a = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID),
				new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM));

		ExpCompuesta suma_b = new ExpCompuesta(new ExpBasica(null, valor_a, Tipo.INT, TipoExpresion.NUM),
				new ExpBasica(null, valor_b, Tipo.INT, TipoExpresion.NUM), TipoExpresion.SUMA);
		Instruccion_programa asig_b = tiny.asig(new ExpBasica(int_a, null, Tipo.INT, TipoExpresion.ID), suma_b);

		Instrucciones isA = new Instrucciones();
		Decs decsA = new Decs();

		Instrucciones isB = new Instrucciones();

		isA = isA.add(asig_a);
		decsA = decsA.add(decA);
		Programa pA = new Programa(decsA, isA);

		isB = isB.add(asig_b);
		Programa pB = new Programa(decsA, isB);

		ComprobacionTipo ct = new ComprobacionTipo(pA);
		Vinculacion vincula = new Vinculacion(pA);

	}

}
