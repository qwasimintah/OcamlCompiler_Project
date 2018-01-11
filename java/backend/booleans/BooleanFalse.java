package backend.booleans;

import java.util.*;
import backend.functions.*;

public class BooleanFalse extends BooleanExpression {
private String name;
private Function func;

public BooleanFalse(String name, Function func) {
        super(name, func);
        this.name = name;
        this.func = func;
}
}
