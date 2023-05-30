package maquinaP;

import maquinaP.MaquinaP.Instruccion;
import maquinaP.MaquinaP.ValorInt;

public class IActiva implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int nivel;
       private int tamdatos;
       private int dirretorno;
       public IActiva(MaquinaP maquinaP, int nivel, int tamdatos, int dirretorno) {
           this.maquinaP = maquinaP;
		this.nivel = nivel;
           this.tamdatos = tamdatos;
           this.dirretorno = dirretorno;
       }
       public void ejecuta() {
          int base = this.maquinaP.gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
          this.maquinaP.datos[base] = this.maquinaP.new ValorInt(dirretorno);
          this.maquinaP.datos[base+1] = this.maquinaP.new ValorInt(this.maquinaP.gestorPilaActivaciones.display(nivel));
          this.maquinaP.pilaEvaluacion.push(this.maquinaP.new ValorInt(base+2));
          this.maquinaP.pc++;
       }
       public String toString() {
          return "activa("+nivel+","+tamdatos+","+dirretorno+")";                 
       }
   }