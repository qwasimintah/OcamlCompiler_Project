package instructions;
import variables.*;

import java.io.*;

public class InstructionADD implements Instruction {
Object x, y;
public InstructionADD(Variable x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionADD(Integer x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionADD(Variable x, Integer y) {
        this.x = x;
        this.y = y;
}

public InstructionADD(Integer x, Integer y) {
        this.x = x;
        this.y = y;
}

public void show() {
        System.out.println("ADD " + x + " " + y);
}

public static void main(String[] args) {
        VInteger x = new VInteger("x", 10, null);
        VInteger y = new VInteger("y", 10, null);
        InstructionADD i = new InstructionADD(x, y);
        i.show();
}
}
