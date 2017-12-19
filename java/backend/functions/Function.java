package functions;

import java.util.*;
import variables.*;
import instructions.*;

public class Function {

private String name;
private List<Instruction> instructions;
private List<Variable> arguments;
private Integer spillOffset = 4;
private HashSet<Variable> variables = new HashSet<Variable>();

public Function(String name, List<Variable> arguments, List<Instruction> instructions) {
        this.name = name;
        this.instructions = instructions;
        this.arguments = arguments;
}

public List<Instruction> getInstructions() {
        return instructions;
}

public String getName(){

        return name;
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

public HashSet<Variable> getVariables(){

        return variables;
}

public void setVariables (HashSet<Variable> locals){
        variables =locals;
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
