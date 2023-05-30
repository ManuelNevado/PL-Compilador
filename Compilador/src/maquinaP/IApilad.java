package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorInt;

public class IApilad implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int nivel;
       public IApilad(MaquinaP maquinaP, int nivel) {
         this.maquinaP = maquinaP;
		this.nivel = nivel;  
       }
       public void ejecuta() {
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorInt(this.maquinaP.gestorPilaActivaciones.display(nivel)));
         this.maquinaP.pc++;
       }
       public String toString() {
          return "apilad("+nivel+")";                 
       }

   }