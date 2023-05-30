package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorBool;

public class IApilaBool implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private boolean valor;
      public IApilaBool(MaquinaP maquinaP, boolean valor) {
        this.maquinaP = maquinaP;
		this.valor = valor;  
      }
      public void ejecuta() {
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(valor)); 
         this.maquinaP.pc++;
      } 
      public String toString() {return "apilaBool("+valor+")";};
   }