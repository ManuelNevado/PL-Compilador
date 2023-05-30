package procesamientos;

import java.util.*;
import asint.*;

public class RecolectaProcs {
	private Stack<Dec> pila;
	
	public RecolectaProcs() {
		this.pila = new Stack<Dec>();
	}
	
	public void recolecta_procs(Decs decs) {
		for(Dec d: decs.getElements()) {
			if(d.tipo() == TipoDeclaracion.DEC_PROC) {
				pila.push(d);
			}
		}
	}
	
	public Stack<Dec> getPila() {return pila;}
	public void setPila(Stack<Dec> pila) {this.pila = pila;}
}
