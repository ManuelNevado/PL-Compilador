package asint;

//TODO Tipos base

public class TinyASint {
    
    public Prog prog_con_decs(Exp exp, Decs decs) {
        return new Prog_con_decs(exp,decs);
    }
    public Prog prog_sin_decs(Exp exp) {
        return new Prog_sin_decs(exp);
    }
    
    public Exp suma(Exp arg0, Exp arg1) {
        return new Suma(arg0,arg1);
    }
    public Exp resta(Exp arg0, Exp arg1) {
        return new Resta(arg0,arg1);
    }
    public Exp mul(Exp arg0, Exp arg1) {
        return new Mul(arg0,arg1);
    }
    public Exp div(Exp arg0, Exp arg1) {
        return new Div(arg0,arg1);
    }
    public Exp num(StringLocalizado num) {
        return new Num(num);
    }
    public Exp id(StringLocalizado num) {
        return new Id(num);
    }
    public Dec dec(StringLocalizado id, StringLocalizado val) {
        return new Dec(id,val, TipoDeclaracion.NULL);
    }
    public Decs decs_una(Dec dec) {
        return new Decs_una(dec);
    }
    public Dec_var dec_var(StringLocalizado id, StringLocalizado val, TipoDeclaracion tipo) {
    	return new Dec_var(id, val, tipo);
    }
    public Dec_tipo dec_tipo(StringLocalizado id, StringLocalizado val, TipoDeclaracion tipo) {
    	return new Dec_tipo(id,val,tipo);
    }
    public Dec_campo dec_campo(StringLocalizado id, StringLocalizado val, TipoDeclaracion tipo) {
    	return new Dec_campo(id, val, tipo);
    }
    public Decs decs_muchas(Decs decs, Dec dec) {
        return new Decs_muchas(decs,dec);
    }
	public Decs decs_vacia(Decs decs) {
	    	return new Decs_muchas(null, null);
	}
	public Campos cs_uno(Campo c) {
		Campos cs = new Campos();
		return cs.add(c);
	}
	public Campos cs_muchos(Campo c, Campos cs) {
		return cs.add(c);
	}
    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}























