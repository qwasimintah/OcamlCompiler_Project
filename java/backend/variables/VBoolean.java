package backend.variables;

import java.io.*;
import java.util.*;
import backend.functions.*;

public class VBoolean extends Variable {
private boolean value;

public VInteger(String name, boolean value, Function func) {
        super(name, func);
        this.value = value;
}

public boolean getValue() {
        return value;
}

public void setValue(boolean newValue) {
        this.value = newValue;
}
}
