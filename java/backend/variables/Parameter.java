package variables;
import registers.*;
import java.util.Hashtable;
import java.util.*;
import functions.*;

public class Parameter extends Variable {

private String reg;

  public Parameter(String name, String reg,  HashMap registers, Function func) {
          super(name, registers, func);
          this.reg=reg;
  }

  public String getVReg() {
          return reg;
  }
}
