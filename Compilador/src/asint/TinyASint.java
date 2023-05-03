package asint;



public class TinyASint {
    

    // Constructoras    
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
    public Decs decs_muchas(Decs decs, Dec dec) {
        return new Decs_muchas(decs,dec);
    }
    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s,fila,col);
    }
}
