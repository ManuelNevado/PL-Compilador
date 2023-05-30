package tiny;
import java.util.*;
import asint.*;

public class Programa {
	private Decs decs;
	private Instrucciones is;
	
	public Programa() {
		this.decs = new Decs();
		this.is = new Instrucciones();
		
	}
	
	public Decs getDecs() {return decs;}
	public void add(Dec d) {this.decs.add(d);}
	public Instrucciones getIs() {return this.is;}
	public void add(Instruccion_programa i) {this.is.add(i);}
	
}
