package ast.type;

public class TVar extends Type {
    String v;

    public TVar(String v) {
        this.v = v;
    }
    @Override
    public String toString() {
        return v;
    }
}
