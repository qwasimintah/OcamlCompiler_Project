package backend.instructions;

import backend.variables.*;
import java.util.*;


public class InstructionCALL implements Instruction {

private List<Object> params;
private String return_reg;
private String fname;
private List<Object> operands = new ArrayList<Object>();

public InstructionCALL(List<Object> params, String return_reg, String fname) {
        this.params = params;
        this.return_reg = return_reg;
        this.fname = fname;
}

public InstructionCALL(List<Object> params, String fname) {
        this.params = params;
        this.return_reg ="r0";
        this.fname= fname;
}

public List<Object> getParams() {
        return params;
}

public List<Object> getOperands() {
        operands.add(params.get(0));
        return operands;
}

public String getReturn() {
        return return_reg;
}

public String getFname() {
        return fname;
}

public void show() {
        System.out.print("CALL\t" + fname + " ");
        for (Object o : params) {
          System.out.print(o + " ");
        }
        System.out.println("");
}

}
