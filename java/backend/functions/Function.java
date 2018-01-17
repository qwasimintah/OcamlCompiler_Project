/**
  * Class Function, representing a block containing some instructions,
  * possibly having arguments.
  * This is the second biggest container in our Jerry structure after the List
  * of Functions (flist).
  *
  * @author Fabien Tribel
  *
  */

package backend.functions;

import java.util.*;
import backend.variables.*;
import backend.instructions.*;
import backend.registers.*;

public class Function {

private String name;
private List<Instruction> instructions;
private List<Variable> arguments;
private Integer spillOffset = 4;
private Integer spillOffsetParameters = 4;
private HashSet<Variable> variables = new HashSet<Variable>();
public ArrayList<Register> registers;
public ArrayList<Register> parametersRegisters;
public ArrayList<Function> flist;

public Function(String name, List<Variable> arguments, List<Instruction> instructions,
                ArrayList<Register> registers,
                ArrayList<Register> parametersRegisters, ArrayList<Function> flist) {
        /**
          * Constructor of a Function object.
          *
          *@param name the Function's id
          *@param arguments its list of arguments
          *@param instructions the list of instructions the function consists in
          *@param registers the list of available registers (for local variables)
          *@param parametersRegisters the list of available registers (for arguments)
          *@param flist the list of Functions it belongs to

          *@return nothing
        */
        this.name = name;
        this.instructions = instructions;
        this.arguments = arguments;
        this.registers = registers;
        this.parametersRegisters = parametersRegisters;
        this.flist = flist;
}

public Function(String name, List<Variable> arguments, List<Instruction> instructions,
                ArrayList<Register> registers,
                ArrayList<Register> parametersRegisters,
                HashSet<Variable> variables, ArrayList<Function> flist) {
        this.name = name;
        this.instructions = instructions;
        this.arguments = arguments;
        this.registers = registers;
        this.parametersRegisters = parametersRegisters;
        this.variables = variables;
        this.flist = flist;
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

public List<Variable> getArguments() {
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

public void addVariable(Variable var) {
        variables.add(var);
}

public void setVariables (HashSet<Variable> locals){
        variables = locals;
}

public void show() {
        System.out.println("******** " + this.getName() + " *********");
        for (Instruction i : instructions) {
                i.show();
        }
        System.out.println("");
}

public void showVariablesState() {
        System.out.println("***" + this.getName() + " : Variables state***");
        for (Variable v : variables) {
                v.getSaveState();
        }
        System.out.println("***" + this.getName() + " : Arguments state***");
        for (Variable v : this.getArguments()) {
                v.getSaveState();
        }
        System.out.println("");
}

public void showVariables() {
        for (Variable v : variables) {
                System.out.println(v.getName());
        }
}
}
