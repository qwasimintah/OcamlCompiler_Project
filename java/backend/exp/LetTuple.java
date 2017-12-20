package exp;

import translation.*;
import java.util.*;

public class LetTuple extends Exp {
    final List<Id> ids;
    final List<Type> ts;
    final Exp e1;
    final Exp e2;

    public LetTuple(List<Id> ids, List<Type> ts, Exp e1, Exp e2) {
        this.ids = ids;
        this.ts = ts;
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
