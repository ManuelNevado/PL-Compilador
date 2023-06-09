package asint;

//TODO Retorno de tipos base

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
    public Dec dec_var(StringLocalizado id, StringLocalizado val, TipoDeclaracion tipo) {
    	return new Dec(id, val, tipo);
    }
    public Dec dec_tipo(StringLocalizado id, StringLocalizado val, Tipo t) {
    	Dec dec = new Dec(id,val,TipoDeclaracion.DEC_TYPE);
    	dec.setTipoVinculacion(t);
    	return dec;
    }
    public Dec_campo dec_campo(StringLocalizado id, StringLocalizado val, TipoDeclaracion tipo) {
    	return new Dec_campo(id, val, tipo);
    }
    public Decs decs_muchas(Decs decs, Dec dec) {
        return decs.add(dec);
    }
	public Decs decs_vacia(Decs decs) {
	    	return new Decs();
	}
	public Campos cs_uno(Campo c) {
		return (new Campos()).add(c);
	}
	public Campos cs_muchos(Campo c, Campos cs) {
		return cs.add(c);
	}
	
	public Dec dec_proc(StringLocalizado id, Parametros pforms, Decs decs, Instrucciones Is) {
		return new Procedimiento(id, pforms, decs, Is);
	}
	
	public Parametros pf_ninguno() {
		return new Parametros();
	}
	
	public Parametros pf_muchos(Parametros pforms, Parametro pform) {
		return pforms.add(pform);
	}
	
	public Parametros pf_uno(Parametro pform) {
		return (new Parametros()).add(pform);
	}
	
	public Parametro pf_valor(Tipo t, StringLocalizado valor) {
		return new Parametro(valor,t, TipoParametro.P_VAL);
	}
	
	public Parametro pf_var(Tipo t, StringLocalizado id) {
		return new Parametro(id, t, TipoParametro.P_VAR);
	}
	
	public Instrucciones is_vacia() {
		return new Instrucciones();
	}
	
	public Instrucciones is_una(Instruccion_programa i) {
		return (new Instrucciones()).add(i);
	}
	
	public Instrucciones is_muchas(Instruccion_programa i, Instrucciones is) {
		return is.add(i);
	}
	
	public Instruccion_programa asig(Exp e0, Exp e1) {
		Instruccion_programa asignacion = new Instruccion_programa(Tipo.ASIG);
		asignacion.setE0(e0);
		asignacion.setE1(e1);
		return asignacion;
	}
	
	public Instruccion_programa if_then(Exp e0, Instrucciones is) {
		Instruccion_programa if_then = new Instruccion_programa(Tipo.IF_THEN);
		if_then.setE0(e0);
		if_then.setIs0(is);
		return if_then;
	}
	
	public Instruccion_programa if_then_else(Exp e0, Instrucciones is0, Instrucciones is1) {
		Instruccion_programa if_then_else = new Instruccion_programa(Tipo.IF_THEN_ELSE);
		if_then_else.setE0(e0);
		if_then_else.setIs0(is0);
		if_then_else.setIs1(is1);
		return if_then_else;
	}
	
	public Instruccion_programa _while(Exp e0, Instrucciones is) {
		Instruccion_programa _while = new Instruccion_programa(Tipo.WHILE);
		_while.setE0(e0);
		_while.setIs0(is);
		return _while;
	}
	
	public Instruccion_programa read(Exp e0) {
		Instruccion_programa read = new Instruccion_programa(Tipo.READ);
		read.setE0(e0);
		return read;
	}
	
	public Instruccion_programa write(Exp  e0) {
		Instruccion_programa write = new Instruccion_programa(Tipo.WRITE);
		write.setE0(e0);
		return write;
	}
	
	public Instruccion_programa nl() {return new Instruccion_programa(Tipo.NL);}
	
	public Instruccion_programa res_mem(Exp e0) {
		Instruccion_programa res_mem = new Instruccion_programa(Tipo.RES_MEM);
		res_mem.setE0(e0);
		return res_mem;
	}
	
	public Instruccion_programa del_mem(Exp e0) {
		Instruccion_programa del_mem = new Instruccion_programa(Tipo.DEL_MEM);
		del_mem.setE0(e0);
		return del_mem;
	}
	
	public Instruccion_programa call(Exp e0, Parametros preal) {
		Instruccion_programa call = new Instruccion_programa(Tipo.CALL);
		call.setE0(e0);
		call.setPreal(preal);
		return call;
	}
	
	public Parametros pr_ninguno() {
		return new Parametros();
	}
	
	public Parametros pr_uno(Parametro preal) {
		return (new Parametros()).add(preal);
	}
	
	public Parametros pr_muchos(Parametros preals, Parametro preal) {
		return preals.add(preal);
	}
	
	public Instruccion_programa comp(Decs decs, Instrucciones is) {
		Instruccion_programa comp = new Instruccion_programa(Tipo.COMP);
		comp.setDecs(decs);
		return comp;
	}
	
	public Exp exp_num(StringLocalizado id) {
		Exp exp_num = new ExpNum(id);
		exp_num.setTipo(TipoExpresion.NUM);
		return exp_num;
	}
	
	public Exp exp_real(StringLocalizado id) {
		Exp exp_real = new ExpReal(id);
		exp_real.setTipo(TipoExpresion.REAL);
		return exp_real;
	}
	
	public Exp exp_bool(StringLocalizado id) {
		Exp exp_bool = new ExpBool(id);
		exp_bool.setTipo(TipoExpresion.BOOL);
		return exp_bool;
	}
	
	public Exp exp_array(StringLocalizado id) {
		Exp exp_array = new ExpArray(id);
		exp_array.setTipo(TipoExpresion.ARRAY);
		return exp_array;
	}
	
	public Exp exp_id(StringLocalizado id) {
		Exp exp_id = new ExpId(id);
		exp_id.setTipo(TipoExpresion.ID);
		return exp_id;
	}
	
    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}























