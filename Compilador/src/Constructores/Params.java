package Constructores;

public class Params {
	public enum TipoParam{
		FORMAL, REAL
	}
	
	public static abstract class Param {
		
		public abstract TipoParam tipo();
		
		private StringAlocated id;
		
		public Param(StringAlocated s) {this.id=s;}
		
		public StringAlocated getStringAlocated() {return this.id;}
		
		public abstract void procesa(Procesamiento p);
	}
	
	public abstract class PForm extends Param{
		public PForm(StringAlocated s) {
			super(s);
		}
		public TipoParam tipo() {
			return TipoParam.FORMAL;
		}
	}
	
	public abstract class PForms extends PForm{
		public PForms(StringAlocated s) {
			super(s);
		}
	}
	
	public class PForms_una extends PForms{
		private PForm pForm;
		
		public PForms_una(StringAlocated s, PForm pForm) {
			super(s);
			this.pForm = pForm;
		}
		
		public PForm getPForm() {return this.pForm;}
		public void procesa(Procesamiento p) {p.procesa(this);}
		
	}
	
	public class PForms_muchas extends PForms{
		private PForm pForm;
		private PForms pForms;
		
		public PForms_muchas(StringAlocated s, PForm pForm, PForms pForms) {
			super(s);
			this.pForm = pForm;
			this.pForms = pForms;
		}
		
		public PForm getPForm() {return this.pForm;}
		public PForms getPForms() {return this.pForms;}
		
		public void procesa(Procesamiento p) { p.procesa(this);}
	}
	
	
}
