package backend.exp;

import backend.translation.*;

public class FAdd extends Exp {
public final Exp e1;
public final Exp e2;

public FAdd(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
}

public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
}
public void accept(Visitor v) {
        v.visit(this);
}
}
