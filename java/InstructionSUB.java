public class InstructionSUB extends Instruction {
Object x, y;
public InstructionSUB(Variable x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionSUB(Integer x, Variable y) {
        this.x = x;
        this.y = y;
}

public InstructionSUB(Variable x, Integer y) {
        this.x = x;
        this.y = y;
}

public InstructionSUB(Integer x, Integer y) {
        this.x = x;
        this.y = y;
}

public void show() {
        System.out.println("SUB " + x + " " + y);
}
}
