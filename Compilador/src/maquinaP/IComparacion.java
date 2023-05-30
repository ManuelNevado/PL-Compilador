package maquinaP;

import asint.*;
import maquinaP.MaquinaP.Valor;
import maquinaP.MaquinaP.ValorBool;

public class IComparacion implements MaquinaP.Instruccion{
	 
		private Tipo tipo;
		private TipoExpresion op;
		private final MaquinaP maquinaP;
		/**
		 * @param maquinaP
		 */
		public IComparacion(MaquinaP maquinaP, Tipo t, TipoExpresion op) {
			this.maquinaP = maquinaP;
			this.tipo = t;
			this.op = op;
		}
		public void ejecuta() {
			
	         Valor opnd2 = this.maquinaP.pilaEvaluacion.pop(); 
	         Valor opnd1 = this.maquinaP.pilaEvaluacion.pop();
	         
	         if(op == TipoExpresion.GREATER) {
	        	 if(this.tipo == Tipo.INT) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorInt() > opnd2.valorInt()));
		         }else if(this.tipo == Tipo.REAL) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorReal() > opnd2.valorReal()));
		         }
	         }else if(op == TipoExpresion.GREATER_EQUAL) {
	        	 if(this.tipo == Tipo.INT) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorInt() >= opnd2.valorInt()));
		         }else if(this.tipo == Tipo.REAL) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorReal() >= opnd2.valorReal()));
		         }
	         }else if(op == TipoExpresion.LESS) {
	        	 if(this.tipo == Tipo.INT) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorInt() < opnd2.valorInt()));
		         }else if(this.tipo == Tipo.REAL) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
		         }
	         }else if(op == TipoExpresion.LESS_EQUAL) {
	        	 if(this.tipo == Tipo.INT) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorInt() <= opnd2.valorInt()));
		         }else if(this.tipo == Tipo.REAL) {
		        	 this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
		         }
	         }else if(op == TipoExpresion.NOT) {
	        	 
	        	 
	         }else if(op == TipoExpresion.NOT2) {
	        	 
	         }

	         this.maquinaP.pc++;
	         
		}
}
