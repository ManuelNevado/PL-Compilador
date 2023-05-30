package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorReal;

public class IMulReal implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	public IMulReal(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
         Valor opnd2 = this.maquinaP.pilaEvaluacion.pop(); 
         Valor opnd1 = this.maquinaP.pilaEvaluacion.pop();
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorReal(opnd1.valorReal()*opnd2.valorReal()));
         this.maquinaP.pc++;
      } 
      public String toString() {return "mul_real";};
   }