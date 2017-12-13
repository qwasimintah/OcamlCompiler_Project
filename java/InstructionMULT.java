public class InstructionMULT extends Instruction {
Object x, y;
public InstructionMULT(Variable x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionMULT(Integer x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionMULT(Variable x, Integer y) {
        this.x = x;
        this.y = y;
}

public InstructionMULT(Integer x, Integer y) {
        this.x = x;
        this.y = y;
}

public void show() {
        System.out.println("MULT " + x + " " + y);
}
}
