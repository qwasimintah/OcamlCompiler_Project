package backend.variables;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import backend.registers.*;
import backend.exceptions.*;
import backend.functions.*;
import backend.intervals.*;

public class Variable {
private String name;
private Register register;
private Register parametersRegister;
private Integer offset;
private Integer parametersOffset;
private Function func;
private Interval interval;

public Variable(String name, Function func) {
        this.name = name;
        this.func = func;
        this.interval = new Interval(this);
}

public String getName() {
        return name;
}

public void allocRegister() {
        try {
                Register reg = this.func.registers.get(0);
                this.func.registers.remove(0);
                this.setRegister(reg);
                return;
        }
        catch (Exception e) {
                this.spill();
                return;
        }
}

public void allocParametersRegister() {
        if (func.parametersRegisters.isEmpty()) {
                this.spillParameter();
        } else {
                Register reg = this.func.parametersRegisters.get(0);
                func.parametersRegisters.remove(0);
                this.setParametersRegister(reg);
        }
}

public void spill() {
        Integer spillOffset = this.func.getOffset();
        this.func.setOffset(spillOffset + 4);
        this.setOffset(spillOffset);
}

public void spillParameter() {
        Integer spillOffset = this.func.getOffsetParameters();
        this.func.setOffsetParameters(spillOffset + 4);
        this.setParametersOffset(spillOffset);
}

public void spill(Function func) {
        Integer spillOffset = func.getOffset();
        func.setOffset(spillOffset + 4);
        this.setOffset(spillOffset);
}

public void setRegister(Register reg) {
        register = reg;
}

public void setParametersRegister(Register reg) {
        parametersRegister = reg;
}

public Register getRegister() {
        return register;
}

public Register getParametersRegister() {
        return parametersRegister;
}

public void setOffset(Integer i) {
        if (i % 4 == 0) {
                offset = i;
        } else {
                System.out.println("Incorrect offset : " + i);
        }
}

public void setParametersOffset(Integer i) {
        if (i % 4 == 0) {
                parametersOffset = i;
        } else {
                System.out.println("Incorrect offset : " + i);
        }
}

public Integer getOffset() {
        return offset;
}

public Integer getParametersOffset() {
        return parametersOffset;
}

public void getSaveState() {
        Register regVar = this.getRegister();
        Register regArg = this.getParametersRegister();
        Integer offVar = this.getOffset();
        Integer offArg = this.getParametersOffset();
        String storeVar;
        String storeArg;
        if (regVar != null) {
                storeVar = regVar.getName();
        } else if (offVar != null) {
                storeVar = "[fp + " + this.getOffset() + "]";
        } else {
                storeVar = "-";
        }
        if (regArg != null) {
                storeArg = regArg.getName();
        } else if (offArg != null) {
                storeArg = "[fp + " + offArg + "]";
        } else {
                storeArg = "-";
        }
        System.out.println("Var/Arg " + this.getName() + " (" + this + ") " + " : " + storeVar + "/" + storeArg);
}

public Interval getInterval() {
        return this.interval;
}

public void kill() {
        if (this.register != null) {
          func.registers.add(this.register);
        }
}

public void killParameter() {
        func.parametersRegisters.add(this.parametersRegister);
        // Collections.sort(func.parametersRegisters);
}
}
