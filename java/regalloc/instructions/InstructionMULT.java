package instructions;

import java.io.*;
import java.util.*;
import variables.*;

public class InstructionMULT implements Instruction {
public List<Object> operands;

public InstructionMULT(Variable x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Integer x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Variable x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionMULT(Integer x, Integer y) {
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
