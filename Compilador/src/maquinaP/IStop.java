package maquinaP;

import maquinaP.MaquinaP.Instruccion;

class IStop implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	IStop(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
           this.maquinaP.pc = this.maquinaP.codigoP.size();
       }
       public String toString() {
          return "stop";                 
       }
   }