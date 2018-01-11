package backend.booleans;

import java.util.*;
import backend.functions.*;

public class BooleanEQ extends BooleanExpression {
private String name;
private Function func;
public List<Object> operands = new ArrayList<Object>();

public BooleanEQ(String name, Function func, Object x, Object y) {
        super(name, func);
        this.name = name;
        this.func = func;
        operands.add(x);
        operands.add(y);
}
}
