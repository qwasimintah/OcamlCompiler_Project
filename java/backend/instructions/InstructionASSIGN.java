package instructions;

import variables.*;
import functions.*;
import java.util.*;

public class InstructionASSIGN implements Instruction {
public List<Object> operands=new ArrayList<Object>();

public InstructionASSIGN(Function func, Variable x, Variable y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionASSIGN(Function func, Variable x, Integer y) {
        this.operands.add(x);
        this.operands.add(y);
}

public InstructionASSIGN(Function func, Variable x, Instruction inst) {
        this.operands.add(x);
        this.operands.add(inst);
}

public List<Object> getOperands () {
        List<Object> op = new ArrayList<Object>();
        op.add(operands.get(0));
        if (operands.get(1) instanceof Instruction) {
                op.add(((Instruction)(operands.get(1))).getOperands(0));
                op.add(((Instruction)(operands.get(1))).getOperands(1));
        }
        return op;
}

public void show() {
        System.out.println(operands.get(0) + " := " + operands.get(1));
}
}
