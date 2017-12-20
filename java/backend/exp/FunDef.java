package exp;

import translation.*;
import java.util.*;

public class FunDef {
    final Id id;
    final Type type;
    final List<Id> args;
    final Exp e;

    public FunDef(Id id, Type t, List<Id> args, Exp e) {
        this.id = id;
        this.type = t;
        this.args = args;
        this.e = e;
    }

}
