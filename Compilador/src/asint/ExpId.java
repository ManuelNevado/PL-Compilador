package asint;

public class ExpId extends Exp{
	
	private StringLocalizado id;
	
	public ExpId(StringLocalizado id) {
		this.id = id;
	}
	
	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void procesa(Procesamiento procesamiento) {
		// TODO Auto-generated method stub
		
	}

}
