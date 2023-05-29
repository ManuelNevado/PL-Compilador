package asint;

public class ExpArray extends Exp{
	
	private StringLocalizado id;
	
	public ExpArray(StringLocalizado id) {
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
