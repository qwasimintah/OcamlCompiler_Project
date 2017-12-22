package instructions;

import variables.*;
import functions.*;
import java.util.*;

public class InstructionMULT implements Instruction {
public List<Object> operands =new ArrayList<Object>();

public InstructionMULT(Function func, Variable x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Function func, Integer x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Function func, Variable x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Function func, Integer x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands () {
        return operands;
}

public void show() {
        System.out.println("MULT " + operands.get(0) + " " + operands.get(1));
}
}
