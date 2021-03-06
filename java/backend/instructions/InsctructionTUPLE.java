package backend.instructions;

import backend.variables.*;
import java.io.*;
import java.util.*;
import backend.variables.*;
import backend.functions.*;

public class InstructionTUPLE implements Instruction {
public List<Object> operands = new ArrayList<Object>();

public InstructionTUPLE(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands() {
        return operands;
}

public void show() {
        System.out.println("ADD\t" + operands.get(0) + " " + operands.get(1));
}
}
