package backend.variables;

import java.io.*;
import java.util.*;
import backend.functions.*;
import backend.tuples.*;

public class VTuple extends Variable {
private TupleJerry value;

public VTuple(String name, TupleJerry value, Function func) {
        super(name, func);
        this.value = value;
}

public TupleJerry getValue() {
        return value;
}

public void setValue(TupleJerry newValue) {
        value = newValue;
}
}
