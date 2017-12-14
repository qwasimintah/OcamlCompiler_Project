package instructions;

import java.io.*;
import java.util.*;
import variables.*;

public class InstructionADD implements Instruction {
public List<Object> operands = new ArrayList<Object>();

public InstructionADD(Variable x, Variable y) {
        try {
          System.out.println(((VInteger)x).getValue());
          System.out.println(y);
        } catch (Exception e) {
          System.out.println("nope");
        }
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionADD(Integer x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionADD(Variable x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionADD(Integer x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands() {
        return operands;
}

public void show() {
        System.out.println("ADD " + operands.get(0) + " " + operands.get(1));
}

public static void main(String[] args) {
        VInteger x = new VInteger("x", 10, null);
        VInteger y = new VInteger("y", 10, null);
        InstructionADD i = new InstructionADD(x, y);
        i.show();
}
}
