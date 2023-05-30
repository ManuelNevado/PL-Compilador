package tiny;


import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.io.Reader;

import asint.Dec;
import asint.Decs;
import asint.ExpBasica;
import asint.ExpCompuesta;
import asint.Instruccion_programa;
import asint.Instrucciones;
import asint.StringLocalizado;
import asint.TinyASint;
import asint.Tipo;
import asint.TipoDeclaracion;
import asint.TipoExpresion;
import procesamientos.AsignacionEspacios;
import procesamientos.ComprobacionTipo;
import procesamientos.Etiquetado;
import procesamientos.GeneraCodigo;
import procesamientos.Vinculacion;

public class Main {
   public static void main(String[] args) throws Exception {
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
			Decs decsB = new Decs();

			isA = isA.add(asig_a);
			isA = isA.add(asig_a2);
			decsA = decsA.add(decA);
			decsA = decsA.add(decB);
			Programa pA = new Programa(decsA, isA);

			isB = isB.add(asig_b);
			decsB = decsB.add(decA);
			Programa pB = new Programa(decsB, isB);
			
			ComprobacionTipo ct = new ComprobacionTipo(pB);
			Vinculacion v = new Vinculacion(pB);
			AsignacionEspacios asigna = new AsignacionEspacios(pB);
			Etiquetado etiqueta = new Etiquetado(pB);
			GeneraCodigo genCod = new GeneraCodigo(pB);

			       
     }
 
} 
  
