import java.io.*;
import java.util.Hashtable;
import java.util.*;
import instructions.*;
import variables.*;
import registers.*;

public class RegisterAllocation {
public static void initRegisters(HashMap<Register, Variable> registers) {
        for (Integer i = 4; i < 13; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }
}

public static void showRegisters(HashMap<Register, Variable> registers) {
        for(Object key: registers.keySet()) {
                Register reg = (Register) key;
                System.out.print("r" + reg.getIndex() + ": ");
                System.out.println(registers.get(reg));
        }
        System.out.println("\n");
}

public static void main(String[] args) {
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
        initRegisters(registers);
        showRegisters(registers);
        VInteger[] variables = new VInteger[10];
        for (Integer i = 0; i < 10; i++) {
                VInteger x = new VInteger("x", i, registers);
                variables[i] = x;
                x.allocRegister();
                showRegisters(registers);
        }
        VInteger i = variables[0];
        i.kill();
        showRegisters(registers);
}
}
