package backend.instructions;

import backend.variables.*;
import java.util.*;
import backend.functions.*;
import backend.instructions.*;

public class InstructionIF implements Instruction {

public VBoolean cond;
public Function branch_then;
public Function branch_else;

public InstructionIF(VBoolean cond, Function branch_then, Function branch_else) {
        this.cond = cond;
        this.branch_then = branch_then;
        this.branch_else = branch_else;
}

public void show() {
        System.out.println("If " + this.cond + " then " + this.branch_then + " else " + this.branch_else);
}

public ArrayList<Object> getOperands() {
        ArrayList<Object> ops = new ArrayList<Object>();
        for (Instruction inst : this.branch_then.getInstructions()) {
                for (Object o : inst.getOperands()) {
                        ops.add(o);
                }
        }
        for (Instruction inst : this.branch_else.getInstructions()) {
                for (Object o : inst.getOperands()) {
                        ops.add(o);
                }
        }
        return ops;
}

}
