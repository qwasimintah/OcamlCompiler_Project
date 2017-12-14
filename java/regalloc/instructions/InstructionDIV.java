package instructions;

import java.io.*;
import java.util.*;
import variables.*;
import functions.*;

public class InstructionDIV implements Instruction {
public List<Object> operands;

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

public List<Object> getOperands () {
        return operands;
}

public void show() {
        System.out.println("DIV " + operands.get(0) + " " + operands.get(1));
}

}
