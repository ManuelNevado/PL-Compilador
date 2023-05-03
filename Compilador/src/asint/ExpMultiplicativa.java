package asint;

abstract class ExpMultiplicativa extends ExpBin {
    public ExpMultiplicativa(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public final int prioridad() {
        return 1;
    }
}