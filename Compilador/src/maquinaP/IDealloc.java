package maquinaP;

import maquinaP.MaquinaP.Instruccion;

public class IDealloc implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int tam;
      public IDealloc(MaquinaP maquinaP, int tam) {
        this.maquinaP = maquinaP;
		this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = this.maquinaP.pilaEvaluacion.pop().valorInt();
        this.maquinaP.gestorMemoriaDinamica.free(inicio,tam);
        this.maquinaP.pc++;
      } 
      public String toString() {return "dealloc("+tam+")";};
   }