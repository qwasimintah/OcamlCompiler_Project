package functions;

import java.util.*;
import variables.*;
import instructions.*;

public class Function {
private List<Instruction> instructions;
private List<Object> arguments;
private Integer spillOffset = 0;

public Function(List<Object> arguments, List<Instruction> instructions) {
        this.instructions = instructions;
        this.arguments = arguments;
}

public List<Instruction> getInstructions() {
        return instructions;
}

public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
}

public Iterator<Instruction> iterator() {
        return instructions.iterator();
}

public List<Object> getArguments() {
        return arguments;
}

public Integer getOffset() {
        return spillOffset;
}

public void setOffset(Integer value) {
        spillOffset = value;
}

public void show() {
        for (Instruction i : instructions) {
                i.show();
        }
}
}
