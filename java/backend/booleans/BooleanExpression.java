/**
  * Boolean expressions upper class
  *
  *@author Fabien Tribel
  *
  */

package backend.booleans;

import java.util.*;
import backend.functions.*;

public abstract class BooleanExpression {
private String name;
private Function func;

public BooleanExpression(String name, Function func) {
        this.name = name;
        this.func = func;
}
}
