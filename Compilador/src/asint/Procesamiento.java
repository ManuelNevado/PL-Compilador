package asint;

public interface Procesamiento {
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Id exp);
    void procesa(Num exp);
    void procesa(Dec dec);
    void procesa(Decs_muchas decs);
    void procesa(Decs_una decs);
    void procesa(Prog_sin_decs prog);    
    void procesa(Prog_con_decs prog);
	void procesa(SumaReal sumaReal);    
}