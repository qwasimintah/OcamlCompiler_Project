package exp;

import translation.*;
import java.util.*;

public class Tuple extends Exp {
    final List<Exp> es;

    public Tuple(List<Exp> es) {
        this.es = es;
    }

    <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }

    void accept(Visitor v) {
        v.visit(this);
    }
}
