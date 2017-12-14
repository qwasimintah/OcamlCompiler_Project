package functions;

import java.util.*;
import variables.*;
import instructions.*;

public class Function {
private List<Instruction> instructions;
private List<Object> arguments;
public Function(List<Object> arguments, List<Instruction> instructions) {
        this.instructions = instructions;
        this.arguments = arguments;
}

public List<Instruction> getInstructions() {
        return instructions;
}

public void putInstruction(Instruction instruction) {
        instructions.add(instruction);
}

public Iterator<Instruction> iterator() {
        return instructions.iterator();
}

public List<Object> getArguments() {
        return arguments;
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
