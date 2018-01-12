package frontend;

import java.util.*;
import ast.type.*;

public class Equation{
  public Type t1;
  public Type t2;
  public Equation(Type type1, Type type2){
    t1 = type1;
    t2 = type2;
  }

  public String toString(){
      return "(" + this.t1 + ", " + this.t2 + ")";
  }
}
