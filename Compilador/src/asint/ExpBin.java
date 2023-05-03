package asint;

abstract class ExpBin extends Exp {
    private Exp arg0;
    private Exp arg1;
    public Exp arg0() {return arg0;}
    public Exp arg1() {return arg1;}
    public ExpBin(Exp arg0, Exp arg1) {
        super();
        this.arg0 = arg0;
        this.arg1 = arg1;
    }
}