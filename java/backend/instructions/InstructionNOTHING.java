package backend.instructions;

import backend.variables.*;
import java.util.*;


public class InstructionNOTHING implements Instruction {

public Object x;

public InstructionNOTHING(Object x) {
        this.x = x;
}

public List<Object> getOperands() {
        return new ArrayList<Object>();
}

public void show() {
        try {
                System.out.println("NOTHING\t" + ((Variable)this.x).getName() + " (" + this.x + ")");

        } catch (Exception e) {
                System.out.println("NOTHING\t" + this.x);
        }
}

}
