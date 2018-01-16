package backend.instructions;

import backend.variables.*;
import backend.functions.*;
import java.util.*;

public class InstructionASSIGN implements Instruction {
public List<Object> operands=new ArrayList<Object>();

public InstructionASSIGN(Function func, Object x, Object y) {
        this.operands.add(x);
        this.operands.add(y);
}

public List<Object> getOperands () {
        List<Object> op = new ArrayList<Object>();
        op.add(operands.get(0));
        if (operands.get(1) instanceof Instruction) {
                for (Object o : ((Instruction)operands.get(1)).getOperands()) {
                        op.add(o);
                }
        }
        return op;
}

public void show() {
        System.out.println("ASSIGN\t" + ((Variable)operands.get(0)).getName() + " (" + operands.get(0) + ") " + operands.get(1));
}
}
