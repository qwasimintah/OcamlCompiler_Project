package backend.instructions;

import java.util.*;

public interface Instruction {

public List<Object> getOperands();

public abstract void show();
}
