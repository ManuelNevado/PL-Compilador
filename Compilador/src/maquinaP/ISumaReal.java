package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorReal;

public class ISumaReal implements MaquinaP.Instruccion {
	   /**
	 * 
	 */
	public final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	public ISumaReal(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
		   Valor op2 = this.maquinaP.pilaEvaluacion.pop();
		   Valor op1 = this.maquinaP.pilaEvaluacion.pop();
		   this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorReal(op1.valorReal()+op2.valorReal()));
		   this.maquinaP.pc++;
	   }
	   public String toString() {return "suma_real";};
   }