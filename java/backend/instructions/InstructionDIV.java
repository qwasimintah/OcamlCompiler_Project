package backend.instructions;

import backend.variables.*;
import backend.functions.*;
import java.util.*;

public class InstructionDIV implements Instruction {
public List<Object> operands =new ArrayList<Object>();

public InstructionDIV(Function func, Variable x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionDIV(Function func, Integer x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionDIV(Function func, Variable x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionDIV(Function func, Integer x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionDIV(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands () {
        return operands;
}

public void show() {
        System.out.println("DIV\t" + operands.get(0) + " " + operands.get(1));
}
}
