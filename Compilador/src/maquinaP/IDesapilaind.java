package maquinaP;

import maquinaP.MaquinaP.EAccesoFueraDeRango;
import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.Valor;

public class IDesapilaind implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private  final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	public IDesapilaind(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
        Valor valor = this.maquinaP.pilaEvaluacion.pop();
        int dir = this.maquinaP.pilaEvaluacion.pop().valorInt();
        if (dir >= this.maquinaP.datos.length) throw new MaquinaP.EAccesoFueraDeRango();
        this.maquinaP.datos[dir] = valor;
        this.maquinaP.pc++;
      } 
      public String toString() {return "desapilaind";};
   }