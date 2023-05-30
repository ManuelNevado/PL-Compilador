package maquinaP;

import maquinaP.MaquinaP.Instruccion;

class IIrind implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	IIrind(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
          this.maquinaP.pc = this.maquinaP.pilaEvaluacion.pop().valorInt();  
       }
       public String toString() {
          return "irind";                 
       }
   }