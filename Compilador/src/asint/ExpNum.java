package asint;

public class ExpNum extends Exp{
	private StringLocalizado id;
	
	public ExpNum(StringLocalizado id) {
		this.id = id;
	}
	
	@Override
	public int prioridad() {
		return 0;
	}

	@Override
	public void procesa(Procesamiento procesamiento) {
	}

}
