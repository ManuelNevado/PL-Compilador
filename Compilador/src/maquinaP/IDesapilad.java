package maquinaP;

import maquinaP.MaquinaP.Instruccion;

class IDesapilad implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int nivel;
       public IDesapilad(MaquinaP maquinaP, int nivel) {
         this.maquinaP = maquinaP;
		this.nivel = nivel;  
       }
       public void ejecuta() {
         this.maquinaP.gestorPilaActivaciones.fijaDisplay(nivel,this.maquinaP.pilaEvaluacion.pop().valorInt());  
         this.maquinaP.pc++;
       }
       public String toString() {
          return "setd("+nivel+")";                 
       }
   }