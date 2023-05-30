package maquinaP;

import maquinaP.MaquinaP.Instruccion;

class IDesactiva implements MaquinaP.Instruccion {
       /**
	 * 
	 */
	private final MaquinaP maquinaP;
	private int nivel;
       private int tamdatos;
       public IDesactiva(MaquinaP maquinaP, int nivel, int tamdatos) {
           this.maquinaP = maquinaP;
		this.nivel = nivel;
           this.tamdatos = tamdatos;
       }
       public void ejecuta() {
          int base = this.maquinaP.gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
          this.maquinaP.gestorPilaActivaciones.fijaDisplay(nivel,this.maquinaP.datos[base+1].valorInt());
          this.maquinaP.pilaEvaluacion.push(this.maquinaP.datos[base]); 
          this.maquinaP.pc++;
       }
       public String toString() {
          return "desactiva("+nivel+","+tamdatos+")";                 
       }

   }