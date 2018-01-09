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
private LinkedHashMap<Register, Variable> registers;
private Integer offset;
private Function function;
private Interval interval;

public Variable(String name, LinkedHashMap<Register, Variable> registers, Function func) {
        this.name = name;
        this.registers = registers;
        this.function = func;
        this.interval = new Interval(this);
        // allocRegister();
}

public String getName() {
        return name;
}

public void allocRegister() {
        // for(Object key: registers.keySet()) {
        //         if (registers.get(key) == null) {
        //                 Register reg = (Register) key;
        //                 this.setRegister(reg);
        //                 registers.put(reg, this);
        //                 return;
        //         }
        // }

        try {
                 Map.Entry<Register,Variable> result =
                        registers.entrySet().stream()
                        .filter(entry -> entry.getValue() == null)
                        .findFirst()
                        .get();
                Register reg = result.getKey();
                this.setRegister(reg);
                registers.put(reg, this);
                return;
        } catch (NoSuchElementException e) {
                spill();
        }

        // registers.forEach(
        //         (reg, var) -> {
        //           System.out.println(reg);
        //           System.out.println(var);
        //                 if (var == null) {
        //                         this.setRegister(reg);
        //                         registers.put(reg, this);
        //                         return;
        //                 }
        //         }
        //         );

        // for (registers.Entry<Register, Variable> entry : registers.entrySet()) {
        //         Register reg = entry.getKey();
        //         Variable var = entry.getValue();
        //         if (var == null) {
        //                 this.setRegister(reg);
        //                 registers.put(reg, this);
        //                 return;
        //         }
        // }

        // spill();
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
