package backend.exp;

import backend.translation.*;
import java.util.*;

public class Tuple extends Exp {
public final List<Exp> es;

public Tuple(List<Exp> es) {
        this.es = es;
}

public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
}

public void accept(Visitor v) {
        v.visit(this);
}
}
