package exp;

import translation.*;

public class FNeg extends Exp {
    final Exp e;

    public FNeg(Exp e) {
        this.e = e;
    }

    <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }

    void accept(Visitor v) {
        v.visit(this);
    }
}
