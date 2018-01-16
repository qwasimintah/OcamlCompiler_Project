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
        System.out.println("IF\t" + this.cond + " THEN " + this.branch_then.getName() + " ELSE " + this.branch_else.getName());
        System.out.println("");
        this.branch_then.show();
        this.branch_else.show();
}

public ArrayList<Object> getOperands() {
        ArrayList<Object> ops = new ArrayList<Object>();

        for (Instruction inst : this.branch_then.getInstructions()) {
                for (Object o : inst.getOperands()) {
                        if (!(ops.contains(o)) && o != null) {
                                ops.add(o);
                        }
                }
        }
        for (Instruction inst : this.branch_else.getInstructions()) {
                for (Object o : inst.getOperands()) {
                        // System.out.println(o);
                        if (!(ops.contains(o)) && o != null) {
                                ops.add(o);
                        }
                }
        }
        // System.out.println("getOps IF");
        // for (Object o : ops) {
        //         // System.out.print(((Variable)o).getName() + " ");
        //         System.out.println(o);
        // }
        return ops;
}

}
