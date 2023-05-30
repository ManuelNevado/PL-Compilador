package maquinaP;

import maquinaP.MaquinaP.Instruccion;

public class IIrF implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int dir;
      public IIrF(MaquinaP maquinaP, int dir) {
        this.maquinaP = maquinaP;
		this.dir = dir;  
      }
      public void ejecuta() {
         if(! this.maquinaP.pilaEvaluacion.pop().valorBool()) { 
            this.maquinaP.pc=dir;
         }   
         else {
            this.maquinaP.pc++; 
         }
      } 
      public String toString() {return "irf("+dir+")";};
   }