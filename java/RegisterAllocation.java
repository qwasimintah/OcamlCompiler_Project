import java.io.*;
import java.util.Hashtable;
import java.util.*;

public class RegisterAllocation {
public static void initRegisters(HashMap<Register, Variable> registers) {
        for (Integer i = 4; i < 13; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }
}

public static void showRegisters(HashMap<Register, Variable> registers) {
        Set set = registers.entrySet();
        Iterator i = set.iterator();

        while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                Register reg = (Register)me.getKey();
                System.out.print("r" + reg.getIndex() + ": ");
                System.out.println(me.getValue());
        }
        System.out.println();
}

public static void main(String[] args) {
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(12);
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
