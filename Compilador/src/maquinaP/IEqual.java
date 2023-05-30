package maquinaP;

import asint.Tipo;
import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorBool;

public class IEqual implements MaquinaP.Instruccion{
	 /**
		 * 
		 */
		private Tipo tipo;
		private final MaquinaP maquinaP;
		/**
		 * @param maquinaP
		 */
		public IEqual(MaquinaP maquinaP, Tipo t) {
			this.maquinaP = maquinaP;
		}
		public void ejecuta() {
			
	         Valor opnd2 = this.maquinaP.pilaEvaluacion.pop(); 
	         Valor opnd1 = this.maquinaP.pilaEvaluacion.pop();
	         
	         if(this.tipo == Tipo.BOOL) {
	        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorBool() == opnd2.valorBool()));
	         }else if(this.tipo == Tipo.INT) {
	        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorInt() == opnd2.valorInt()));
	         }else if(this.tipo == Tipo.REAL) {
	        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorReal() == opnd2.valorReal()));
	         }
	         
	         this.maquinaP.pc++;
	      } 
	      public String toString() {return "eq";};
}
