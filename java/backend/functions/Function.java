package functions;

import java.util.*;
import variables.*;
import instructions.*;

public class Function {
private List<Instruction> instructions;
private List<Variable> arguments;
private Integer spillOffset = 0;
private HashSet<Variable> variables = new HashSet<Variable>();

public Function(List<Variable> arguments, List<Instruction> instructions) {
        this.instructions = instructions;
        this.arguments = arguments;
}

public List<Instruction> getInstructions() {
        return instructions;
}

public void addInstruction(Instruction instruction) {
        instructions.add(instruction);

        for (Object v : instruction.getOperands()) {
                if (v instanceof Variable) {
                        variables.add((Variable) v);
                } else if (v instanceof Instruction) {
                        for (Object x : ((Instruction)v).getOperands()) {
                                if (x instanceof Variable) {
                                        variables.add((Variable) x);
                                }
                        }
                }
        }
}

public Iterator<Instruction> iterator() {
        return instructions.iterator();
}

public List<Variable> getArguments() {
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

public void showVariablesState() {
        for (Variable v : variables) {
                v.getSaveState();
        }
}
}
