package backend.functions;

import java.util.*;
import backend.variables.*;
import backend.instructions.*;
import backend.registers.*;

public class Function {

private String name;
private List<Instruction> instructions;
private List<Parameter> arguments;
private Integer spillOffset = 4;
private HashSet<Variable> variables = new HashSet<Variable>();
public HashMap<Register, Variable> registers;
public HashMap<Register, Variable> parametersRegisters;

public Function(String name, List<Parameter> arguments, List<Instruction> instructions,
                HashMap<Register, Variable> registers,
                HashMap<Register, Variable> parametersRegisters) {
        this.name = name;
        this.instructions = instructions;
        this.arguments = arguments;
        this.registers = registers;
        this.parametersRegisters = parametersRegisters;
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

public List<Parameter> getArguments() {
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
