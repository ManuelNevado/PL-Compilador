package asint;

abstract class ExpAditiva extends ExpBin {
    public ExpAditiva(Exp arg0, Exp arg1) {
        super(arg0,arg1);
    }
    public final int prioridad() {
        return 0;
    }
}