package tiny;
import java.util.*;
import asint.*;

public class Programa {
	private List<Decs> Declaraciones;
	private List<Exp> Expresiones;
	
	public Programa() {
		this.Declaraciones = new ArrayList<Decs>();
		this.Expresiones = new ArrayList<Exp>();
		
		this.Declaraciones.add(new Decs_una(
				new Dec(
						new StringLocalizado("x",0,0), 
						new StringLocalizado("5",0,1) ,
						TipoDeclaracion.INT
						)
				)
		);
		this.Declaraciones.add(new Decs_una(
				new Dec(
						new StringLocalizado("y",0,1), 
						new StringLocalizado("7",0,2) ,
						TipoDeclaracion.INT
						)
				)
		);
		
		
		this.Expresiones.add(new Suma(
				new Num(new StringLocalizado("x",0,0)), 
				new Num(new StringLocalizado("y",0,1))
				));
		
		//Descomentar para ver la gestion de errores
		
//		this.Declaraciones.add(new Decs_una(
//				new Dec(
//						new StringLocalizado("y",0,0), 
//						new StringLocalizado("True",0,1) ,
//						TipoDeclaracion.BOOL
//						)
//				)
//		);
	}
	
	public List<Decs> getDeclaraciones() {return Declaraciones;}
	public void setDeclaraciones(List<Decs> declaraciones) {Declaraciones = declaraciones;}
	public List<Exp> getExpresiones() {return Expresiones;}
	public void setExpresiones(List<Exp> expresiones) {Expresiones = expresiones;}
	
}
