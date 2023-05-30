package maquinaP;

import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorInt;

public class IDiv implements MaquinaP.Instruccion {
	  /**
		 * 
		 */
		private final MaquinaP maquinaP;
		/**
		 * @param maquinaP
		 */
		public IDiv(MaquinaP maquinaP) {
			this.maquinaP = maquinaP;
		}
		public void ejecuta() {
	         Valor opnd2 = this.maquinaP.pilaEvaluacion.pop(); 
	         Valor opnd1 = this.maquinaP.pilaEvaluacion.pop();
	         this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorInt((int)(opnd1.valorInt()/opnd2.valorInt())));
	         this.maquinaP.pc++;
	      } 
	      public String toString() {return "mul";};

}
