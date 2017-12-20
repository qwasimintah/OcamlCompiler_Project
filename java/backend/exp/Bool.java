package exp;

import translation.*;

public class Bool extends Exp {
    final boolean b;

    public Bool(boolean b) {
        this.b = b;
    }

    <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }

    void accept(Visitor v) {
        v.visit(this);
    }
}
