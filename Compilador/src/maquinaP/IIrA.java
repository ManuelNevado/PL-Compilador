package maquinaP;

import maquinaP.MaquinaP.Instruccion;

public class IIrA implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int dir;
      public IIrA(MaquinaP maquinaP, int dir) {
        this.maquinaP = maquinaP;
		this.dir = dir;  
      }
      public void ejecuta() {
            this.maquinaP.pc=dir;
      } 
      public String toString() {return "ira("+dir+")";};
   }