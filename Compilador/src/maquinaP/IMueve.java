package maquinaP;

import maquinaP.MaquinaP.EAccesoFueraDeRango;
import maquinaP.MaquinaP.Instruccion;

class IMueve implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int tam;
      public IMueve(MaquinaP maquinaP, int tam) {
        this.maquinaP = maquinaP;
		this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = this.maquinaP.pilaEvaluacion.pop().valorInt();
            int dirDestino = this.maquinaP.pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= this.maquinaP.datos.length)
                throw new MaquinaP.EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= this.maquinaP.datos.length)
                throw new MaquinaP.EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                this.maquinaP.datos[dirDestino+i] = this.maquinaP.datos[dirOrigen+i]; 
            this.maquinaP.pc++;
      } 
      public String toString() {return "mueve("+tam+")";};
   }