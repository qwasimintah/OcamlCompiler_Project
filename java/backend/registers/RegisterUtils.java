package backend.registers;

import java.util.*;
import backend.variables.*;

public class RegisterUtils {

public static void initRegisters(HashMap<Register, Variable> registers, HashMap<Register, Variable> parametersRegisters) {
        for (Integer i = 4; i < 12; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }

        for (Integer i = 2; i < 4; i++) {
                Register reg = new Register(i);
                parametersRegisters.put(reg, null);
        }
}

public static void showRegisters(HashMap<Register, Variable> registers) {
        System.out.println("------ State of the registers ------");
        for(Object key: registers.keySet()) {
                Register reg = (Register) key;
                System.out.print("r" + reg.getIndex() + "\t:\t");
                try {
                        System.out.println(registers.get(reg).getName());
                } catch (NullPointerException e) {
                        System.out.println("empty");
                }
        }
        System.out.println("\n");
}
}
