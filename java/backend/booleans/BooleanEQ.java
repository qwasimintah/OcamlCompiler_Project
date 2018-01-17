/**
  * Boolean expression EQ
  *
  *@author Fabien Tribel
  *
  */

package backend.booleans;

import java.util.*;
import backend.functions.*;

public class BooleanEQ extends BooleanExpression {
private String name;
private Function func;
public List<Object> operands = new ArrayList<Object>();

public BooleanEQ(String name, Function func, Object x, Object y) {
  /**
    * Boolean expression EQ
    *
    *@param name the expression id
    *@param func the function it belongs to
    *@param x the left operand
    *@param y the right operand
    *
    *@return nothing
  */
        super(name, func);
        this.name = name;
        this.func = func;
        operands.add(x);
        operands.add(y);
}
}
