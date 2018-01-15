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
        if (func.registers.isEmpty()) {
                spill();
        } else {
                Register reg = func.registers.get(0);
                func.registers.remove(0);
                this.setRegister(reg);
        }
}

public void allocParametersRegister() {
        if (func.parametersRegisters.isEmpty()) {
                spillParameter();
        } else {
                Register reg = func.parametersRegisters.get(0);
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
        if (this.getRegister() != null) {
                System.out.println("Variable " + this.getName() + " (" + this + ") " + "stored in register " + this.getRegister().getName());
        } else if (this.getOffset() != null) {
                System.out.println("Variable " + this.getName() + " (" + this + ") " + "stored in memory at [fp + " + this.getOffset() + "]");
        } else {
                System.out.println("Variable " + this.getName() + " (" + this + ") " + "not saved !");
        }

        if (this.getParametersRegister() != null) {
                System.out.println("Parameter " + this.getName() + " (" + this + ") " + "stored in register " + this.getParametersRegister().getName());
        } else if (this.getParametersOffset() != null) {
                System.out.println("Parameter " + this.getName() + " (" + this + ") " + "stored in memory at [fp + " + this.getParametersOffset() + "]");
        } else {
                System.out.println("Parameter " + this.getName() + " (" + this + ") " + "not saved !");
        }
}

public Interval getInterval() {
        return this.interval;
}

public void kill() {
        // registers.put(this.register, null);
        func.registers.add(this.register);
}

public void killParameter() {
        // registers.put(this.register, null);
        func.parametersRegisters.add(this.parametersRegister);
}
}
