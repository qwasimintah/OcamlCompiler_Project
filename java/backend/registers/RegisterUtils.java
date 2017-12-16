package registers;

import java.util.*;
import registers.*;
import variables.*;

public class RegisterUtils {

public static void initRegisters(HashMap<Register, Variable> registers) {
        for (Integer i = 4; i < 5; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
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
