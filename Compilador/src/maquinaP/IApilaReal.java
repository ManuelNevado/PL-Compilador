package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorReal;

public class IApilaReal implements MaquinaP.Instruccion {
      /**
 * 
 */
private final MaquinaP maquinaP;
	private double valor;
      public IApilaReal(MaquinaP maquinaP, double valor) {
        this.maquinaP = maquinaP;
		this.valor = valor;  
      }
      public void ejecuta() {
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorReal(valor)); 
         this.maquinaP.pc++;
      } 
      public String toString() {return "apilaReal("+valor+")";};
   }