package exp;

import translation.*;

public class Float extends Exp {
    float f;

    public Float(float f) {
        this.f = f;
    }

    <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }
    void accept(Visitor v) {
        v.visit(this);
    }
}
