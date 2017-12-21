package exp;

import translation.*;

public class Var extends Exp {
public final Id id;

public Var(Id id) {
        this.id = id;
}

public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
}
public void accept(Visitor v) {
        v.visit(this);
}
}
