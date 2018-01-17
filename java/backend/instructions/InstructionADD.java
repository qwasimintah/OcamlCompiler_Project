package backend.instructions;

import backend.variables.*;
import java.io.*;
import java.util.*;
import backend.variables.*;
import backend.functions.*;

public class InstructionADD implements Instruction {
public List<Object> operands = new ArrayList<Object>();

public InstructionADD(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands() {
        List<Object> op = new ArrayList<Object>();
        Object op1 = operands.get(0);
        Object op2 = operands.get(1);
        if (op1 instanceof Variable) {
                op.add(op1);
        }
        else if (op1 instanceof Instruction) {
                for (Object o : ((Instruction)op1).getOperands()) {
                        op.add(o);
                }
        }
        if (op2 instanceof Variable) {
                op.add(op2);
        }
        else if (op2 instanceof Instruction) {
                for (Object o : ((Instruction)op2).getOperands()) {
                        op.add(o);
                }
        }
        return op;
}

public void show() {
        System.out.println("ADD\t" + operands.get(0) + " " + operands.get(1));
}
}
