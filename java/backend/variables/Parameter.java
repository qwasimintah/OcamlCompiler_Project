package backend.variables;

import backend.registers.*;
import java.util.*;
import backend.functions.*;

public class Parameter extends Variable {

private String reg;

// public Parameter(String name, String reg,  LinkedHashMap registers, Function func) {
public Parameter(String name, String reg, Function func) {
        super(name, func);
        this.reg=reg;
}

public String getVReg() {
        return reg;
}
}
