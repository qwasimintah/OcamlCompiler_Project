package backend.registers;

import java.util.*;
import backend.variables.*;

public class RegisterUtils {

// public static void initRegisters(LinkedHashMap<Register, Variable> registers, LinkedHashMap<Register, Variable> parametersRegisters) {
public static void initRegisters(ArrayList<Register> registers, ArrayList<Register> parametersRegisters) {
        for (Integer i = 4; i < 11; i++) {
                Register reg = new Register(i);
                // registers.put(reg, null);
                registers.add(reg);
        }

        for (Integer i = 2; i < 4; i++) {
                Register reg = new Register(i);
                // parametersRegisters.put(reg, null);
                parametersRegisters.add(reg);
        }
}

// public static void showRegisters(LinkedHashMap<Register, Variable> registers) {
public static void showRegisters(ArrayList<Register> registers) {
        System.out.println("------ State of the registers ------");
        // for(Object key: registers.keySet()) {
        //         Register reg = (Register) key;
        //         System.out.print("r" + reg.getIndex() + "\t:\t");
        //         try {
        //                 System.out.println(registers.get(reg).getName());
        //         } catch (NullPointerException e) {
        //                 System.out.println("empty");
        //         }
        // }
        // System.out.println("\n");
        for (Register reg : registers) {
                System.out.print("r" + reg.getIndex() + " empty");
        }
}
}
