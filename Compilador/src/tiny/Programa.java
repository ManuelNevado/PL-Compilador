package tiny;
import java.util.*;
import asint.*;

public class Programa {
	private Decs decs;
	private Instrucciones is;
	
	public Programa(Decs decs, Instrucciones is) {
		this.decs = decs;
		this.is = is;
		
	}
	
	public Decs getDecs() {return decs;}
	public void add(Dec d) {this.decs.add(d);}
	public Instrucciones getIs() {return this.is;}
	public void add(Instruccion_programa i) {this.is.add(i);}
	
}
