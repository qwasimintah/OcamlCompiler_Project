package backend.variables;

import java.io.*;
import java.util.*;
import backend.functions.*;

public class VInteger extends Variable {
private Integer value;

// public VInteger(String name, Integer value, LinkedHashMap registers, Function func) {
public VInteger(String name, Integer value, Function func) {
        super(name, func);
        this.value = value;
}

public Integer getValue() {
        return value;
}

public void setValue(Integer newValue) {
        value = newValue;
}
}
