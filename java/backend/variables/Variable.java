package variables;

import java.io.*;
import java.util.Hashtable;
import java.util.*;
import registers.*;
import exceptions.*;
import functions.*;
import intervals.*;

public abstract class Variable {
private String name;
private Register register;
private HashMap<Register, Variable> registers;
private Integer offset;
private Function function;
private Interval interval;

public Variable(String name, HashMap<Register, Variable> registers, Function func) {
        this.name = name;
        this.registers = registers;
        this.function = func;
        this.interval = new Interval(this);
}

public String getName() {
        return name;
}

public void allocRegister() throws NoAvailableRegister {
        for(Object key: registers.keySet()) {
                if (registers.get(key) == null) {
                        Register reg = (Register) key;
                        this.setRegister(reg);
                        registers.put(reg, this);
                        return;
                }
                else{
                        spill();
                }
        }
        throw new NoAvailableRegister();
}

public void spill() {
        Integer spillOffset = this.function.getOffset();
        this.function.setOffset(spillOffset + 4);
        this.setOffset(spillOffset);
}

public void setRegister(Register reg) {
        register = reg;
}

public Register getRegister() {
        return register;
}

public void setOffset(Integer i) {
        if (i % 4 == 0) {
                offset = i;
        } else {
                System.out.println("Incorrect offset : " + i);
        }
}

public Integer getOffset() {
        return offset;
}

public void getSaveState() {
        if (this.getRegister() != null) {
                System.out.println("Variable " + this.getName() + " stored in register " + this.getRegister().getName());
        } else if (this.getOffset() != null) {
                System.out.println("Variable " + this.getName() + " stored in memory at [fp + " + this.getOffset() + "]");
        } else {
                System.out.println("Variable " + this.getName() + " not saved !");
        }
}

public Interval getInterval() {
        return this.interval;
}

public void kill() {
        registers.put(this.register, null);
}
}
