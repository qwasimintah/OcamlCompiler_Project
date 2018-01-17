package frontend;

import java.util.*;
import ast.type.*;

public class Equation{
  // Class of equation (to put in the list), the construction is always the same :
  // the two Types we want to compare.
  public Type t1;
  public Type t2;

  //Only one way to construct it.
  public Equation(Type type1, Type type2){
    t1 = type1;
    t2 = type2;
  }

  public String toString(){
      return "(" + this.t1 + ", " + this.t2 + ")";
  }
}
