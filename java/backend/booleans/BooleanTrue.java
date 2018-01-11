package backend.booleans;

import java.util.*;
import backend.functions.*;

public class BooleanTrue extends BooleanExpression {
private String name;
private Function func;

public BooleanTrue(String name, Function func) {
        super(name, func);
        this.name = name;
        this.func = func;
}
}
