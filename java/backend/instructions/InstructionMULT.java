package backend.instructions;

import backend.variables.*;
import backend.functions.*;
import java.util.*;

public class InstructionMULT implements Instruction {
public List<Object> operands =new ArrayList<Object>();

public InstructionMULT(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands () {
        return operands;
}

public void show() {
        System.out.println("MULT\t" + operands.get(0) + " " + operands.get(1));
}
}
