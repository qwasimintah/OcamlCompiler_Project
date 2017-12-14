package instructions;

import java.util.*;
import variables.*;
import functions.*;

public class InstructionASSIGN implements Instruction {
public List<Object> operands;

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
        return operands;
}

public void show() {
        System.out.println(operands.get(0) + " := " + operands.get(1));
}

public static void main(String[] args) {
        VInteger x = new VInteger("x", 10, null);
        VInteger y = new VInteger("y", 1, null);
        InstructionASSIGN i = new InstructionASSIGN(x, 10);
        i.show();
}
}
