package backend.variables;

import java.io.*;
import java.util.*;
import backend.functions.*;

public class VInteger extends Variable {
  private Integer value;

  public VInteger(String name, Integer value, HashMap registers, Function func) {
    super(name, registers, func);
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
