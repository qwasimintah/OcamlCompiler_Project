/**
  * Implementation of the alpha conversion of an expression
  *
  *@author Fabien Tribel
  *
  */

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

private static ArrayList<Register> registers = new ArrayList<Register>(9);
private static ArrayList<Register> parametersRegisters = new ArrayList<Register>(4);

public static void VBA(Function func) {
  /**
    * Very Basic Allocation that put variables in free registers and spill them
    * if all registers are taken
    *
    *@param func a Function object

    *@return nothing
  */
        for (Variable var : func.getVariables()) {
                try {
                        if (var.getRegister() == null) {
                                var.allocRegister();
                        }
                }
                catch (Exception e) {
                        System.out.println(e.getMessage());
                        return;
                }
        }
}

// public static void SpillEverything(Function func) {
//         for (Instruction inst : func.getInstructions()) {
//                 for (Object op : inst.getOperands()) {
//                         try {
//                                 Variable var = (Variable) op;
//                                 if (var.getOffset() == null) {
//                                         var.spill();
//                                 }
//                         }
//                         catch (Exception e) {
//                                 System.out.println(e.getMessage());
//                                 return;
//                         }
//                 }
//         }
// }

public static void LinearScan(Function func) {
  /**
    * Linear Scan for register allocation
    *
    * @param func a Function object
    *
    * @return nothing
  */
        HashSet<Variable> variables = new HashSet<Variable>();
        List<Interval>intervals = new ArrayList<Interval>();
        Integer i = 0;

        for (Variable v : func.getArguments()) {
                v.allocParametersRegister();
        }

        for (Instruction inst : func.getInstructions()) {
                try {
                        for (Object op : inst.getOperands()) {
                                Variable var = (Variable) op;
                                if (!variables.contains(var)) {
                                        var.getInterval().setStartingPoint(i);
                                        var.getInterval().setEndingPoint(i);
                                        variables.add(var);
                                        intervals.add(var.getInterval());
                                } else {
                                        var.getInterval().setEndingPoint(i);
                                }

                        }
                } catch (Exception e) {;}
                i++;
        }

        Collections.sort(intervals);

        for (Integer j = 0; j <= i; j++) {
                for (Interval interval : intervals) {
                        if (interval.getStartingPoint() == j) {
                                interval.getVariable().allocRegister();
                        }
                        if (interval.getEndingPoint() == j) {
                                interval.getVariable().kill();
                        }
                }
        }
}
}
