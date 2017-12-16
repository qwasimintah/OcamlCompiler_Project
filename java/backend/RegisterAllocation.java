import java.io.*;
import java.util.Hashtable;
import java.util.*;
import instructions.*;
import variables.*;
import registers.*;
import functions.*;
import exceptions.*;
import intervals.*;

public class RegisterAllocation {

private static HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);

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

public static void LinearScan(Function fun) {
        HashSet<Variable> variables = new HashSet<Variable>();
        Integer i = 0;

        for (Instruction inst : fun.getInstructions()) {
                for (Object op : inst.getOperands()) {
                        Variable var = (Variable) op;
                        if (!variables.contains(var)) {
                                variables.add(var);
                                var.getInterval().setStartingPoint(i);
                                var.getInterval().setEndingPoint(i);
                        } else {
                                var.getInterval().setEndingPoint(i);
                        }
                }
                i++;
        }
        for (Variable var : variables) {
                System.out.println(var.getInterval().getDescription());
        }
}

public static void main(String[] args) {
        RegisterUtils.initRegisters(registers);
        RegisterUtils.showRegisters(registers);
        Function fun = new Function("main", new ArrayList(), new ArrayList());
        VInteger x = new VInteger("x", 10, registers, fun);
        for (Integer i = 0; i < 9; i++) {
                // VInteger x = new VInteger("x" + i.toString(), 10, registers, fun);
                InstructionSUB inst = new InstructionSUB(fun, x, x);
                fun.addInstruction(inst);
        }
        //  SpillEverything(fun);
        LinearScan(fun);
        fun.showVariablesState();
        // VInteger x = new VInteger("x", 10, registers, fun);
        // InstructionSUB i1 = new InstructionSUB(fun, 3, 1);
        // InstructionASSIGN ass = new InstructionASSIGN(fun, x, i1);
}
}
