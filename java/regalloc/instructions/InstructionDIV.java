package instructions;
import variables.*;

public class InstructionDIV implements Instruction {
Object x, y;
public InstructionDIV(Variable x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionDIV(Integer x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionDIV(Variable x, Integer y) {
        this.x = x;
        this.y = y;
}

public InstructionDIV(Integer x, Integer y) {
        this.x = x;
        this.y = y;
}

public void show() {
        System.out.println("DIV " + x + " " + y);
}
}
