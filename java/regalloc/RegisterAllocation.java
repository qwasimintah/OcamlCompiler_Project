import java.io.*;
import java.util.Hashtable;
import java.util.*;
import instructions.*;
import variables.*;
import registers.*;
import functions.*;
import exceptions.*;

public class RegisterAllocation {

private static HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);

public static void initRegisters() {
        for (Integer i = 4; i < 13; i++) {
                Register reg = new Register(i);
                registers.put(reg, null);
        }
}

public static void showRegisters() {
        for(Object key: registers.keySet()) {
                Register reg = (Register) key;
                System.out.print("r" + reg.getIndex() + ": ");
                try {
                        System.out.println(registers.get(reg).getName());
                } catch (NullPointerException e) {
                        System.out.println("empty");
                }
        }
        System.out.println("\n");
}

public static void example() {
        HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
        initRegisters();
        showRegisters();
        VInteger[] variables = new VInteger[10];
        try {
                for (Integer i = 0; i < 10; i++) {
                        VInteger x = new VInteger("x", i, registers);
                        variables[i] = x;
                        x.allocRegister();
                        showRegisters();
                }
        } catch (NoAvailableRegister e) {
                System.out.println(e.getMessage());
        }
        VInteger i = variables[0];
        i.kill();
        showRegisters();
}

public static void VBA(Function fun) {
        for (Instruction inst : fun.getInstructions()) {
                for (Object op : inst.getOperands()) {
                        try {
                                Variable var = (Variable) op;
                                var.allocRegister();
                        }
                        catch (NoAvailableRegister e) {
                                System.out.println(e.getMessage());
                                showRegisters();
                                return;
                        }
                }
        }
        showRegisters();
}

public static void main(String[] args) {
        initRegisters();
        showRegisters();
        Function fun = new Function(new ArrayList(), new ArrayList());
        for (Integer i = 0; i < 10; i++) {
                VInteger x = new VInteger("x" + i.toString(), 10, registers);
                InstructionADD inst = new InstructionADD(fun, x, x);
                System.out.println(inst.operands.get(0).getName());
                fun.addInstruction(inst);
        }
        VBA(fun);
}
}
