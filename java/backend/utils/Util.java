package utils;

import registers.*;

public class Utils {
public static void initRegisters() {
        for (Integer i = 4; i < 12; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }
}
}
