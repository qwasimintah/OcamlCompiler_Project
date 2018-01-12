package backend.variables;

import java.io.*;
import java.util.*;
import backend.functions.*;
import backend.booleans.*;

public class VBoolean extends Variable {
private BooleanExpression exp;

public VBoolean(String name, BooleanExpression exp, Function func) {
        super(name, func);
        this.exp = exp;
}

public VBoolean(String name, boolean b, Function func) {
        super(name, func);
        if (b) {
                this.exp = new BooleanTrue(name, func);
        } else {
                this.exp = new BooleanFalse(name, func);
        }
}

public BooleanExpression getExp() {
        return exp;
}
}
