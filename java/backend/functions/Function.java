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
private Integer spillOffsetParameters = 4;
private HashSet<Variable> variables = new HashSet<Variable>();
private ArrayList<Object> parameters = new ArrayList<Object>();
public ArrayList<Register> registers;
public ArrayList<Register> parametersRegisters;

public Function(String name, List<Parameter> arguments, List<Instruction> instructions,
                ArrayList<Register> registers,
                ArrayList<Register> parametersRegisters) {
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

public Integer getOffsetParameters() {
        return spillOffsetParameters;
}

public void setOffset(Integer value) {
        spillOffset = value;
}

public void setOffsetParameters(Integer value) {
        spillOffsetParameters = value;
}

public HashSet<Variable> getVariables(){
        return variables;
}

public ArrayList<Object> getParameters() {
        return parameters;
}

public void setVariables (HashSet<Variable> locals){
        variables = locals;
}

public void setParameters(ArrayList<Object> params){

        parameters = params;
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

        for (Object o : parameters) {
             
                        if (o instanceof Variable) {
                                ((Variable)o).getSaveState();
                        }
                
        }
}

public void showVariables() {
        for (Variable v : variables) {
                System.out.println(v.getName());
        }

}
}
