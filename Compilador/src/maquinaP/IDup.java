package maquinaP;

import maquinaP.MaquinaP.Instruccion;

class IDup implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	IDup(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
           this.maquinaP.pilaEvaluacion.push(this.maquinaP.pilaEvaluacion.peek());
           this.maquinaP.pc++;
       }
       public String toString() {
          return "dup";                 
       }
   }