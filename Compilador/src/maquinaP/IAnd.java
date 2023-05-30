package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorBool;

public class IAnd implements MaquinaP.Instruccion {
      /**
	 * 
	 */
	private final MaquinaP maquinaP;
	/**
	 * @param maquinaP
	 */
	public IAnd(MaquinaP maquinaP) {
		this.maquinaP = maquinaP;
	}
	public void ejecuta() {
         Valor opnd2 = this.maquinaP.pilaEvaluacion.pop(); 
         Valor opnd1 = this.maquinaP.pilaEvaluacion.pop();
         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorBool()&&opnd2.valorBool()));
         this.maquinaP.pc++;
      } 
      public String toString() {return "and";};
   }