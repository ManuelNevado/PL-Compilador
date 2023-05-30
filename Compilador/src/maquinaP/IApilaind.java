package maquinaP;

import maquinaP.MaquinaP.EAccesoAMemoriaNoInicializada;
import maquinaP.MaquinaP.EAccesoFueraDeRango;
import maquinaP.MaquinaP.Instruccion;

public class IApilaind implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	public IApilaind(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
        int dir = this.maquinaP.pilaEvaluacion.pop().valorInt();
        if (dir >= this.maquinaP.datos.length) throw new MaquinaP.EAccesoFueraDeRango();
        if (this.maquinaP.datos[dir] == null)  throw new MaquinaP.EAccesoAMemoriaNoInicializada(this.maquinaP.pc,dir);
        this.maquinaP.pilaEvaluacion.push(this.maquinaP.datos[dir]);
        this.maquinaP.pc++;
      } 
      public String toString() {return "apilaind";};
   }