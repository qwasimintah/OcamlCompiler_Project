public class InstructionASSIGN extends Instruction {
private Object x, y;

InstructionASSIGN(Variable x, Variable y) {
        this.x = x;
        this.y = y;
}

InstructionASSIGN(Variable x, Integer y) {
        this.x = x;
        this.y = y;
}

public void show() {
        System.out.println(x + " := " + y);
}

public static void main(String[] args) {
        VInteger x = new VInteger("x", 10, null);
        VInteger y = new VInteger("y", 1, null);
        InstructionASSIGN i = new InstructionASSIGN(x, 10);
        i.show();
}
}
