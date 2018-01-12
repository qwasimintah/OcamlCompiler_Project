package backend.instructions;

import backend.variables.*;
import java.util.*;
import backend.functions.*;

public class InstructionBRANCH implements Instruction {

private Object foperand;
private Object Soperand;

private String Tlabel;
private String Flabel;

private List<Object> operands = new ArrayList<Object>();

public InstructionBRANCH(Object foperand, Object Soperand, String Tlabel, String Flabel) {
        this.foperand = foperand;
        this.Soperand = Soperand;
        this.Tlabel = Tlabel;
        this.Flabel = Flabel;
}

public List<Object> getOperands() {
        operands.add(foperand);
        operands.add(Soperand);
        return operands;
}

public List<String> getLabels(){
        return new ArrayList<String>().add(Tlabel).add(Flabel);
}

public ConditionType getCondionType(){

    return condition;
}


public void show() {

        //return new StringBuilder().append("IF ").append()
    }

}
