package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorInt;

public class IApilaInt implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int valor;
      public IApilaInt(MaquinaP maquinaP, int valor) {
        this.maquinaP = maquinaP;
		this.valor = valor;  
      }
      public void ejecuta() {
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorInt(valor)); 
         this.maquinaP.pc++;
      } 
      public String toString() {return "apilaInt("+valor+")";};
   }