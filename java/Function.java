import java.util.*;

public class Function {
private List<Instruction> instructions;
private List<Variable> arguments;
public Function(List arguments, List instructions) {
        this.instructions = instructions;
        this.arguments = arguments;
}

public List getInstructions() {
        return instructions;
}

public void putInstruction(Instruction instruction) {
        instructions.add(instruction);
}

public Iterator<Instruction> interator() {
        return instructions.iterator();
}

public void show() {
        for (Instruction i : instructions) {
                i.show();
        }
}

public static void main(String[] args) {
        VInteger x = new VInteger("x", 1, null);
        List<Instruction> instructions = new ArrayList<Instruction>();
        Function f = new Function(null, instructions);
        Instruction add = new InstructionADD(x, 2);
        f.putInstruction(add);
        Instruction sub = new InstructionSUB(1, 2);
        f.putInstruction(sub);
        Instruction mult = new InstructionMULT(1, 2);
        f.putInstruction(mult);
        Instruction ass = new InstructionASSIGN(x, 4);
        f.putInstruction(ass);

        f.show();
}
}
