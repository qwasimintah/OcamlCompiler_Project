package backend.booleans;

import java.util.*;
import backend.functions.*;

public class BooleanLE extends BooleanExpression {
private String name;
private Function func;
public List<Object> operands = new ArrayList<Object>();

public BooleanLE(String name, Function func, Object x, Object y) {
        super(name, func);
        this.name = name;
        this.func = func;
        operands.add(x);
        operands.add(y);
}
}
