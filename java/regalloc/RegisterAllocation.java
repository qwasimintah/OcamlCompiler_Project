import java.io.*;
import java.util.Hashtable;
import java.util.*;
import instructions.*;
import variables.*;
import registers.*;
import functions.*;
import exceptions.*;

public class RegisterAllocation {

public static HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);

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
                System.out.println(registers.get(reg));
        }
        System.out.println("\n");
}

public static void example() {
        VInteger[] variables = new VInteger[10];
        for (Integer i = 0; i < 10; i++) {
                try {
                        VInteger x = new VInteger("x", i, registers);
                        variables[i] = x;
                        x.allocRegister();
                        showRegisters();
                }
                catch (NoAvailableRegister e) {
                        System.out.println("No more register left !");
                }
        }
        VInteger i = variables[0];
        VInteger j = variables[1];
        i.spill();
        j.spill();
        i.spill();
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
                                return;
                        }
                }
        }
}

public static void main(String[] args) {
        initRegisters();
        showRegisters();
        List<Instruction> instructions = new ArrayList<Instruction>();
        for (Integer i = 0; i < 10; i++) {
                VInteger x = new VInteger("x", 10, registers);
                InstructionADD inst = new InstructionADD(x, x);
                instructions.add(inst);
        }
        Function fun = new Function(new ArrayList(), instructions);
        VBA(fun);
}
}
