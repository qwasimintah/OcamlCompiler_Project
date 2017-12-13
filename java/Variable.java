import java.io.*;
import java.util.Hashtable;
import java.util.*;

abstract class Variable {
private String name;
private Register register;
private HashMap registers;

public Variable(String name, HashMap registers) {
        this.name = name;
        this.registers = registers;
}

public String getName() {
        return name;
}

public void allocRegister() {
        for(Object key: registers.keySet()) {
                if (registers.get(key) == null) {
                        Register reg = (Register) key;
                        this.setRegister(reg);
                        registers.put(reg, this);
                        return;
                }
        }
        System.out.println("All registers are allocated !");
}

public void setRegister(Register reg) {
        register = reg;
}

public Register getRegister() {
        return register;
}

public void kill() {
        registers.put(this.register, null);
}
}
