package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorInt;

public class IAlloc implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int tam;
      public IAlloc(MaquinaP maquinaP, int tam) {
        this.maquinaP = maquinaP;
		this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = this.maquinaP.gestorMemoriaDinamica.alloc(tam);
        this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorInt(inicio));
        this.maquinaP.pc++;
      } 
      public String toString() {return "alloc("+tam+")";};
   }