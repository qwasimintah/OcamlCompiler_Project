package backend;

import java.io.*;
import java.util.*;
import backend.instructions.*;
import backend.variables.*;
import backend.registers.*;
import backend.functions.*;
import backend.exceptions.*;
import backend.intervals.*;
import backend.translation.*;

public class RegisterAllocation {

private static HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
private static HashMap<Register, Variable> parametersRegisters = new HashMap<Register, Variable>(4);

public static void VBA(Function fun) {
        // for (Instruction inst : fun.getInstructions()) {
        //         for (Object op : inst.getOperands()) {
        //                 try {
        //                         Variable var = (Variable) op;
        //                         if (var.getRegister() == null) {
        //                                 var.allocRegister();
        //                         }
        //                 }
        //                 catch (Exception e) {
        //                         System.out.println(e.getMessage());
        //                         RegisterUtils.showRegisters(registers);
        //                         return;
        //                 }
        //         }
        // }
        for (Variable var : fun.getVariables()) {
                try {
                        if (var.getRegister() == null) {
                                var.allocRegister();
                        }
                }
                catch (Exception e) {
                        System.out.println(e.getMessage());
                        RegisterUtils.showRegisters(registers);
                        return;
                }
        }
        RegisterUtils.showRegisters(fun.registers);
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
        List<Interval >intervals = new ArrayList<Interval>();
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
                // System.out.println(var.getInterval().getDescription());
                intervals.add(var.getInterval());
        }
        Collections.sort(intervals);
        for (Interval interval : intervals) {
                System.out.println(interval.getDescription());
        }
        for (Integer j = 0; j < i; j++) {
                RegisterUtils.showRegisters(registers);
                for (Interval interval : intervals) {
                        if (interval.getStartingPoint() == j) {
                                try {
                                        interval.getVariable().allocRegister();
                                } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                }
                        }
                        if (interval.getEndingPoint() == j) {
                                interval.getVariable().kill();
                        }
                }
        }
        RegisterUtils.showRegisters(registers);
}

// public static void main(String[] args) {
//         RegisterUtils.initRegisters(registers, parametersRegisters);
//         RegisterUtils.showRegisters(registers);
//         Function fun = new Function("main", new ArrayList(), new ArrayList());
//         // VInteger x = new VInteger("x", 10, registers, fun);
//         // for (Integer i = 0; i < 14; i++) {
//         //         VInteger x = new VInteger("x" + i.toString(), 10, registers, fun);
//         //         InstructionSUB inst = new InstructionSUB(fun, x, x);
//         //         fun.addInstruction(inst);
//         // }
//         VInteger x = new VInteger("x", 1, registers, fun);
//         VInteger y = new VInteger("y", 2, registers, fun);
//         VInteger z = new VInteger("z", 10, registers, fun);
//         VInteger a = new VInteger("a", 11, registers, fun);
//         VInteger b = new VInteger("b", 2, registers, fun);
//         VInteger c = new VInteger("c", 4, registers, fun);
//         VInteger d = new VInteger("d", 4, registers, fun);
//         VInteger e = new VInteger("e", 100, registers, fun);
//         InstructionASSIGN assx = new InstructionASSIGN(fun, x, x.getValue());
//         InstructionASSIGN assy = new InstructionASSIGN(fun, y, y.getValue());
//         InstructionASSIGN assz = new InstructionASSIGN(fun, z, z.getValue());
//         InstructionASSIGN assa = new InstructionASSIGN(fun, a, a.getValue());
//         InstructionASSIGN assb = new InstructionASSIGN(fun, b, b.getValue());
//         InstructionASSIGN assc = new InstructionASSIGN(fun, c, c.getValue());
//         InstructionSUB subxa = new InstructionSUB(fun, x, a);
//         InstructionASSIGN assd = new InstructionASSIGN(fun, d, d.getValue());
//         InstructionASSIGN asse = new InstructionASSIGN(fun, e, subxa);
//         InstructionADD addyz = new InstructionADD(fun, y, z);
//         InstructionMULT multbc = new InstructionMULT(fun, b, c);
//         InstructionDIV divbd = new InstructionDIV(fun, b, d);
//         fun.addInstruction(assx);
//         fun.addInstruction(assy);
//         fun.addInstruction(assz);
//         fun.addInstruction(assa);
//         fun.addInstruction(assb);
//         fun.addInstruction(assc);
//         fun.addInstruction(subxa);
//         fun.addInstruction(assd);
//         fun.addInstruction(asse);
//         fun.addInstruction(addyz);
//         fun.addInstruction(multbc);
//         fun.addInstruction(divbd);
//
//
//         //  SpillEverything(fun);
//         // LinearScan(fun);
//         // fun.showVariablesState();
//
//         TranslationVisitor tv = new TranslationVisitor();
//         tv.main(new String[0]);
//         // VInteger x = new VInteger("x", 10, registers, fun);
//         // InstructionSUB i1 = new InstructionSUB(fun, 3, 1);
//         // InstructionASSIGN ass = new InstructionASSIGN(fun, x, i1);
// }
}
