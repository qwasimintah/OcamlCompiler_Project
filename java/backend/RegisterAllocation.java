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

// public static void initRegisters() {
//         for (Integer i = 4; i < 13; i++) {
//                 Register reg = new Register(i);
//                 registers.put(reg, null);
//         }
// }
//
// public static void showRegisters() {
//         System.out.println("------ State of the registers ------");
//         for(Object key: registers.keySet()) {
//                 Register reg = (Register) key;
//                 System.out.print("r" + reg.getIndex() + "\t:\t");
//                 try {
//                         System.out.println(registers.get(reg).getName());
//                 } catch (NullPointerException e) {
//                         System.out.println("empty");
//                 }
//         }
//         System.out.println("\n");
// }

// public static void example() {
//         HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
//         initRegisters();
//         showRegisters();
//         VInteger[] variables = new VInteger[10];
//         try {
//                 for (Integer i = 0; i < 10; i++) {
//                         VInteger x = new VInteger("x", i, registers);
//                         variables[i] = x;
//                         x.allocRegister();
//                         showRegisters();
//                 }
//         } catch (NoAvailableRegister e) {
//                 System.out.println(e.getMessage());
//         }
//         VInteger i = variables[0];
//         i.kill();
//         showRegisters();
// }

public static void VBA(Function fun) {
        for (Instruction inst : fun.getInstructions()) {
                for (Object op : inst.getOperands()) {
                        try {
                                Variable var = (Variable) op;
                                if (var.getRegister() == null) {
                                        var.allocRegister();
                                }
                        }
                        catch (NoAvailableRegister e) {
                                System.out.println(e.getMessage());
                                RegisterUtils.showRegisters(registers);
                                return;
                        }
                }
        }
        RegisterUtils.showRegisters(registers);
}

public static void SpillEverything(Function fun) {
        for (Instruction inst : fun.getInstructions()) {
                for (Object op : inst.getOperands()) {
                        try {
                                Variable var = (Variable) op;
                                if (var.getOffset() == null) {
                                        var.spill();
                                }
                        }
                        catch (Exception e) {
                                System.out.println(e.getMessage());
                                return;
                        }
                }
        }
}

public static void main(String[] args) {
        RegisterUtils.initRegisters(registers);
        RegisterUtils.showRegisters(registers);
        //Function fun = new Function(new ArrayList(), new ArrayList());
        /*for (Integer i = 0; i < 9; i++) {
                VInteger x = new VInteger("x" + i.toString(), 10, registers, fun);
                InstructionSUB inst = new InstructionSUB(fun, x, x);
                fun.addInstruction(inst);
        }
        SpillEverything(fun);
        fun.showVariablesState();*/
        // VInteger x = new VInteger("x", 10, registers, fun);
        // InstructionSUB i1 = new InstructionSUB(fun, 3, 1);
        // InstructionASSIGN ass = new InstructionASSIGN(fun, x, i1);
}
}
