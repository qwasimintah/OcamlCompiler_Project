package exp;

import ast.*;
import java.util.*;

public class App extends Exp {
    public final Exp e;
    public final List<Exp> es;

    public App(Exp e, List<Exp> es) {
        this.e = e;
        this.es = es;
    }

    public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
