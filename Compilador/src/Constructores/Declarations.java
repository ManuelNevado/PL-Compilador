package Constructores;

public class Declarations {
	
	public enum TipoDeclaracion{
		VAR, TIPO
	}
	
	public abstract static class Dec{
		private StringAlocated id;
		
		public abstract TipoDeclaracion tipo();
		
		public Dec(StringAlocated s) {this.id=s;}
		
		public StringAlocated id() {return this.id;}
		
		public abstract void procesa(Procesamiento P);
	}
	
	public static abstract class Decs{
		public Decs() {}
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Decs_vacia{
		public Decs_vacia() {}
		public abstract void procesa(Procesamiento p);
	}
	
	public static  class Decs_una extends Decs{
		private Decs dec;
		public Decs_una(Decs dec) {
			super();
			this.dec = dec;
		}
		public void procesa(Procesamiento p) {p.procesa(this);};
	}
	
	public static class Tipo extends Dec{
		public Tipo(StringAlocated s) {
			super(s);
		}
		public TipoDeclaracion tipo() {
			return TipoDeclaracion.TIPO;
		}
		
		public void procesa(Procesamiento p) { p.procesa(this);}
		
	}
	
	public static class Var extends Dec{
		public Var(StringAlocated s) {
			super(s);
		}
		public TipoDeclaracion tipo() {
			return TipoDeclaracion.VAR;
		}
		public void procesa(Procesamiento p) {p.procesa(this);}
	}
	
	public static class Decs_muchas extends Decs{
		private Dec dec;
		private Decs decs;
		
		public Decs_muchas(Dec dec, Decs decs) {
			super();
			this.dec=dec;
			this.decs=decs;
		}
		
		public Dec getDec() {return this.dec;}
		public Decs getDecs() {return this.decs;}
		
		public void procesa(Procesamiento p) {p.procesa(this);}
	}
	
}
