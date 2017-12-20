package variables;

public class Parameter extends Variable {
private String name;
private Register register;
private HashMap<Register, Variable> registers;
private Integer offset;
private Function function;

public Parameter(String name, HashMap<Register, Variable> registers, Function func) {
        super(name, registers, func);
}

}
