package exp;

import translation.*;

public class FSub extends Exp {
    final Exp e1;
    final Exp e2;

    public FSub(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }
    void accept(Visitor v) {
        v.visit(this);
    }
}
