package procesamientos;

import asint.*;
import tiny.*;

public class AsignacionEspacios {
	private int dir;
	private int nivel;
	private Programa programa;

	public AsignacionEspacios(Programa p) {
		this.dir = 0;
		this.nivel = 0;
		this.programa = p;
		Asigna(programa.getDecs());
		Asigna(programa.getIs());
		
		System.out.println("ASIGNA OK !!!!");
	}

	public void Asigna(Decs decs) {
		for (Dec dec : decs.getElements()) {
			switch (dec.tipo()) {
			case DEC_TYPE:
				Asigna(dec.getTipoVinculacion(), dec);
				break;
			case DEC_PROC:
				int ant_dir = this.dir;
				nivel++;
				this.dir = 0;
				Asigna(dec.getPforms());
				Asigna(dec.getDecs());
				dec.setTam_datos(this.dir);
				this.dir = ant_dir;
				nivel--;
				break;
			case DEC_VAR:
				Asigna(dec.getTipoVinculacion(), dec);
				dec.setDir(this.dir);
				dec.setNivel(nivel);
				this.dir = this.dir + tamTipo(dec.getTipoVinculacion());// se incrementa la dirección en el tamaño requerido por T

			}
		}
	}

	public void Asigna(Instrucciones Is) {
		for (Instruccion_programa i : Is.getElements()) {
			switch (i.getTipo()) {
			case IF_THEN:
				Asigna(i.getIs0());
			case IF_THEN_ELSE:
				Asigna(i.getIs0());
				Asigna(i.getIs1());
			case WHILE:
				Asigna(i.getIs0());
			case CALL:
				Asigna(i.getPreal());
			case COMP:
				int ant_dir = dir;
				Asigna(i.getDecs());
				Asigna(i.getIs0());
				dir = ant_dir;
			}
		}
	}

	public void Asigna(Tipo t, Dec dec) {
		switch (t) {
		case INT:
			dec.setTam_datos(1);
			break;
		case REF:
			dec.setTam_datos(tamTipo(dec.getTipoVinculacion()));
		case BOOL:
			dec.setTam_datos(1);
		case ARRAY:
			dec.setTam_datos(1);
			break;
		case REGISTRO:
			dec.setNivel(nivel);
			dec.setTam_datos(Asigna(dec.getCampos()));
			break;
		case PUNTERO:
			dec.setTam_datos(1);
			break;
		}
	}

	public void Asigna(Tipo t, Parametro pform) {
		switch (t) {
		case INT:
			pform.setTam_datos(1);
			break;
		case REF:
			pform.setTam_datos(tamTipo(pform.getTipo()));
		case BOOL:
			pform.setTam_datos(1);
		case ARRAY:
			pform.setTam_datos(1);
			break;
		case REGISTRO:
			Asigna(pform.getCampo());
			break;
		case PUNTERO:
			pform.setTam_datos(1);
			break;
		}
	}

	public void Asigna(Parametros pforms) {
		for (Parametro pform : pforms.getElements()) {
			switch (pform.getTparametro()) {
			case P_VAL:
				pform.setDir(dir);
				pform.setNivel(nivel);
				Asigna(pform.getTipo(), pform);
				this.dir += tamTipo(pform.getTipo());
				break;
			case P_VAR:
				pform.setDir(dir);
				pform.setNivel(nivel);
				Asigna(pform.getTipo(), pform);
				this.dir++;
				break;
			}

		}
	}

	public int Asigna(Campos cs) {
		int espacio_total = 0;
		for (Campo c : cs.getLista()) {
			espacio_total += tamTipo(c.getTipo());
		}
		return espacio_total;
	}

	public int tamTipo(Tipo t) {
		if (t == Tipo.BOOL || t == Tipo.INT || t == Tipo.PUNTERO) {
			return 1;
		} else if (t == Tipo.REAL) {
			return 2;
		}
		return 1;
	}

	// -------------------------------------------------- MAIN -----------------------------------------------
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

	}

}
