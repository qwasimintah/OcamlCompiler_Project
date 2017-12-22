package utils;

import registers.*;

public class Utils {
public static void initRegisters() {
        for (Integer i = 4; i < 13; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }
}
}
