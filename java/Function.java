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
        Instruction add = new InstructionADD(x, 2);
        instructions.add(add);
        Instruction sub = new InstructionSUB(1, 2);
        instructions.add(sub);
        Instruction mult = new InstructionMULT(1, 2);
        instructions.add(mult);
        Instruction ass = new InstructionASSIGN(x, 4);
        instructions.add(ass);

        Function f = new Function(null, instructions);
        f.show();
}
}
