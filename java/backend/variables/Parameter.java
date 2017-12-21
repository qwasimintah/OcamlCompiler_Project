package variables;
import registers.*;
import java.util.Hashtable;
import java.util.*;
import functions.*;

public class Parameter extends Variable {

private Integer value;

  public Parameter(String name, Integer value,  HashMap registers, Function func) {
          super(name, registers, func);
          this.value=value;
  }

  public Integer getValue() {
          return value;
  }
}
