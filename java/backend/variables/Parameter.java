package backend.variables;

import backend.registers.*;
import java.util.*;
import backend.functions.*;

public class Parameter extends Variable {

private String reg;

public Parameter(String name, String reg,  TreeMap registers, Function func) {
        super(name, registers, func);
        this.reg=reg;
}

public String getVReg() {
        return reg;
}
}
